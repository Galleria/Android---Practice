package com.example.dt204842.helloworld;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dt204842.helloworld.model.TodoModel;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private static final String TAG = "TodoAdapter";
    private List<TodoModel> todoList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.task_title);
        }
    }

    public TodoAdapter(List<TodoModel> todoList){
        this.todoList = todoList;
        //Log.d(TAG, "onCreateViewHolder: " + this.todoList.size() );
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        //Log.d(TAG, "onCreateViewHolder: " + todoList.size() );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoModel todo = todoList.get(position);
        holder.title.setText(todo.getTopic());
        //Log.d(TAG, "onBindViewHolder: " + todoList.size() );
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


}
