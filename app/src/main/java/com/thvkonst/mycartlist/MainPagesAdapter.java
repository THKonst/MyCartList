package com.thvkonst.mycartlist;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagesAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NEED = 0;
    private static final int PAGE_DONE = 1;
    private static final int PAGE_BALANCE = 2;

    private String[] titles;

    public MainPagesAdapter(FragmentManager fm, Context context) {

        super(fm);
        titles = context.getResources().getStringArray(R.array.tab_title);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case PAGE_NEED:
               return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_NEED);
            case PAGE_DONE:
                return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_DONE);
            case PAGE_BALANCE:
                return ItemsFragment.createItemsFragment(ItemsFragment.TYPE_BALANCE);

                default:
                    return null;
        }

    }

    @Override
    public int getCount() {return 3;}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
