package com.example.bizzclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginPage extends AppCompatActivity {
    ImageView imageViewFacebook,imageViewinkedIn,imageViewGoogle;
    Button loginBtn;
    DatabaseHelper db;
    EditText loginEmail,loginPassword;
    TextView newAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        imageViewGoogle = findViewById(R.id.google);
        imageViewFacebook=findViewById(R.id.facebook);
        imageViewinkedIn=findViewById(R.id.linkedIn);
        newAccount=findViewById(R.id.newAccount);
        loginBtn=findViewById(R.id.login);
        db=new DatabaseHelper(this);
        loginEmail=findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);

        //image linking park
        imageViewGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/v3/signin/"));
                startActivity(browserIntent);
            }
        });
        imageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/login/?locale=en_GB"));
                startActivity(browserIntent);
            }
        });
        imageViewinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin"));
                startActivity(browserIntent);
            }
        });
        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginPage.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        loginBtn.setOnClickListener(v-> {

            String email=loginEmail.getText().toString();
            String pass=loginPassword.getText().toString();
            if(db.checkUser(email,pass)){
                Toast.makeText(LoginPage.this,"Login Successful 🥳",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginPage.this,Dashboard.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(LoginPage.this,"Invalid credentials 🙁",Toast.LENGTH_SHORT).show();
            }
        });
        //view compact part --> put this bottom
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}