package org.techtown.retrofit2.naverapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.retrofit2.R
import org.techtown.retrofit2.response.NMItem


class SearchMovieRecyclerAdapter : RecyclerView.Adapter<SearchMovieViewHolder>() {
    var items : ArrayList<NMItem> = ArrayList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_search_movie, parent, false)
        return SearchMovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    
    fun addMovie(item: List<NMItem>){
        val positionStart : Int = this.items.size +1
        this.items.addAll(item)
        notifyItemRangeChanged(positionStart, items.size)
    }
}

class SearchMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var naverTitle: TextView
    var naverDate: TextView
    var naverUserRating: TextView
    var naverImage: ImageView
    var naverActor: TextView
    var naverDirector : TextView

    init {
        naverTitle = itemView.findViewById(R.id.naver_title)
        naverDate = itemView.findViewById(R.id.naver_pubDate)
        naverUserRating = itemView.findViewById(R.id.naver_userRating)
        naverImage = itemView.findViewById(R.id.naver_imageView)
        naverActor = itemView.findViewById(R.id.naver_actor)
        naverDirector = itemView.findViewById(R.id.naver_director)
    }

    fun setItem(item: NMItem) {
        naverTitle.text = "제목 : " + item.title
        naverDate.text = "개봉년도 : " + item.pubDate
        naverUserRating.text = "평점 : " + item.userRating
        Glide.with(itemView).load(item.image).into(naverImage)
        naverActor.text = "출연배우 : " + item.actor
        naverDirector.text = "감독 : " + item.director
    }
}