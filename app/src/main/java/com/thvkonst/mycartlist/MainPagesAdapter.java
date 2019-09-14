package com.thvkonst.mycartlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagesAdapter extends FragmentPagerAdapter {


    public MainPagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position ==0){
            Fragment fragment = new ItemsFragment();

            Bundle bundle = new Bundle();
            bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_NEED);

            fragment.setArguments(bundle);
            return fragment;
        } else if (position ==1){
            Fragment fragment = new ItemsFragment();

            Bundle bundle = new Bundle();
            bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_DONE);

            fragment.setArguments(bundle);
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {return 2;}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position ==0){
            return "Надо купить";
        } else if (position ==1){
            return "Уже куплено";
        }
        return null;
    }
}
