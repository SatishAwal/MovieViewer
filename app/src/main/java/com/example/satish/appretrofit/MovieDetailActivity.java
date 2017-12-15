package com.example.satish.appretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.satish.appretrofit.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
Movie movie;
@BindView(R.id.ivMovieBackdrop)
    ImageView ivMovieBackdrop;
@BindView(R.id.overview)
    TextView overview;
@BindView(R.id.title1) TextView title;
@BindView(R.id.subtitle1) TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_movie_detail);
        ButterKnife.bind(this);
        /*Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        //To receive data obtained from Intent ,you need to cast Movie and use getSerializable method//
       Intent intent=getIntent();
       if(intent !=null) {
           movie = (Movie) intent.getExtras().getSerializable("Movie");
           title.setText(movie.getTitle());
           subtitle.setText(movie.getReleaseDate());
           overview.setText(movie.getOverview());
           Glide.with(this)
                   .load(movie.getBackdropPath())
                   .diskCacheStrategy(DiskCacheStrategy.ALL)
                   .into(ivMovieBackdrop);
       }

    }
}
