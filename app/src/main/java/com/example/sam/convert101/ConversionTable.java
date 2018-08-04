package com.example.sam.convert101;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ConversionTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String colorSelector =
                PreferenceManager.getDefaultSharedPreferences(ConversionTable.this)
                        .getString("pref_color_scheme", "Blue");

        if (colorSelector.equals("Blue")) {
            setTheme(R.style.AppThemeBase);
        }
        else if (colorSelector.equals("Yellow")) {
            setTheme(R.style.AppThemeYellowBase);
        }
        else if (colorSelector.equals("Dark")) {
            setTheme(R.style.AppThemeDarkBase);
        }
        else {
            setTheme(R.style.AppThemeBase);
        }
        setContentView(R.layout.activity_convertion_table);


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_conv_table);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                ConversionTable.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_conv_table);
        tabLayout.setupWithViewPager(viewPager);


    }
}
