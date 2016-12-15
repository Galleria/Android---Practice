package com.example.galleria.helloworld;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.example.galleria.helloworld.model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener ,BlankFragment2.OnFragmentInteractionListener , CalendarFragment.OnFragmentInteractionListener, SocialFragment.OnFragmentInteractionListener , MapMainFragment.OnFragmentInteractionListener,SocialCreateEventTabFragment.OnFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    private List<TodoModel> todoList = new ArrayList<>();
    private RecyclerView todoRecyclerView;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

/*
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_fragment_container, BlankFragment.newInstance());
        ft.commit();
*/

        setUpBottomNavigationBar();

    }

    @Override
    public void onResume(){
        super.onResume();
        bottomNavigation.setCurrentItem(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        getSupportActionBar().setCustomView(R.layout.custom_bar_layout_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

/*

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

        return false;
    }
*/

    FragmentTransaction ft;
    AHBottomNavigation bottomNavigation;

    public void setUpBottomNavigationBar(){

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setForceTint(false);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_main);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        setupActionBottomBar();
    }

    private void setupActionBottomBar(){

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                //ft = getSupportFragmentManager().beginTransaction();
                ft = getFragmentManager().beginTransaction();

                switch (position) {
                    case 0:
                        //com.google.android.gms.maps.MapFragment.newInstance()
                        ft.replace(R.id.fragment, MapMainFragment.newInstance() );
                        getSupportActionBar().setCustomView(R.layout.custom_bar_layout_main);
                        getSupportActionBar().show();
                        break;
                    case 1:
                        ft.replace(R.id.fragment, CalendarFragment.newInstance());
                        getSupportActionBar().setCustomView(R.layout.custom_bar_layout_calendar);
                        getSupportActionBar().show();
                        break;
                    case 2:
                        ft.replace(R.id.fragment, SocialFragment.newInstance());
                        //getSupportActionBar().hide();
                        break;
                    case 3:
                        ft.replace(R.id.fragment, new BlankFragment());
                        getSupportActionBar().show();
                        break;
                }
                ft.commit();
                return true;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBlankFragment2Interaction(String string) {

    }

    @Override
    public void onBlankFragmentInteraction(String string) {

    }
}
