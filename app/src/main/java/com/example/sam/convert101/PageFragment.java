package com.example.sam.convert101;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

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
            String str = getResources().getString(R.string.string_length_text);
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
            String str = getResources().getString(R.string.string_length_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else if (mPage == 8) { //Storage
            String str = getResources().getString(R.string.string_length_text);
            WebView webView = view.findViewById(R.id.wv_unit_description);
            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }else {
        }
        return view;
    }
}
