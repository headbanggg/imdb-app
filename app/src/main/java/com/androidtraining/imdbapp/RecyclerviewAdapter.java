package com.androidtraining.imdbapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Mine Kandil on 27.12.2018.
 */
public class RecyclerviewAdapter extends  RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {
    List<Movie> movieList;

    public RecyclerviewAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {

        View view;
        view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_item,viewGroup,false);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvTitle.setText(movieList.get(i).getTitle());
        myViewHolder.tvYear.setText(movieList.get(i).getYear());
        myViewHolder.tvType.setText(movieList.get(i).getType());
        Glide.with(myViewHolder.imageView.getContext())
                .load(movieList.get(i).getPoster())
                .into(myViewHolder.imageView);

        //todo(1): cv onclicklistener tanımla, intent içine imdbidyi ekle
        myViewHolder.cvMovieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context =myViewHolder.cvMovieItem.getContext();
                Intent intent= new Intent(context,MovieDetailActivity.class);
                intent.putExtra("imdbid",movieList.get(i).getImdbId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        TextView tvYear;
        TextView tvType;
        CardView cvMovieItem;

        public MyViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.ivPoster);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvType =itemView.findViewById(R.id.tvType);
            cvMovieItem= itemView.findViewById(R.id.cvMovieItem);

        }
    }


}
