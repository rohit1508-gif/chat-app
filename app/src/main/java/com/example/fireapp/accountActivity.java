package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class accountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private  EditText passwordEditText;
    private EditText cnfPasswordEditText;
    private Button signUpButton;
    private EditText usernameEditText;
    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cnfPasswordEditText = findViewById(R.id.cnfPasswordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signup();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.getCurrentUser();

      }
        public void signup(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String cnfPassword = cnfPasswordEditText.getText().toString();
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cnfPassword)) {
                Toast.makeText(this, "Fields are Empty", Toast.LENGTH_SHORT).show();

            }
          else if(password.equals(cnfPassword))  {
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(accountActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            mAuth.getCurrentUser();
                            FirebaseUser mUser = mAuth.getCurrentUser();
                            String userid = mUser.getUid();
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User").child(userid);

                            mRootRef = new Firebase("https://fireapp-49e03.firebaseio.com/User");
                            String username = usernameEditText.getText().toString();

                            if(mAuth.getCurrentUser() != null)
                            {
                                HashMap<String,Object> hashMap = new HashMap<>();
                                hashMap.put("username",username);
                                hashMap.put("id",userid);
                                reference.setValue(hashMap);
                               // mRootRef.child(mAuth.getUid()).child("username").setValue(username);
                            }

//                            Firebase childRef=mRootRef.child("Username");
//                            childRef.setValue(username);

                            startActivity(new Intent(accountActivity.this, Main2Activity.class));
                        }else
                        {
                            Toast.makeText(accountActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
          else
            {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }

        }

}
