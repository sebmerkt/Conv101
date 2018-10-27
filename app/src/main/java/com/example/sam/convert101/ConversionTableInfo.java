package com.example.sam.convert101;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

public class ConversionTableInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String colorSelector =
                PreferenceManager.getDefaultSharedPreferences(ConversionTableInfo.this)
                        .getString("pref_color_scheme", "Blue");

        if (colorSelector.equals("Blue")) {
            setTheme(R.style.AppThemeBase);
        } else if (colorSelector.equals("Yellow")) {
            setTheme(R.style.AppThemeYellowBase);
        } else if (colorSelector.equals("Red")) {
            setTheme(R.style.AppThemeRedBase);
        } else if (colorSelector.equals("Green")) {
            setTheme(R.style.AppThemeGreenBase);
        } else if (colorSelector.equals("Dark")) {
            setTheme(R.style.AppThemeDarkBase);
        } else {
            setTheme(R.style.AppThemeBase);
        }
        setContentView(R.layout.conversion_table_info);

        String str = getResources().getString(R.string.string_info_wearos_abbrev);
        WebView webView = findViewById(R.id.wv_conversion_table_info);
        webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);

    }
}
