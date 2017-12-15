    package com.example.satish.appretrofit;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.widget.Toast;

    import com.example.satish.appretrofit.adapter.MoviesAdapter;
    import com.example.satish.appretrofit.model.Movie;
    import com.example.satish.appretrofit.model.MovieResponse;
    import com.example.satish.appretrofit.rest.ApiClient;
    import com.example.satish.appretrofit.rest.ApiInterface;

    import java.util.List;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    private final static String API_KEY="fdf8a1bed92f0be5aef68a382224450c";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            //https://api.themoviedb.org/3/movie/550?api_key=fdf8a1bed92f0be5aef68a382224450c
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if(API_KEY.isEmpty()){
                Toast.makeText(this, "Please obtain api key from TMDB.com", Toast.LENGTH_SHORT).show();
            return;
            }

            final RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view_movie);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager
                    .VERTICAL,16));

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        int statusCode=response.code();
                        List<Movie> movies=response.body().getResults();

                        Log.d(TAG,"NUmber ofmovies  received: "+movies.size());
                        recyclerView.setAdapter(new MoviesAdapter(movies,R.layout.text,getApplicationContext()));
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.e(TAG,t.toString());
                    }
                });


        }
    }
