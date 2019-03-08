package com.example.firebase20;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileActivity extends AppCompatActivity {


    private EditText editTextName, editTextCity, editTextPhone;
    private TextView textViewInfo;
    private Button btnLogout, btnUpdate;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user =firebaseAuth.getCurrentUser();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        textViewInfo = (TextView) findViewById(R.id.textViewInfo);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        textViewInfo.setText("Welcome "+user.getEmail());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String city = editTextCity.getText().toString().trim();
                String phoneno = editTextPhone.getText().toString().trim();

                UpdateInformation updateInformation = new UpdateInformation(name,city,phoneno);
                FirebaseUser user = firebaseAuth.getCurrentUser();

                databaseReference.child(user.getUid()).setValue(updateInformation);
                Toast.makeText(getApplicationContext(),"Database Updated",Toast.LENGTH_SHORT).show();

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                firebaseAuth.signOut();
                Toast.makeText(getApplicationContext(),"Logout Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });
    }
}
