package com.project.notesapp.activities.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.notesapp.R;
import com.project.notesapp.databinding.ActivityEditNotesBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EditNotesActivity extends AppCompatActivity {

    ActivityEditNotesBinding binding;
    Intent data;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNotesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        data =getIntent(); //GETTING NOTES CONTENT

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //DATE AND TIME
        binding.textDateTime.setText(
                new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        setDataInView();//SETTING NOTES CONTENT IN PAGE
        setListener();
    }
//SET LISTNERS
    private  void setListener()
    {
        //BACKBUTTON
        binding.imageBackButton.setOnClickListener(v -> onBackPressed());

        binding.saveNotes.setOnClickListener(v ->{
            if(isValidField())
            {
                updateDataInDatabase(); //UPDATE NOTES IN DATABASE
                setDataInView(); // SET NOTES CONTENT AFTER UPDATE
            }
        });
    }

    //UPDATING DATA IN DATABASE
    private void updateDataInDatabase()
    {
        DocumentReference documentReference = firebaseFirestore.collection("notes")
                .document(firebaseUser.getUid()).collection("myNotes").document(data.getStringExtra("noteId"));
        Map<String,Object> notes = new HashMap<>();
        notes.put(Variables.TITLE,binding.inputNoteTitle.getText().toString());
        notes.put(Variables.CONTENT,binding.inputNote.getText().toString());
        notes.put(Variables.DATE_TIME, binding.textDateTime.getText().toString());


        documentReference.update(notes).addOnSuccessListener(unused -> {
            showToast("Saved...");
            onBackPressed();

        }).addOnFailureListener(e -> showToast("Failed to saved"));

    }
//SETTING NOTES CONTENT IN PAGE
    private void setDataInView()
    {
        binding.inputNoteTitle.setText(data.getStringExtra(Variables.TITLE));
        binding.inputNote.setText(data.getStringExtra(Variables.CONTENT));
    }
    //Displaying MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }
    //CHECKING TITLE AND NOTES CONTENT IS EMPTY OR NOT
    private Boolean isValidField()
    {
        if(binding.inputNoteTitle.getText().toString().trim().isEmpty())     //CHECKING EMAIL
        {
            showToast("Enter Title");
            return false;
        }

        else if(binding.inputNote.getText().toString().trim().isEmpty())  //PASSWORD
        {
            showToast("Enter Notes");
            return false;
        }

        else {

            return true;
        }

    }
}