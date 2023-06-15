package com.project.notesapp.activities.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.notesapp.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {

   private FirebaseAuth firebaseAuth;
private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        //CALLING setListners() METHOD
        setListeners();
    }

    //BUTTON CLICK FUNCTIONS
    private void setListeners()
    {
        //LOGIN PAGE BUTTON
        binding.buttonLogIn.setOnClickListener(v -> onBackPressed());

        //SIGNUP BUTTON
        binding.buttonSignUp.setOnClickListener(v -> {
            if(isValidSignUpDetails())  //CHEKING THE FIELD
            {
                    SignUp();  //CALLING SIGNUP METHOD
            }
        });
    }
    //SIGN UP ACTIVITY
    private void SignUp()
    {
      loading(true);

        //CREATING NEW USER
        firebaseAuth.createUserWithEmailAndPassword(binding.inputEmail.getText().toString().trim()
                ,binding.inputPassword.getText().toString().trim()).addOnCompleteListener(task -> {
                             if(task.isSuccessful())
                             {
                                 showToast("Registration Successfull");
                                 sendEmailVeriification();    //CALLING sendEmailVerification
                             }
                             else
                             {
                                loading(false);   //BUTTON VISIBLE
                                 showToast("Failed To Create New Account");
                             }
                });
    }

    //SENDING VERIFICATION EMAIL
    private void sendEmailVeriification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
            showToast("Verification Email is Sent Please Verify");
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
            });
        }

    }

    //Displaying MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

//CHECKING THE FIELD IS EMPTY OR INVALID
    private Boolean isValidSignUpDetails()
    {


         if(binding.inputEmail.getText().toString().trim().isEmpty())     //CHECKING EMAIL
         {
            showToast("Enter Email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches())  //CHECKING EMAIL PATTERN
        {
            showToast("Enter Valid Email");
            return false;
        }
        else if(binding.inputPassword.getText().toString().trim().isEmpty())  //PASSWORD
        {
            showToast("Enter Password");
            return false;
        }
        else if(binding.inputPassword.getText().toString().trim().length()<7)    //PASSWORD LENGTH
         {
            showToast("Password should be greater than 7 digits");
            return false;
         }
        else {
            return true;
        }

    }
    //Progress bar
    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.buttonSignUp.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignUp.setVisibility(View.VISIBLE);
        }
    }
}