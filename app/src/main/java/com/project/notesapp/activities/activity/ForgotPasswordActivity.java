package com.project.notesapp.activities.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.project.notesapp.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ActivityForgotPasswordBinding binding;

        private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth =FirebaseAuth.getInstance();

    setListeners();

    }
    private void setListeners()
    {

        binding.loginPage.setOnClickListener(view ->
                startActivity( new Intent(ForgotPasswordActivity.this,LogInActivity.class)));

        binding.forgetPasswordBtn.setOnClickListener(v ->{
            if(isValidSignInDetails())
            {
                forgotPassword();
            }
        });

    }

    //LOGIN ACTIVITY
    private void forgotPassword()
    {
      loading(true);

        firebaseAuth.sendPasswordResetEmail(binding.forgetPasswordEmail.getText().toString().trim()).addOnCompleteListener(task -> {
            if(task.isSuccessful())
            {
                showToast("Recovery mail has been sent");
                finish();
                startActivity(new Intent(ForgotPasswordActivity.this,LogInActivity.class));
            }
            else
            {
                loading(false); //BUTTON VISIBLE
                showToast("Email Not Registered");
            }
        });


    }


    //Displaying MESSAGE
    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    //CHECKING FIELD DETAILS
    private Boolean isValidSignInDetails() {
        if (binding.forgetPasswordEmail.getText().toString().trim().isEmpty()) {
            showToast("Enter Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.forgetPasswordEmail.getText().toString().trim()).matches()) {
            showToast("Enter Valid Email");
            return false;
        } else {
            return true;
        }
    }
    //Progress bar
    private void loading(Boolean isLoading)
    {
        if(isLoading)
        {
            binding.forgetPasswordBtn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.forgetPasswordBtn.setVisibility(View.VISIBLE);
        }
    }
}