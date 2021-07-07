package com.indifferenttech.applovinads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private long backPressedTime;
    private Toast backToast;

    private MaxAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarId);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationView = findViewById(R.id.nav_viewId);

        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        int[] popular_images = { R.drawable.p_1, R.drawable.p_2,
                R.drawable.p_3,R.drawable.p_4,R.drawable.p_5,R.drawable.p_6,
                R.drawable.p_7,R.drawable.p_8,R.drawable.p_9,R.drawable.p_10,
                R.drawable.p_11,R.drawable.p_12};

        String[] popular_tile = getResources().getStringArray(R.array.Popular_title);
        String[] popular_time = getResources().getStringArray(R.array.Popular_time);
        String[] popular_view = getResources().getStringArray(R.array.Popular_views);
        String[] popular_link = getResources().getStringArray(R.array.Popular_links);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewId);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        Adapter adapter = new Adapter(getApplicationContext(),popular_images,popular_tile,popular_view,popular_time,popular_link);
        recyclerView.setAdapter(adapter);


        // Please make sure to set the mediation provider value to "max" to ensure proper functionality
        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration) {
                createBannerAd();
            }
        });

    }

    void createBannerAd() {
        adView = new MaxAdView("fc0683c604daf309", this);
        ViewGroup rootView = findViewById(android.R.id.content);
        adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM));
        rootView.addView(adView);
        // Load the ad
        adView.loadAd();
    }

    @SuppressLint("NonConstantResourceId")

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {


            case R.id.action_exitd:
                try {
                    MainActivity.this.finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Unable to Exit", Toast.LENGTH_SHORT).show();
                }

                break;

            default:
                break;

        }

        return false;
    }

    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }


}