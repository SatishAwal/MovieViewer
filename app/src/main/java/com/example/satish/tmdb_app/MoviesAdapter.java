package com.example.satish.tmdb_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.satish.tmdb_app.activities.MovieDetailActivity;
import com.example.satish.tmdb_app.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by satish on 12/13/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private static List<Movie> moviesList;
    private Context context;
    private int rowLayout;

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.movies_layout) LinearLayout moviesLayout;
        @BindView(R.id.movie_image) ImageView movieImage;
        @BindView(R.id.title) TextView movieTitle;
        @BindView(R.id.subtitle) TextView data;
        @BindView(R.id.description) TextView movieDescription;
        @BindView(R.id.rating) TextView rating;

        public MovieViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            Movie  movie=moviesList.get(getAdapterPosition());
            Intent intent=new Intent(view.getContext(),MovieDetailActivity.class);
            //To pass movie object ,you need to make Movie class implement Serializable or Parcelable
            intent.putExtra("Movie",movie);
            view.getContext().startActivity(intent);
        }
    }

    public MoviesAdapter(List<Movie> moviesList,int rowLayout,Context context){
        this.context=context;
        this.moviesList=moviesList;
        this.rowLayout=rowLayout;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(rowLayout,parent,false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder,int position){
        Movie movie=moviesList.get(position);
/*        holder.movieImage.setText(movie.getPosterPath());*/
        holder.movieTitle.setText(movie.getTitle());
        holder.data.setText(movie.getReleaseDate());
        holder.movieDescription.setText(movie.getOverview());
        holder.rating.setText(movie.getVoteAverage().toString());
        Glide.with(context)
                .load(movie.getPosterPath())//get url from Movie object
                .diskCacheStrategy(DiskCacheStrategy.ALL)//used to cache the image such that no glitch occurs
                .into(holder.movieImage);//display it into Imageview

    }
    @Override
    public int getItemCount(){
        return moviesList.size();
    }
}