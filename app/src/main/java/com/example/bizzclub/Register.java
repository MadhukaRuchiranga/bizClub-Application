//Package of the project
package com.example.bizzclub;

//imports for the projects

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

//register class
public class Register extends AppCompatActivity {

    //constructor
    ImageView logoImage,imageViewGoogle,imageViewFacebook,imageViewLinkedIn;
    TextView signIn;
    EditText username,email,password;
    Button registerBtn;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    //on create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //Views
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
        logoImage.setOnClickListener(v ->
                startActivity(new Intent(this, MainActivity.class)));
        //Sign In navigation
        signIn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginPage.class));
            finish();
        });

        //Google navigation
        imageViewGoogle.setOnClickListener(v ->
                openUrl("https://accounts.google.com"));
        //facebook navigation
        imageViewFacebook.setOnClickListener(v ->
                openUrl("https://facebook.com"));
        //Linked In navigation
        imageViewLinkedIn.setOnClickListener(v ->
                openUrl("https://linkedin.com"));

        //Register process implement
        registerBtn.setOnClickListener(v -> registerUser());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void registerUser() {

        String user = username.getText().toString().trim();
        String em = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(em)) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(em, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = auth.getCurrentUser().getUid();
                        UserModel model = new UserModel(user, em);
                        firestore.collection("Users")
                                .document(userId)
                                .set(model);
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Dashboard.class));
                        finish();
                    } else {
                        Toast.makeText(this,
                                task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void openUrl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
    // Firebase model
    public static class UserModel {
        public String username;
        public String email;
        public UserModel() {}
        public UserModel(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }

}
