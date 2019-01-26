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

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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
