package com.project.notesapp.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.notesapp.databinding.ActivityViewNotesBinding;

public class ViewNotesActivity extends AppCompatActivity {
ActivityViewNotesBinding binding;
    Intent data;

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         data = getIntent();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();


         setDataInView();
        setListener();
    }

    private void setListener()
    {
        binding.imageBackButton.setOnClickListener(v -> onBackPressed());

        binding.imageDelete.setOnClickListener(v -> {
            DocumentReference documentReference=firebaseFirestore.collection("notes").document(firebaseUser.getUid())
                    .collection("myNotes").document(data.getStringExtra("noteId"));
            documentReference.delete().addOnSuccessListener(unused -> {
                showToast("Note Deleted");
                onBackPressed();
            }).addOnFailureListener(e -> showToast("Failed to delete"));
        });


        binding.editNotes.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),EditNotesActivity.class);
            intent.putExtra(Variables.TITLE,binding.inputNoteTitle.getText().toString());
            intent.putExtra(Variables.DATE_TIME,data.getStringExtra(Variables.DATE_TIME));
            intent.putExtra(Variables.CONTENT,data.getStringExtra(Variables.CONTENT));
            intent.putExtra("noteId",data.getStringExtra("noteId"));

            v.getContext().startActivity(intent);

        });

    }
    private void setDataInView()
    {


        binding.inputNoteTitle.setText(data.getStringExtra(Variables.TITLE));
        binding.textDateTime.setText(data.getStringExtra(Variables.DATE_TIME));
        binding.inputNote.setText(data.getStringExtra(Variables.CONTENT));
    }
    //Displaying MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Get the updated note data and position
            String updatedTitle = data.getStringExtra(Variables.TITLE);
            String updatedDateTime = data.getStringExtra(Variables.DATE_TIME);
            String updatedContent = data.getStringExtra(Variables.CONTENT);


            // Update the UI with the updated note data
            binding.inputNoteTitle.setText(updatedTitle);
            binding.textDateTime.setText(updatedDateTime);
            binding.inputNote.setText(updatedContent);

            // Update the note data in the intent to reflect the changes
            data.putExtra(Variables.TITLE, updatedTitle);
            data.putExtra(Variables.DATE_TIME, updatedDateTime);
            data.putExtra(Variables.CONTENT, updatedContent);
        }
    }
    protected void onResume() {
        super.onResume();
        retrieveUpdatedNoteData();
    }

    //RETIERVING UPDATED NOTES FROM EDIT NOTES
    private void retrieveUpdatedNoteData() {
        String noteId = data.getStringExtra("noteId");
        DocumentReference documentReference = firebaseFirestore.collection("notes")
                .document(firebaseUser.getUid())
                .collection("myNotes")
                .document(noteId);

        documentReference.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String title = documentSnapshot.getString(Variables.TITLE);
                String dateTime = documentSnapshot.getString(Variables.DATE_TIME);
                String content = documentSnapshot.getString(Variables.CONTENT);

                binding.inputNoteTitle.setText(title);
                binding.textDateTime.setText(dateTime);
                binding.inputNote.setText(content);
            } else {
                showToast("Note does not exist");
            }
        }).addOnFailureListener(e -> showToast("Failed to retrieve note data"));
    }
}