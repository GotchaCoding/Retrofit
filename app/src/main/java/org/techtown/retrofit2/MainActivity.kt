package org.techtown.retrofit2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.retrofit2.RetrofitClient.Companion.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var tag = "tag"
    private var curPage = 1
    private var itemPerPage = 10
    private var mLoading = true

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var tvError: TextView

    private lateinit var adapter: MovieAdapter

    var retrofitInterface = retrofit.create(RetrofitInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        fetch()
    }

    private fun initView() {
        Log.e("log", "MainActivity initView() 실행")
        button = findViewById(R.id.btn_main)
        editText = findViewById(R.id.edittext)
        button.setOnClickListener(View.OnClickListener {
            fetchSearch()
        })
        recyclerView = findViewById(R.id.recyclerview_layout)

        /**
        제거 및 즐겨찾기기능
         */
        adapter = MovieAdapter(          //Adapter 객체생성 (생성자에 인터페이스 추가된)
            object : MovieClickListener {
                override fun onClick(v: View, movie: Movie) {
                    Log.e("log", " MainActivity  onClick(v: View, movie: Movie")
                    when (v.id) {   //패턴매칭할 변수를 괄호안에 지정. 뷰의 아이디 값 일치여부 확인
                        R.id.btn_remove -> {
                            adapter.removeItem(movie)
                        }
                        R.id.img_star -> {
                            adapter.favoriteItem(movie)
                        }
                    }
                }
            }
        )
        recyclerView.adapter = adapter    //recyclerview.setAdpater(adapter)
        val layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager  //recyclerview.setLayoutManager(layoutManager)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //페이징 처리
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e("log", "MainActivity  onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) 메서드 실행");
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalAItem = layoutManager.itemCount
                Log.e("log2" ,"totalAItem : $totalAItem")
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                Log.e("log2" ,"totalAItem : $lastVisibleItem")
                if (!mLoading && lastVisibleItem == totalAItem - 2) {
                    mLoading = true
                    fetch()
                }
            }
        })
        tvError = findViewById(R.id.tvError)
        tvError.setOnClickListener {
            onRefresh()
        }
    }

    private fun onRefresh() {
        adapter.clearItems()
        mLoading = true
        curPage = 1
        fetch()
    }

    private fun fetchSearch() {
        retrofitInterface.getSearchMovies(
            Retrofit2App.API_KEY,
            1,
            1,
            editText.text.toString()
        ).enqueue(object : Callback<ResultSearchMovies> {
            override fun onResponse(
                call: Call<ResultSearchMovies>,
                response: Response<ResultSearchMovies>
            ) {
                val result = response.body()
                val movieList: List<Movie> = result!!.movieListResult.movieList
                adapter.clearItems()
                adapter.addItems(movieList)
                tvError.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResultSearchMovies>, t: Throwable) {
                tvError.visibility = View.VISIBLE
            }
        })
    }

    private fun fetch() {
        Log.e("log" , "MainActivity fetch() 실행")
        retrofitInterface.getSearchMovies(
            Retrofit2App.API_KEY,
            curPage,
            itemPerPage,
            ""
        ).enqueue(object : Callback<ResultSearchMovies> {
            override fun onResponse(
                call: Call<ResultSearchMovies>,
                response: Response<ResultSearchMovies>
            ) {
                val result = response.body()
                val movieList: List<Movie> = result!!.movieListResult.movieList
                adapter.addItems(movieList)
                curPage += 1
                mLoading = false
                tvError.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResultSearchMovies>, t: Throwable) {
                Log.e(tag, t.message!!)
                tvError.visibility = View.VISIBLE
            }
        })
    }
}