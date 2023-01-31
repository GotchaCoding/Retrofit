package org.techtown.retrofit2.fragment.movielist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.retrofit2.*
import org.techtown.retrofit2.retrofit.RetrofitClient.Companion.retrofit
import org.techtown.retrofit2.application.Retrofit2App
import org.techtown.retrofit2.databinding.FragmentOneBinding
import org.techtown.retrofit2.listener.MovieClickListener
import org.techtown.retrofit2.response.Movie
import org.techtown.retrofit2.response.ResultSearchMovies
import org.techtown.retrofit2.retrofit.service.MovieListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListFragment : Fragment() {
    lateinit var binding: FragmentOneBinding


    private var curPage = 1
    private var itemPerPage = 10
    private var mLoading = true

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var tvError: TextView

    private lateinit var adapter: MovieAdapter

    var service = retrofit.create(MovieListService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        fetch()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_one)
//        initView()
//        fetch()
//    }

    private fun initView() {
        Log.e("log", "MainActivity initView() 실행")

        recyclerView = binding.recyclerviewLayout

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
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager  //recyclerview.setLayoutManager(layoutManager)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //페이징 처리
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e(
                    "log",
                    "MainActivity  onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) 메서드 실행"
                )
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalAItem = layoutManager.itemCount
                Log.e("log2", "totalAItem : $totalAItem")
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                Log.e("log2", "lastVisibleAItem : $lastVisibleItem")
                if (!mLoading && lastVisibleItem == totalAItem - -1) {
                    mLoading = true
                    fetch()
                }
            }
        })
        tvError = binding.tvError
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
        service.getSearchMovies(
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
        Log.e("log", "MainActivity fetch() 실행")
        service.getSearchMovies(
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