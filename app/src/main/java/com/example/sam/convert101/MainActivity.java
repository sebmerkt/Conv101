package com.example.sam.convert101;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String colorSelector =
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
                        .getString("pref_color_scheme", "Green");

        if (colorSelector.equals("Blue")) {
            setTheme(R.style.AppTheme);
        }
        else if (colorSelector.equals("Yellow")) {
            setTheme(R.style.AppThemeYellow);
        }
        else if (colorSelector.equals("Red")) {
            setTheme(R.style.AppThemeRed);
        }
        else if (colorSelector.equals("Green")) {
            setTheme(R.style.AppThemeGreen);
        }
        else if (colorSelector.equals("Dark")) {
            setTheme(R.style.AppThemeDark);
        }
        else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Conversion table
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Conversion table

        LinearLayout[] unitsButton = {findViewById(R.id.button_length),
                findViewById(R.id.button_area),
                findViewById(R.id.button_volume),
                findViewById(R.id.button_time),
                findViewById(R.id.button_speed),
                findViewById(R.id.button_temp),
                findViewById(R.id.button_weight),
                findViewById(R.id.button_storage)};

        unitsButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mLengthIntent = new Intent(MainActivity.this, ConvertUnitsLength.class);
                MainActivity.this.startActivity(mLengthIntent);
//                myIntent.putExtra("key", ""); //Optional parameters
//                MainActivity.this.startActivityForResult(myIntent, TEXT_REQUEST);
            }
        });

        unitsButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mAreaIntent = new Intent(MainActivity.this, ConvertUnitsArea.class);
                MainActivity.this.startActivity(mAreaIntent);
            }
        });

        unitsButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mVolumeIntent = new Intent(MainActivity.this, ConvertUnitsVolume.class);
                MainActivity.this.startActivity(mVolumeIntent);
            }
        });

        unitsButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mTimeIntent = new Intent(MainActivity.this, ConvertUnitsTime.class);
                MainActivity.this.startActivity(mTimeIntent);
            }
        });

        unitsButton[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mTimeIntent = new Intent(MainActivity.this, ConvertUnitsSpeed.class);
                MainActivity.this.startActivity(mTimeIntent);
            }
        });

        unitsButton[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mTimeIntent = new Intent(MainActivity.this, ConvertUnitsTemp.class);
                MainActivity.this.startActivity(mTimeIntent);
            }
        });

        unitsButton[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mTimeIntent = new Intent(MainActivity.this, ConvertUnitsWeight.class);
                MainActivity.this.startActivity(mTimeIntent);
            }
        });

        unitsButton[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent mTimeIntent = new Intent(MainActivity.this, ConvertUnitsStorage.class);
                MainActivity.this.startActivity(mTimeIntent);
            }
        });

        ImageView[] unitsImageView = {findViewById(R.id.button_iv_length),
                findViewById(R.id.button_iv_area),
                findViewById(R.id.button_iv_volume),
                findViewById(R.id.button_iv_time),
                findViewById(R.id.button_iv_speed),
                findViewById(R.id.button_iv_temp),
                findViewById(R.id.button_iv_weight),
                findViewById(R.id.button_iv_storage)};

        TextView[] unitsTextView = {findViewById(R.id.button_tv_length),
                findViewById(R.id.button_tv_area),
                findViewById(R.id.button_tv_volume),
                findViewById(R.id.button_tv_time),
                findViewById(R.id.button_tv_speed),
                findViewById(R.id.button_tv_temp),
                findViewById(R.id.button_tv_weight),
                findViewById(R.id.button_tv_storage)};

        int[] unitsDrawableWhite = {R.drawable.ic_length_ruler_white,
                R.drawable.ic_area_white,
                R.drawable.ic_volume_white,
                R.drawable.ic_clock_white,
                R.drawable.ic_speed_white,
                R.drawable.ic_temp_white,
                R.drawable.ic_weight_white,
                R.drawable.ic_storage_white};

        int[] unitsDrawableBlack = {R.drawable.ic_length_ruler,
                R.drawable.ic_area,
                R.drawable.ic_volume,
                R.drawable.ic_clock,
                R.drawable.ic_speed,
                R.drawable.ic_temp,
                R.drawable.ic_weight,
                R.drawable.ic_storage};

        if(colorSelector.equals("Blue")){
            for(int i = 0; i<unitsImageView.length; i++){
                unitsImageView[i].setBackgroundResource(unitsDrawableWhite[i]);
            }
            for(LinearLayout ll : unitsButton){
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryBlue));
            }
            for(TextView tv : unitsTextView){
                tv.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        } else if (colorSelector.equals("Yellow")) {
            for(int i = 0; i<unitsImageView.length; i++){
                unitsImageView[i].setBackgroundResource(unitsDrawableBlack[i]);
            }
            for(LinearLayout ll : unitsButton){
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryYellow));
            }
            for(TextView tv : unitsTextView){
                tv.setTextColor(ContextCompat.getColor(this, R.color.black));
            }
        } else if (colorSelector.equals("Red")) {
            for(int i = 0; i<unitsImageView.length; i++){
                unitsImageView[i].setBackgroundResource(unitsDrawableWhite[i]);
            }
            for(LinearLayout ll : unitsButton){
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryRed));
            }
            for(TextView tv : unitsTextView){
                tv.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        } else if (colorSelector.equals("Green")) {
            for(int i = 0; i<unitsImageView.length; i++){
                unitsImageView[i].setBackgroundResource(unitsDrawableWhite[i]);
            }
            for(LinearLayout ll : unitsButton){
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryGreen));
            }
            for(TextView tv : unitsTextView){
                tv.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        } else {
            for(int i = 0; i<unitsImageView.length; i++){
                unitsImageView[i].setBackgroundResource(unitsDrawableWhite[i]);
            }
            for(LinearLayout ll : unitsButton){
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            }
            for(TextView tv : unitsTextView){
                tv.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent mSettingsActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(mSettingsActivityIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_conversion_table) {
            Intent mProfilesIntent = new Intent(MainActivity.this, ConversionTable.class);
            MainActivity.this.startActivity(mProfilesIntent);
            return true;
        } else if (id == R.id.nav_settings) {
            Intent mSettingsActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(mSettingsActivityIntent);
        } else if (id == R.id.nav_info) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://sebmerkt.github.io"));
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No application can handle this request."
                        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else if (id == R.id.nav_rate) {
            //TODO: Add info
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
