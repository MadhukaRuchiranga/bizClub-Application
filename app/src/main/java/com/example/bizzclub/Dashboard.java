package com.example.bizzclub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView hamburger;
    private ImageView news;

    private CardView cardViewAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Drawer and Navigation setup
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        hamburger = findViewById(R.id.imageView4);
        news=findViewById(R.id.lottieAnimationView);
        hamburger.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();
                // Add logout logic here
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Set user info in nav header
        View header = navigationView.getHeaderView(0);
        TextView userName = header.findViewById(R.id.nav_user_name);
        userName.setText("Madhuka Ruchiranga");

        ImageView profileImg = header.findViewById(R.id.nav_profile_image);
        profileImg.setImageResource(R.drawable.logo); // Replace with actual image

        // Marquee news text
        TextView marqueeText = findViewById(R.id.news_marquee);
        marqueeText.setSelected(true);

        // Image Slider setup
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        // Investor Card click
        CardView cardViewInvest = findViewById(R.id.investor);
        cardViewInvest.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Dashboard.this, Investers.class);
            startActivity(browserIntent);
        });

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //news Icon Working part
        news.setOnClickListener(v -> {
            Intent intent = new Intent(Dashboard.this,NewsActivity.class);
            startActivity(intent);
        });

        //About us navigation

        cardViewAbout =findViewById(R.id.aboutUs);
        cardViewAbout.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Dashboard.this, About_us.class);
            startActivity(browserIntent);
        });

        //contact us navigation

        cardViewAbout =findViewById(R.id.contactUs);
        cardViewAbout.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Dashboard.this, ContactUs.class);
            startActivity(browserIntent);
        });

    }
}
