package com.example.firebase20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnReg;
    private EditText editTextEmail, editTextPass;
    private TextView textViewSignup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);


        if(firebaseAuth.getCurrentUser() != null){
            finish();
            firebaseAuth.signOut();

        }

        btnReg = (Button) findViewById(R.id.btnReg);
        editTextEmail =(EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        btnReg.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    public void register(){
        String email = editTextEmail.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please Enter your Email Address",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(pass)){
            Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"User Registration Successful",Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Cannot Register User",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

    }

    @Override
    public void onClick(View v){
        if(v == btnReg){
            register();
        }

        if(v == textViewSignup){
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }
    }
}
