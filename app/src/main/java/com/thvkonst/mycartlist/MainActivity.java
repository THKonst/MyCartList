package com.thvkonst.mycartlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private TabLayout tablayout;
    private FloatingActionButton fab;
    private ActionMode actionMode = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pager = findViewById(R.id.viewPager);
        tablayout = findViewById(R.id.tab1);

        MainPagesAdapter adapter = new MainPagesAdapter(getSupportFragmentManager(), this);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(this);
        tablayout.setupWithViewPager(pager);

        fab = findViewById(R.id.plus_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(MainActivity.this, Add_ItemActivity.class);
                intent.putExtra(Add_ItemActivity.TYPE_KEY, ItemsFragment.TYPE_KEY);
                startActivityForResult(intent,ItemsFragment.ADD_ITEM_REQUEST_CODE);

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case MainPagesAdapter.PAGE_NEED:
                fab.show();
                break;
            case MainPagesAdapter.PAGE_DONE:
            case MainPagesAdapter.PAGE_BALANCE:
                fab.hide();
                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state){
            case ViewPager.SCROLL_STATE_IDLE:
                fab.setEnabled(true);
                break;
            case ViewPager.SCROLL_STATE_DRAGGING:
            case ViewPager.SCROLL_STATE_SETTLING:
                if (actionMode!=null){actionMode.finish();}
                fab.setEnabled(false);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for(Fragment fragment: getSupportFragmentManager ().getFragments()){
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSupportActionModeStarted(@NonNull androidx.appcompat.view.ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        fab.hide();
        actionMode = mode;
    }

    @Override
    public void onSupportActionModeFinished(@NonNull androidx.appcompat.view.ActionMode mode) {
        super.onSupportActionModeFinished(mode);
        fab.show();
        actionMode = null;
    }
}