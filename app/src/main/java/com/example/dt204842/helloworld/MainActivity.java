package com.example.dt204842.helloworld;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dt204842.helloworld.model.Movie;
import com.example.dt204842.helloworld.model.TodoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<TodoModel> todoList = new ArrayList<>();
    private RecyclerView todoRecyclerView;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoAdapter = new TodoAdapter(todoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        todoRecyclerView.setLayoutManager(mLayoutManager);
        todoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        todoRecyclerView.setAdapter(todoAdapter);
        prepareTodoData();

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

        todoRecyclerView = (RecyclerView) findViewById(R.id.list_todo);
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

        todoAdapter.notifyDataSetChanged();
    }

}
