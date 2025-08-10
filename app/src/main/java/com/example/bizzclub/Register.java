//Package of the project
package com.example.bizzclub;

//imports for the projects

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;

//register class
public class Register extends AppCompatActivity {

    //constructor
    ImageView logoImage,imageViewGoogle,imageViewFacebook,imageViewLinkedIn;
    TextView signIn;
    EditText username,email,password;
    Button registerBtn;
    DatabaseHelper db;

    //on create method

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //get the items by their ID
        db=new DatabaseHelper(this);
        registerBtn=findViewById(R.id.register);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signIn = findViewById(R.id.existAccount);
        logoImage = findViewById(R.id.logoImage);
        imageViewGoogle = findViewById(R.id.google);
        imageViewFacebook = findViewById(R.id.facebook);
        imageViewLinkedIn = findViewById(R.id.linkedIn);

        //Home  page Navigation
        logoImage.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Register.this, MainActivity.class);
            startActivity(browserIntent);
        });
        //Sign In navigation
        signIn.setOnClickListener(v1 -> {
            Intent browserIntent1 = new Intent(Register.this, LoginPage.class);
            startActivity(browserIntent1);
            finish();
        });

        //Google navigation
        imageViewGoogle.setOnClickListener(v3 -> {
            Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/v3/signin/"));
            startActivity(browserIntent3);
        });

        //facebook navigation
        imageViewFacebook.setOnClickListener(v2 -> {
            Intent browserIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/login/?locale=en_GB"));
            startActivity(browserIntent2);
        });

        //Linked In navigation
        imageViewLinkedIn.setOnClickListener(v4 -> {
            Intent browserIntent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin"));
            startActivity(browserIntent4);
        });

        //Register process implement
        registerBtn.setOnClickListener(view->{
            String user = username.getText().toString().trim();
            String em = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty()) {
                Toast.makeText(this, "Please Enter a UserName 😐", Toast.LENGTH_SHORT).show();
            } else if (em.isEmpty()) {
                Toast.makeText(this, "Please Enter an Email 😐", Toast.LENGTH_SHORT).show();
            } else if (pass.isEmpty()) {
                Toast.makeText(this, "Please Enter a Password 😐", Toast.LENGTH_SHORT).show();
            } else {
                boolean inserted = db.insertUser(user, em, pass);
                if (inserted) {
                    Toast.makeText(this, "🥳 Registered Successfully " + user, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Dashboard.class));
                    finish();
                } else {
                    Toast.makeText(this, "Registration Failed 😥", Toast.LENGTH_SHORT).show();
                }
            }

        });

        //view compact part --> put this bottom
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}
