package com.example.sahilbansal.online_open_house;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class TabsPagerAdaptor extends FragmentPagerAdapter {
    public TabsPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                enterproblems newProblem = new enterproblems();
                return newProblem;
            case 1:
                seeproblems allproblem = new seeproblems();
                return allproblem;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "New Problems";
            case 1:
                return "All prolems";
            default:
                return null;
        }
    }
}
