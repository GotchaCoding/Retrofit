package org.techtown.retrofit2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.retrofit2.RetrofitClient.Companion.retrofit
import org.techtown.retrofit2.databinding.FragmentTwoBinding
import org.techtown.retrofit2.naverapi.NMItem
import org.techtown.retrofit2.naverapi.NaverAdapter
import org.techtown.retrofit2.naverapi.NaverMovie
import org.techtown.retrofit2.naverapi.Retrofit2App_forNaver
import org.techtown.retrofit2.naverapi.RetrofitClient_forNaver.Companion.retrofit_naver
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class TwoFragment : Fragment() {

    lateinit var binding: FragmentTwoBinding


    private lateinit var btn_seach: Button
    private lateinit var editText_search: EditText

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NaverAdapter

    var retrofitInterface = retrofit_naver.create(RetrofitInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()


    }

    private fun initView() {
        btn_seach = binding.btnFragmentNaver
        editText_search = binding.edtFragmentNaver
        btn_seach.setOnClickListener {
           fetch()
        }

        recyclerView = binding.recyclerViewNaver
        adapter = NaverAdapter()
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }

    private fun fetch() {
        retrofitInterface.naverMovies(
            Retrofit2App.clientId,
            Retrofit2App.clientSecret,
            editText_search.text.toString(),
            10
        ).enqueue(object : retrofit2.Callback<NaverMovie> {
            override fun onResponse(call: Call<NaverMovie>, response: Response<NaverMovie>) {
                val result = response.body()
                val movieList: List<NMItem> = result!!.items
                adapter.addItem_naver(movieList)
            }

            override fun onFailure(call: Call<NaverMovie>, t: Throwable) {
                Log.d("log" , "OnFailure")
            }
        })

    }

}