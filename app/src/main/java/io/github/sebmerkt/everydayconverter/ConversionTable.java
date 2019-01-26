/*
    Copyright 2019 Sebastian Merkt

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package io.github.sebmerkt.everydayconverter;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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
        else if (colorSelector.equals("Red")) {
            setTheme(R.style.AppThemeRedBase);
        }
        else if (colorSelector.equals("Green")) {
            setTheme(R.style.AppThemeGreenBase);
        }
        else if (colorSelector.equals("Dark")) {
            setTheme(R.style.AppThemeDarkBase);
        }
        else {
            setTheme(R.style.AppThemeBase);
        }
        setContentView(R.layout.activity_convertion_table);


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = findViewById(R.id.vp_conv_table);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                ConversionTable.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tl_conv_table);
        tabLayout.setupWithViewPager(viewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_conversion_table_info, menu);

        String colorSelector =
                PreferenceManager.getDefaultSharedPreferences(ConversionTable.this)
                        .getString("pref_color_scheme", "Blue");

        if(colorSelector.equals("Yellow")) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_info_outline_black_24dp));
        }
        else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_info_outline_white_24dp));

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            Intent mInfoActivityIntent = new Intent(ConversionTable.this, ConversionTableInfo.class);
            ConversionTable.this.startActivity(mInfoActivityIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
