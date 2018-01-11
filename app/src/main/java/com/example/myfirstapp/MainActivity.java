package com.example.myfirstapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    // Title of the list area
    TextView listTitle;

    // Toolbar
    Toolbar toolbar;

    // TabLayout
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate list title
        listTitle = findViewById(R.id.list_title);

        // Instantiate toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Instantiate tablayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_tasks));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_shopping));

        // Set the tabs to take up whole layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Pager view for tabs
        final ViewPager viewPager = findViewById(R.id.viewPager);

        // Set up "adapter"
        final PagerNavFragment pagerAdapter = new PagerNavFragment(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        // Setting a listener
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
