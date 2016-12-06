package com.example.galleria.helloworld.adapter;

/**
 * Created by dt204842 on 11/24/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.galleria.helloworld.R;
import com.example.galleria.helloworld.model.MapCard;
import com.example.galleria.helloworld.model.Movie;

import java.util.List;

public class MapCardAdapter extends RecyclerView.Adapter<MapCardAdapter.MyViewHolder> {

    private List<MapCard> mapCardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            /*
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
            */
        }
    }

    public MapCardAdapter(List<MapCard> mapCardList) {
        this.mapCardList = mapCardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_card_main, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MapCard map_card = mapCardList.get(position);
        /*
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        */
    }

    @Override
    public int getItemCount() {
        return mapCardList.size();
    }
}