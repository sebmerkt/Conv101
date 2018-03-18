package com.example.sam.convert101;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        if(mPage == 1) {
            TextView textView = (TextView) view;
//            textView.setText("Length");

            String formattedText = "This <i>is</i> a <b>test</b> of <a href='http://foo.com'>html</a>";
//             or getString(R.string.htmlFormattedText);
            textView.setText(Html.fromHtml(formattedText));

//            getString(R.string.string_length_table);
        } else if (mPage == 2) {
            TextView textView = (TextView) view;
            textView.setText("Area");
        } else if (mPage == 3) {
            TextView textView = (TextView) view;
            textView.setText("Volume");
        }else if (mPage == 4) {
            TextView textView = (TextView) view;
            textView.setText("Time");
        }else if (mPage == 5) {
            TextView textView = (TextView) view;
            textView.setText("Speed");
        }else if (mPage == 6) {
            TextView textView = (TextView) view;
            textView.setText("Mass");
        }else if (mPage == 7) {
            TextView textView = (TextView) view;
            textView.setText("Temperature");
        }else {
            TextView textView = (TextView) view;
            textView.setText("More");
        }
        return view;
    }
}
