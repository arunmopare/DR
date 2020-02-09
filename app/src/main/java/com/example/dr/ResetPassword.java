package com.example.dr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    private EditText reset_email_input;
    private Button btnBack;
    private Button btnGetpass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        auth = FirebaseAuth.getInstance();
        reset_email_input = (EditText) findViewById(R.id.email_reset);
        //btnReset = (Button) findViewById(R.id.resetpass);
        btnGetpass = (Button) findViewById(R.id.get_my_pass);
        btnBack = (Button) findViewById(R.id.btn_back);
        // progressBar = (ProgressBar) findViewById(R.id.progressBar3);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnGetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users_email=reset_email_input.getText().toString().trim();
                if(TextUtils.isEmpty(users_email))
                {
                    Toast.makeText(ResetPassword.this,"Please Enter the Email",Toast.LENGTH_SHORT).show();
                }else{
                    auth.sendPasswordResetEmail(users_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassword.this,"Please Check your Mailbox!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPassword.this,LogIn.class));
                            }
                            else {
                                String message=task.getException().getMessage();
                                Toast.makeText(ResetPassword.this,message,Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

                //                String email2 = inputEmail2.getText().toString().trim();
                //            if (!TextUtils.isEmpty(email2))
                //            {
                //                progressBar.setVisibility(View.VISIBLE);
                //                auth.sendPasswordResetEmail(email2)
                //                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                //                            @Override
                //                            public void onComplete(@NonNull Task<Void> task) {
                //                                if (task.isSuccessful()) {
                //                                    Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                //                                } else {
                //                                    Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                //                                }
                //
                //                                progressBar.setVisibility(View.GONE);
                //                            }
                //                        });
                //            }
                //            else{
                //                  Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                //                return;
                //            }

            }
        });
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String email = inputEmail.getText().toString().trim();
//
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else {
//                    progressBar.setVisibility(View.VISIBLE);
//                    auth.sendPasswordResetEmail(email)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    progressBar.setVisibility(View.GONE);
//                                }
//                            });
//                }
//
//            }
//        });
    }
}
