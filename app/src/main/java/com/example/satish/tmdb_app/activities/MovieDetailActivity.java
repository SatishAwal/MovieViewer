package com.example.satish.tmdb_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.satish.tmdb_app.R;
import com.example.satish.tmdb_app.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailActivity extends AppCompatActivity {
@BindView(R.id.details_overview)
    TextView overview;
@BindView(R.id.original_language) TextView original_language;
@BindView(R.id.backdrop)
    ImageView ivBackdrop;
Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent=getIntent();
        if(intent !=null) {
         movie=(Movie) getIntent().getExtras().getSerializable("Movie");
         overview.setText(movie.getOverview());
         original_language.setText(movie.getOriginalLanguage());
         Glide.with(this)
                 .load(movie.getBackdropPath())
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(ivBackdrop);
        }
    }
}
