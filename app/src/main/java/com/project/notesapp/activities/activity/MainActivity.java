package com.project.notesapp.activities.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.project.notesapp.R;
import com.project.notesapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;

    FirestoreRecyclerAdapter<FirebaseModel,NoteViewHolder> noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

            setListener();
        retriveDataInRecycleView();
    }
    private void setListener() {
        binding.newNotes.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), CreateNotesActivity.class)));


      //search function on writting
        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchQuery = s.toString().trim();
                performSearch(searchQuery);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this implementation
            }
        });


    }
       // SEARCHING THE TILTLE FORM FIREBASE
    private void performSearch(String query) {
        Query searchQuery;

        if (query.isEmpty()) {
            searchQuery = firebaseFirestore.collection("notes")
                    .document(firebaseUser.getUid())
                    .collection("myNotes")
                    .orderBy("title");
        } else {
            searchQuery = firebaseFirestore.collection("notes")
                    .document(firebaseUser.getUid())
                    .collection("myNotes")
                    .orderBy("title")
                    .startAt(query)
                    .endAt(query + "\uf8ff");
        }

        FirestoreRecyclerOptions<FirebaseModel> searchOptions = new FirestoreRecyclerOptions.Builder<FirebaseModel>()
                .setQuery(searchQuery, FirebaseModel.class)
                .build();

        noteAdapter.updateOptions(searchOptions);
    }

//RETRIVING ALL NOTES FORM FIRESTORE
    private void retriveDataInRecycleView()
    {
        Query query = firebaseFirestore.collection("notes").document(firebaseUser.getUid())
                .collection("myNotes").orderBy("title",Query.Direction.ASCENDING);

            FirestoreRecyclerOptions<FirebaseModel> usernotes = new FirestoreRecyclerOptions.Builder<FirebaseModel>()
                    .setQuery(query,FirebaseModel.class).build();

            noteAdapter = new FirestoreRecyclerAdapter<FirebaseModel, NoteViewHolder>(usernotes) {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull FirebaseModel model) {

                    int colorCode = getRandomColor();
                    holder.layoutNoteContianer.setBackgroundColor(holder.itemView.getResources().getColor(colorCode,null));

                    holder.title.setText(model.getTitle());

                    holder.dateTime.setText(model.getDateTime());
                    holder.content.setText(model.getContent());

                    String NoteID = noteAdapter.getSnapshots().getSnapshot(position).getId();


                    holder.itemView.setOnClickListener(v -> {
                        Intent intent = new Intent(v.getContext(),ViewNotesActivity.class);
                        intent.putExtra(Variables.TITLE,model.getTitle());

                        intent.putExtra(Variables.DATE_TIME,model.getDateTime());
                        intent.putExtra(Variables.CONTENT,model.getContent());
                        intent.putExtra("noteId",NoteID);

                        v.getContext().startActivity(intent);
                    });



                }

                @NonNull
                @Override
                public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contianer_notes,parent,false);
                    return new NoteViewHolder(view);
                }
            };


            //ADDING THE RESULT IN RECYCLEVIEW
            binding.notesRecycleView.setLayoutManager(
                    new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            );
            binding.notesRecycleView.setAdapter(noteAdapter);

    }
    public static class NoteViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;

        private  TextView content;
        private  TextView dateTime;
       private LinearLayout layoutNoteContianer;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.textTitle);

            content = itemView.findViewById(R.id.textContent);
            dateTime = itemView.findViewById(R.id.textDateTime);
            layoutNoteContianer = itemView.findViewById(R.id.layoutNoteContainer);


        }
    }
    protected  void onStart()
    {
        super.onStart();
        noteAdapter.startListening();
    }
    protected  void onStop()
    {
        super.onStop();
       if(noteAdapter !=null)
       {
           noteAdapter.startListening();
       }
    }
        //RANDOM COLORS FOR NOTES
    private int getRandomColor()
    {
        List<Integer> colorcode = new ArrayList<>();
        colorcode.add(R.color.c1);
        colorcode.add(R.color.c2);
        colorcode.add(R.color.c3);
        colorcode.add(R.color.c4);
        colorcode.add(R.color.c5);
        colorcode.add(R.color.c6);
        colorcode.add(R.color.c7);
        colorcode.add(R.color.c8);
        colorcode.add(R.color.c9);
        colorcode.add(R.color.c10);

        Random random = new Random();
        int number = random.nextInt(colorcode.size());
        return colorcode.get(number);

    }
    //Displaying MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

}