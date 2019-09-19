package com.thvkonst.mycartlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.viewPager);
        tablayout = findViewById(R.id.tab1);

        MainPagesAdapter adapter = new MainPagesAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);

        tablayout.setupWithViewPager(pager);

    }
}