package com.example.firebase20;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
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
    private static final int perm =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getPerm();
        }


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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPerm(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.INTERNET},perm);
        }else{
            return;
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int code,@NonNull String[] permissions,@NonNull int grantResults[]){
        if(code == perm){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Internet Permission Granted",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"Permission Not Granted",Toast.LENGTH_SHORT).show();
            }
        }
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
                            firebaseAuth.signOut();
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
