package com.example.galleria.helloworld;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        //Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        //myFragment = (BlankFragment) fragment;
/*
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_fragment_container, BlankFragment.newInstance());
        ft.commit();
*/


        setUpBottomNavigationBar();

        bottomNavigation.setCurrentItem(0);

        //prepareTodoData();
        //updateUI();
    }

    @Override
    public void onResume(){
        super.onResume();
        bottomNavigation.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                addTodoList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

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
                        break;
                    case 1:
                        ft.replace(R.id.fragment, CalendarFragment.newInstance());
                        break;
                    case 2:
                        ft.replace(R.id.fragment, SocialFragment.newInstance());
                        break;
                    case 3:
                        ft.replace(R.id.fragment, new BlankFragment());
                        break;
                }
                ft.commit();
                return true;
            }
        });
    }
    private void addTodoList(){
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        TodoModel todo = new TodoModel();
                        todo.setTopic( task );
                        todo.setDone(false);
                        todoList.add(todo);
                        //updateUI();
                        todoAdapter.notifyDataSetChanged();
                        Log.d(TAG, "Task to add: " + task);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
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
