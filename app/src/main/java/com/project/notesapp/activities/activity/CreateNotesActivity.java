package com.project.notesapp.activities.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.notesapp.databinding.ActivityCreateNotesBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateNotesActivity extends AppCompatActivity {
    ActivityCreateNotesBinding binding;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //DATE AND TIME
        binding.textDateTime.setText(
                new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm", Locale.getDefault())
                        .format(new Date())
        );
        setListeners(); //CALLING LISTENERS
    }
    private void setListeners()
    {
        //BACK BUTTON
        binding.imageBackButton.setOnClickListener(v -> onBackPressed());

        //SAVE BUTTON
        binding.saveNotes.setOnClickListener(v ->{
            if(isValidField())
            {
                saveDataInDatabase(); //SAVING NOTES IN FIRESTORE
            }
        });

    }
    //SAVING NOTES INTO DATABASE
    private void saveDataInDatabase()
    {
        DocumentReference documentReference = firebaseFirestore.collection("notes")
                .document(firebaseUser.getUid()).collection("myNotes").document();
        Map<String,Object> notes = new HashMap<>();
        notes.put(Variables.TITLE,binding.inputNoteTitle.getText().toString().toUpperCase());

        notes.put(Variables.CONTENT,binding.inputNote.getText().toString());
        notes.put(Variables.DATE_TIME, binding.textDateTime.getText().toString());


        documentReference.set(notes).addOnSuccessListener(unused -> {
            showToast("Saved...");
            onBackPressed();
        }).addOnFailureListener(e -> showToast("Failed to saved"));

    }



    //CHECKING THE FIELD IS EMPTY OR INVALID
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

    //Displaying MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

}