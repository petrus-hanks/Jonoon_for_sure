package com.jonoon.clubapp.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jonoon.clubapp.controller.fragement.main_page.NavigationOneFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationTwoFragment;
import com.jonoon.clubapp.util.L;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/13 13:01
 */
public class ViewPager4SlidingDrawer extends FragmentPagerAdapter {


    private final String TAG = this.getClass().getSimpleName();

    public ViewPager4SlidingDrawer(FragmentManager fm) {
        super(fm);
        L.e(TAG, "constructor");
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new NavigationOneFragment();
                break;
//            case 1:
//                fragment = new NavigationTwoFragment();
//                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
