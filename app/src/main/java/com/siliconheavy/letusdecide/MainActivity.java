package com.siliconheavy.letusdecide;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends FragmentActivity  implements ActionBar.TabListener {

    public final static String QUANDARY = "com.siliconheavy.letusdecide.QUANDARY";
    public final static String OPTION_ONE = "com.siliconheavy.letusdecide.ONE";
    public final static String OPTION_TWO = "com.siliconheavy.letusdecide.TWO";
    public final static String OPTION_THREE = "com.siliconheavy.letusdecide.THREE";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }




    }

    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {

    }

    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());

    }

    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    return DecideFragment.newInstance("foo", "bar");
                case 1:
                    return PastDecisionFragment.newInstance("foo", "bar");
            }
            return null;
        }

        @Override
        public int getCount() {
            // Total 2 pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_make_a_decision).toUpperCase(l);
                case 1:
                    return getString(R.string.title_past_decisions).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public void decide(View view) {
        // Intent intent = new Intent(this, MakeDecisionActivity.class);
        //
        EditText quandaryET = (EditText) findViewById(R.id.quandary);
        EditText option1ET = (EditText) findViewById(R.id.option1);
        EditText option2ET = (EditText) findViewById(R.id.option2);
        EditText option3ET = (EditText) findViewById(R.id.option3);
        TextView answerLabel = (TextView) findViewById(R.id.answer_label);
        TextView answer = (TextView) findViewById(R.id.answer);
        //
        String quandary = quandaryET.getText().toString();
        String option1 = option1ET.getText().toString();
        String option2 = option2ET.getText().toString();
        String option3 = option3ET.getText().toString();
        //
        // Sanity Checks
        //
        Boolean valid = true;
        //
        if (quandary.isEmpty()) {
            valid = false;
            quandaryET.setError("You must supply a quandary");
        }
        //
        if (valid) {
            answerLabel.setVisibility(View.VISIBLE);
            answer.setText("FOOBAR!");
        }
        //
    }

    /*
     * Reset the "Make a decision!" form.
     */
    public void reset(View view) {
        List<Integer> fieldIDs = Arrays.asList(R.id.quandary, R.id.option2, R.id.option3, R.id.answer);
        for (int id : fieldIDs) {
            TextView et = (TextView) findViewById(id);
            et.setText("");
        }
        TextView answerLabel = (TextView) findViewById(R.id.answer_label);
        answerLabel.setVisibility(View.INVISIBLE);
    }

}
