package org.techtown.retrofit2.fragment.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.techtown.retrofit2.application.Retrofit2App
import org.techtown.retrofit2.databinding.FragmentSeachmovieBinding
import org.techtown.retrofit2.model.Movie
import org.techtown.retrofit2.retrofit.RetrofitClient.Companion.retrofitNaver
import org.techtown.retrofit2.response.NMItem
import org.techtown.retrofit2.response.NaverMovie
import org.techtown.retrofit2.naverapi.SearchMovieRecyclerAdapter
import org.techtown.retrofit2.retrofit.service.MovieSearchService
import retrofit2.Call
import retrofit2.Response

class SearchMovieFragment : Fragment() {

    lateinit var binding: FragmentSeachmovieBinding

    private lateinit var adapter: SearchMovieRecyclerAdapter

    private var service = retrofitNaver.create(MovieSearchService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeachmovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {

            btnSearch.setOnClickListener {
                fetch()
            }
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SearchMovieRecyclerAdapter()
            recyclerViewNaver.adapter = adapter
            recyclerViewNaver.layoutManager = layoutManager
        }
    }

    private fun fetch() {
        service.getSearchMovies(
            Retrofit2App.clientId,
            Retrofit2App.clientSecret,
            binding.edtSearch.text.toString(),
            10
        ).enqueue(object : retrofit2.Callback<NaverMovie> {
            override fun onResponse(call: Call<NaverMovie>, response: Response<NaverMovie>) {
                val result = response.body()
                val movieList= result!!.items.map {
                    Movie(
                        it.title,
                        it.pubDate,
                        it.userRating,
                        it.director,
                        it.actor,
                        it.image
                    )
                }
                adapter.addMovie(movieList)
            }

            override fun onFailure(call: Call<NaverMovie>, t: Throwable) {
                Log.d("log", "OnFailure")
            }
        })

    }

}