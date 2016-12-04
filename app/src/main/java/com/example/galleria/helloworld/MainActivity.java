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

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener , BlankFragment2.OnFragmentInteractionListener, SocialFragment.OnFragmentInteractionListener {

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
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setForceTint(false);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_main);
        navigationAdapter.setupWithBottomNavigation(bottomNavigation);

        setUpBottomNavigationBar();

        bottomNavigation.setCurrentItem(0);
        //prepareTodoData();
        //updateUI();
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

    TextView textView;
    FragmentTransaction ft;
    AHBottomNavigation bottomNavigationView;

    public void setUpBottomNavigationBar(){
        bottomNavigationView = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
          @Override
          public boolean onTabSelected(int position, boolean wasSelected) {
              //ft = getSupportFragmentManager().beginTransaction();
              ft = getFragmentManager().beginTransaction();

              switch (position) {
                  case 0:
                      ft.replace(R.id.fragment, com.google.android.gms.maps.MapFragment.newInstance() );
                      break;
                  case 1:
                      ft.replace(R.id.fragment, BlankFragment2.newInstance());
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
/*
        bottomNavigationView.setOnNavigationItemSelectedListener(

                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        // must get fragment every time that calling menu
                        // 1 transaction per 1 commit
                        ft = getSupportFragmentManager().beginTransaction();


                        switch (item.getItemId()) {
                            case R.id.action_favorites:

                                //textView.setText("Favorites Selected");
                                ft.replace(R.id.fragment, BlankFragment.newInstance());

                                break;
                            case R.id.action_schedules:

                                //textView.setText("Schedules Selected");
                                ft.replace(R.id.fragment, BlankFragment2.newInstance());

                                break;
                            case R.id.action_music:

                                //textView.setText("Music Selected");
                                ft.replace(R.id.fragment, new BlankFragment());

                                break;
                            case R.id.action_notification:

                                //textView.setText("Music Selected");
                                ft.replace(R.id.fragment, new BlankFragment());

                                break;
                        }

                        //ft.addToBackStack(null);
                        ft.commit();

                        return true;
                    }
                }

        );
        */
        //bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
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

/*

    private void updateUI() {

        todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoAdapter = new TodoAdapter(todoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        todoRecyclerView.setLayoutManager(mLayoutManager);
        todoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        todoRecyclerView.setAdapter(todoAdapter);

        todoAdapter.notifyDataSetChanged();

    }
*/


    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        Log.d(TAG, "deleteTask: " + task);
        //updateUI();
    }

    private void prepareTodoData(){

        TodoModel todo = new TodoModel();
        todo.setTopic("One one ONE");

        TodoModel todo1 = new TodoModel();
        todo1.setTopic("Two two TWO");

        TodoModel todo2 = new TodoModel();
        todo2.setTopic("THREE Three 3");

        todoList.add(todo);
        todoList.add(todo1);
        todoList.add(todo2);

        Log.d(TAG, "prepareTodoData: " + todoList.size() );

        //todoAdapter.notifyDataSetChanged();
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
