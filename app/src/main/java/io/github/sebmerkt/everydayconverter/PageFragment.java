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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by sam on 3/11/18.
 */

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_conv_table, container, false);
        if(mPage == 1) { //Length
            String str = getResources().getString(R.string.string_length_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        } else if (mPage == 2) { //Area
            String str = getResources().getString(R.string.string_area_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        } else if (mPage == 3) { //Volume
            String str = getResources().getString(R.string.string_volume_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else if (mPage == 4) { //Time
            String str = getResources().getString(R.string.string_time_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else if (mPage == 5) { //Speed
            String str = getResources().getString(R.string.string_speed_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else if (mPage == 6) { //Temperature
            String str = getResources().getString(R.string.string_temp_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else if (mPage == 7) { //Weight
            String str = getResources().getString(R.string.string_weight_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else if (mPage == 8) { //Storage
            String str = getResources().getString(R.string.string_storage_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else {
        }
        return view;
    }
}
