package org.techtown.retrofit2;

import static org.techtown.retrofit2.Retrofit2App.API_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String tag = "tag";
    int curPage = 1;
    int itemPerPage = 10;

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    Button button;
    EditText editText;

    private boolean mLoading = true;

    RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fetch();
    }

    private void initView() {
        button = findViewById(R.id.btn_main);
        editText = findViewById(R.id.edittext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, SelectedMovieActivity.class);
//                startActivity(i);
                fetchSearch();
            }
        });

        recyclerView = findViewById(R.id.recyclerview_layout);
        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {   //페이징 처리
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.e(tag, "onScrolled 메서드 실행");
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalAItem = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!mLoading && (lastVisibleItem == totalAItem - 3)) {
                    mLoading = true;
                    fetch();
                }
            }
        });

        tvError = findViewById(R.id.tvError);
        tvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
    }

    private void onRefresh() {
        adapter.clearItems();
        mLoading = true;
        curPage = 1;
        fetch();
    }

    private void fetchSearch() {
        retrofitInterface.getSearchMovies(
                API_KEY,
                1,
                1,
                editText.getText().toString()
        ).enqueue(new Callback<ResultSearchMovies>() {
            @Override
            public void onResponse(Call<ResultSearchMovies> call, Response<ResultSearchMovies> response) {
                ResultSearchMovies result = response.body();
                List<Movie> movieList = result.getMovieListResult().getMovieList();
               adapter.clearItems();
               adapter.addItems(movieList);
               tvError.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ResultSearchMovies> call, Throwable t) {
                tvError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void fetch() {
        retrofitInterface.getSearchMovies(
                API_KEY,
                curPage,
                itemPerPage,
                ""
        ).enqueue(new Callback<ResultSearchMovies>() {
            @Override
            public void onResponse(Call<ResultSearchMovies> call, Response<ResultSearchMovies> response) {
                ResultSearchMovies result = response.body();
                List<Movie> movieList = result.getMovieListResult().getMovieList();
                adapter.addItems(movieList);
                curPage = curPage + 1;
                mLoading = false;
                tvError.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResultSearchMovies> call, Throwable t) {
                Log.e(tag, t.getMessage());
                tvError.setVisibility(View.VISIBLE);
            }
        });
    }

}