package com.example.myfirstapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerNavFragment extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerNavFragment(FragmentManager fragMan, int numOfTabs) {
        super(fragMan);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TasksTabFragment();
            case 1:
                return new ShoppingTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
