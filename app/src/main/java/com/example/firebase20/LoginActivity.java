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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLgn;
    private EditText editTextEmail, editTextPass;
    private TextView textView;
    private FirebaseAuth firebaseAuth;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        if(firebaseAuth.getCurrentUser() != null){
            finish();
          startActivity(new Intent(this,profileActivity.class));
        }

        btnLgn = (Button) findViewById(R.id.btnLgn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass =(EditText) findViewById(R.id.editTextPass);
        textViewSignin =(TextView) findViewById(R.id.textViewSignin);

        btnLgn.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }
    public void login(){
        String email = editTextEmail.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please Enter Your Email Address ",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Loggin In ... ");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),profileActivity.class));
                            finish();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Please Enter Your Correct Credentials",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }

    @Override
    public void onClick(View v){
        if(v == btnLgn){
            login();
        }if(v == textViewSignin){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
