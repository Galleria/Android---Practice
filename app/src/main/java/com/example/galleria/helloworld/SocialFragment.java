package com.example.galleria.helloworld;

import android.app.ActionBar;
import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.TabLayout;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;
import java.util.List;


public class SocialFragment extends Fragment implements SocialCreateEventTabFragment.OnFragmentInteractionListener {

    View rootView;
    ActionBar ab;

    Context context;

    private FragmentTransaction ft;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    private OnFragmentInteractionListener mListener;

    public SocialFragment() {
        // Required empty public constructor
    }

    public static SocialFragment newInstance() {
        SocialFragment fragment = new SocialFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.social, container, false);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        //setupViewPager(viewPager);


        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        tabLayout.getTabAt(0).select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ft = getFragmentManager().beginTransaction();

                switch (position) {
                    case 0:
                        ft.replace(R.id.viewpager, new SocialCreateEventTabFragment().newInstance() );
                        break;
                    case 1:
                        ft.replace(R.id.viewpager, new BlankFragment().newInstance() );
                        break;
                    case 2:
                        ft.replace(R.id.viewpager, new BlankFragment2().newInstance() );
                        break;
                }
                ft.commit();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //AppCompatActivity activity = ((AppCompatActivity) getActivity());
        //activity.getSupportActionBar().hide();

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getFragmentManager() );

        //tabLayout.addView( new SocialCreateEventTabFragment().newInstance() , 0 );

        adapter.addFragment( new SocialCreateEventTabFragment().newInstance(), "Create Event");
        adapter.addFragment(new BlankFragment().newInstance(), "Join Event");
        adapter.addFragment(new BlankFragment2().newInstance(), "Friends");

        viewPager.setAdapter(adapter);

        //viewPager.g
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        //ab = ((ActionBarActivity)getActivity()).getSupportActionBar();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
