package com.assignment.diabetesrecords.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.fragments.FoodExerciseFragment;
import com.assignment.diabetesrecords.modules.add_doctor.DoctorFragment;
import com.assignment.diabetesrecords.modules.add_reminder.AddReminderActivity;
import com.assignment.diabetesrecords.modules.diabetes_entry.EntriesOptionsFragment;
import com.assignment.diabetesrecords.modules.graph.GraphFragment;
import com.assignment.diabetesrecords.modules.medicine_log.MedicineLogFragment;
import com.assignment.diabetesrecords.modules.my_profile.MyProfileActivity;
import com.assignment.diabetesrecords.modules.summary.SummaryFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements
        FragmentManager.OnBackStackChangedListener
        {

    /** The Drawer toggle. */
    private ActionBarDrawerToggle mDrawerToggle;

    /** The Drawer layout. */
    private DrawerLayout mDrawerLayout;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private final int[] tabIcons = {
            R.drawable.summary,
            R.drawable.entries,
            R.drawable.graph,
            R.drawable.medicine,
            R.drawable.doctor,
            R.drawable.exercisse
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewPager);



        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        initLeftSlidingMenu(); //LK: Added here..
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //=================================================
        initActionbar();

        ImageView ivMenuIcon = (ImageView) findViewById(R.id.ivMenuIcon);
        ivMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
                {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                else
                {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });



    }

    /**
     * Inits the actionbar.
     */
    private void initActionbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        LayoutInflater inflater = LayoutInflater.from(this);
        View toolbarLayout = (RelativeLayout) inflater.inflate(
                R.layout.ab_custom, null);



        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbar.addView(toolbarLayout, layoutParams);
        toolbar.bringToFront();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                super.onDrawerSlide(drawerView, slideOffset);
            }

            /** Called when a drawer has settled in a completely closed state. */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getString(R.string.app_name));
            }

            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(
                        getString(R.string.app_name));
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle
                .setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       // checkForBackStackCounter(); //LK2
                    }
                });

    }




    /**
     * Inits the left sliding menu.
     */
    public void initLeftSlidingMenu() {



        RelativeLayout RLMyProfile;
        RLMyProfile = (RelativeLayout) findViewById(R.id.RLMyProfile);
        RLMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);

                Intent in= new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(in);

            }
        });




        RelativeLayout RLEntries;
        RLEntries  = (RelativeLayout) findViewById(R.id.RLEntries);

        RLEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.closeDrawer(GravityCompat.START);
                tabLayout.getTabAt(1).select();
            }
        });



        RelativeLayout RLGraph;
        RLGraph  = (RelativeLayout) findViewById(R.id.RLGraph);

        RLGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.closeDrawer(GravityCompat.START);
                tabLayout.getTabAt(2).select();
            }
        });



        RelativeLayout RLReminder;
        RLReminder = (RelativeLayout) findViewById(R.id.RLReminder);
        RLReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);

                Intent in= new Intent(MainActivity.this, AddReminderActivity.class);
                startActivity(in);

            }
        });

    }


    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onPostCreate(android.os.Bundle)
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    private void setupTabIcons() {
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabIcons[2]);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(tabIcons[3]);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(tabIcons[4]);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(tabIcons[5]);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(SummaryFragment.newInstance(MainActivity.this), "Summary");
     //   EntriesFragment entriesFragment = EntriesFragment.newInstance(this);
        adapter.addFrag(EntriesOptionsFragment.newInstance(MainActivity.this), "Entries");
        adapter.addFrag(new GraphFragment(), "Graph");
     //   adapter.addFrag(MedicineFragment.newInstance(MainActivity.this), "Medicine");
        adapter.addFrag(MedicineLogFragment.newInstance(MainActivity.this), "Medicine");
        adapter.addFrag(DoctorFragment.newInstance(MainActivity.this), "Doctor");
        adapter.addFrag(new FoodExerciseFragment(), "Food & Exercise");
        viewPager.setAdapter(adapter);
    }




    @Override
    public void onBackStackChanged() {

    }



    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }





    public void callEntryTab()
    {

        tabLayout.getTabAt(1).select();

    }

    public void callMedicineTab()
    {

        tabLayout.getTabAt(3).select();
    }
}