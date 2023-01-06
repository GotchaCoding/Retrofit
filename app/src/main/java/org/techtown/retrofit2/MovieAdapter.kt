package org.techtown.retrofit2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(private val movieItemClickListener: MovieClickListener) :
    RecyclerView.Adapter<MovieViewHolder>() {
    var items: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(itemView, movieItemClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: Movie) {
        items.add(item)
    }

    fun getItem(position: Int): Movie {
        return items[position]
    }

    fun addItems(items: List<Movie>) {
        val positionStart: Int = this.items.size + 1
        this.items.addAll(items)
        notifyItemRangeInserted(positionStart, items.size)
    }

    fun removeItem(item: Movie) {
        val positionstart = items.indexOf(item)
        removeItem(positionstart)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size - position - 1)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun favoriteItem(movie: Movie) {
        movie.isFavorite = movie.isFavorite.not()
        val position = items.indexOf(movie)
        notifyItemChanged(position)
    }


}

class MovieViewHolder(itemView: View, private val movieItemClickListener: MovieClickListener) :
    RecyclerView.ViewHolder(itemView) {
    var textView: TextView
    var textView2: TextView
    var button: Button
    var buttonFavorite: Button

    init {
        textView = itemView.findViewById(R.id.textView_adapter_movie)
        textView2 = itemView.findViewById(R.id.textView2_adapter_movie)
        button = itemView.findViewById(R.id.btn_remove)
        buttonFavorite = itemView.findViewById(R.id.btn_favorite)
    }

    fun setItem(item: Movie) {
        textView.text = item.movieNm
        textView2.text = item.genreAlt + " 명"
        button.setOnClickListener {
            movieItemClickListener.onClick(it, item)
        }
        buttonFavorite.setOnClickListener {
            movieItemClickListener.onClick(it, item)
        }

        if (item.isFavorite) {
            //favorite image resource
        } else {
            //not favorite image resource
        }
    }

}







