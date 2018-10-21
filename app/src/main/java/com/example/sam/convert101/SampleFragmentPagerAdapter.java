package com.example.sam.convert101;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sam on 3/11/18.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final private int PAGE_COUNT = 8;
    private String[] tabTitles={};
    private Context context;



    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        Resources res = context.getResources();
        tabTitles = res.getStringArray(R.array.string_arrray_tablists);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
