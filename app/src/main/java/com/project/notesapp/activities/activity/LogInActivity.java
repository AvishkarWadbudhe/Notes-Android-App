package com.project.notesapp.activities.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.notesapp.databinding.ActivityLoginBinding;

public class LogInActivity extends AppCompatActivity {
private ActivityLoginBinding binding;
private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        setListeners();
        LoggedIN();


    }
    private void LoggedIN()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser !=null)
        {
            finish();
            startActivity(new Intent(LogInActivity.this,MainActivity.class));
        }
    }
    //BUTTON CLICK ACTIVITY
    private void setListeners()
    {
        //CREATE NEW ACCOUNT BUTTON
        binding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
        //FORGOT PASSWORD BUTTON
        binding.textForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class)));
        //LOGIN BUTTON
        binding.buttonLogIn.setOnClickListener(v -> {
            if(isValidSignInDetails()){
            logIn();
            }
        });

    }
    //LOGIN ACTIVITY
private void logIn()
{
    loading(true);


        firebaseAuth.signInWithEmailAndPassword(binding.inputEmail.getText().toString().trim(),
                binding.inputPassword.getText().toString().trim()).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        checkMailVerification();
                    }
                    else
                    {
                        loading(false);  //BUTTON VISIBLE
                        showToast("User Not Found");
                    }
                });

}
private void checkMailVerification()
{
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    assert firebaseUser != null;
    if(firebaseUser.isEmailVerified())
    {
        showToast("Logged In...");
        finish();
        startActivity(new Intent(LogInActivity.this,MainActivity.class));
    }
    else
    {
        binding.buttonLogIn.setVisibility(View.VISIBLE);    //BUTTON VISIBLE
        showToast("Email Not Verified");
        firebaseAuth.signOut();
    }

}


    //DISPLAYING MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }
//CHECKING INPUT FIELDS
    private Boolean isValidSignInDetails()
    {
        if(binding.inputEmail.getText().toString().trim().isEmpty())
        {
            showToast("Enter Email");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString().trim()).matches())
        {
            showToast("Enter Valid Email");
            return false;
        }
        else if(binding.inputPassword.getText().toString().trim().isEmpty())
        {
            showToast("Enter Password");
            return false;
        }  else
        {
            return true;
        }
    }
    //Progress bar
    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.buttonLogIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonLogIn.setVisibility(View.VISIBLE);
        }
    }

}