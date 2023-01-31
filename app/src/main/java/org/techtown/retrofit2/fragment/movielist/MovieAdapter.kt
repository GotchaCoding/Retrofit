package org.techtown.retrofit2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import org.techtown.retrofit2.listener.MovieClickListener
import org.techtown.retrofit2.response.Movie

class MovieAdapter(private val movieItemClickListener: MovieClickListener) : // 생성자에 movieItemClickListener 인터페이스 추가
    RecyclerView.Adapter<MovieViewHolder>() {
    var items: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.e(
            "log",
            "MovieAdapter onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder 실행"
        )
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(itemView, movieItemClickListener)    // movieItemClickListener
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.e("log", "MovieAdapter onBindViewHolder(holder: MovieViewHolder, position: Int) 실행")
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        Log.e("log", "MovieAdapter getItemCount(): Int 실행")
        return items.size
    }

    fun addItem(item: Movie) {
        items.add(item)
    }

    fun getItem(position: Int): Movie {
        return items[position]
    }

    fun addItems(items: List<Movie>) {
        Log.e("log", "MovieAdapter addItems 실행(items: List<Movie>)")
        val positionStart: Int = this.items.size + 1
        this.items.addAll(items)
        notifyItemRangeInserted(positionStart, items.size)
    }

    fun removeItem(item: Movie) {
        Log.e("log", "MovieAdapter  removeItem 실행(item: Movie) ")
        val positionstart = items.indexOf(item)
        removeItem(positionstart)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size - position - 1)
    }

    fun clearItems() {
        Log.e("log", "MovieAdapter clearItems() 실행")
        items.clear()
        notifyDataSetChanged()
    }

    fun favoriteItem(movie: Movie) {
        Log.e("log", "MovieAdapter favoriteItem (movie: Movie)실행")
        movie.isFavorite = movie.isFavorite.not()
        val position = items.indexOf(movie)
        notifyItemChanged(position)
//        notifyItemMoved(position, 0)
    }


}

class MovieViewHolder(
    itemView: View,
    private val movieItemClickListener: MovieClickListener
) :  //생성자에  movieItemClickListener: MovieClickListener
    RecyclerView.ViewHolder(itemView) {
    var movieName: TextView
    var movieData: TextView
    var btn_remove: Button
    var num: TextView
    var imgStar: ImageView

    init {
        movieName = itemView.findViewById(R.id.textView_adapter_movie)
        movieData = itemView.findViewById(R.id.textView2_adapter_movie)
        btn_remove = itemView.findViewById(R.id.btn_remove)
        imgStar = itemView.findViewById(R.id.img_star)
        num = itemView.findViewById(R.id.tvn_num)
    }

    fun setItem(item: Movie) {
        Log.e("log", "MovieAdapter setItem(item: Movie)실행")
        movieName.text = item.movieNm
        movieData.text = item.prdtYear + " 년도"
        btn_remove.setOnClickListener {
            movieItemClickListener.onClick(it, item)              //movieItemClickListener
        }
        imgStar.setOnClickListener {
            movieItemClickListener.onClick(it, item) //movieItemClickListener
            Log.e("log", "MovieAdapter buttonFavorite.setOnClickListener실행")
//
        }
        imgStar.isSelected = item.isFavorite
        if (item.isFavorite) {
            //favorite image resource
            imgStar.setImageResource(R.drawable.star_full)


        } else {
            //not favorite image resource
//            imgStar.setImageDrawable(ContextCompat.getDrawable(imgStar.context,R.drawable.star_non))
            imgStar.setImageResource(R.drawable.star_non)
        }
        var pos : Int = position + 1
        num.text = pos.toString()

    }

}

















