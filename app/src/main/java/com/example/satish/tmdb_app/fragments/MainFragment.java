package com.example.satish.tmdb_app.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.satish.tmdb_app.MoviesAdapter;
import com.example.satish.tmdb_app.MyDividerItemDecoration;
import com.example.satish.tmdb_app.R;
import com.example.satish.tmdb_app.model.Movie;
import com.example.satish.tmdb_app.model.MovieResponse;
import com.example.satish.tmdb_app.rest.ApiClient;
import com.example.satish.tmdb_app.rest.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private static final String TAG=MainFragment.class.getSimpleName();
    private final static String API_KEY="fdf8a1bed92f0be5aef68a382224450c";
    private Context context;

    @BindView(R.id.recycler_view_movie) RecyclerView recyclerView;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
//this.getContext use instead of getContext
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this.getContext(),LinearLayoutManager
                .VERTICAL,16));
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode=response.code();
                List<Movie> movies=response.body().getResults();

                Log.d(TAG,"NUmber of movies  received: "+movies.size());
                recyclerView.setAdapter(new MoviesAdapter(movies,R.layout.item_view,getContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });


return view;
    }

    }


