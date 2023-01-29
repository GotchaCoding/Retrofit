package org.techtown.retrofit2.naverapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.techtown.retrofit2.R


class NaverAdapter : RecyclerView.Adapter<NaverViewHolder>() {
    var items : ArrayList<NMItem> = ArrayList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.naver_item, parent, false)
        return NaverViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NaverViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    
    fun addItem_naver(item: List<NMItem>){
        val positionStart : Int = this.items.size +1
        this.items.addAll(item)
        notifyItemRangeChanged(positionStart, items.size)
    }
}

class NaverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var naverTitle: TextView
    var naverDate: TextView
    var naverUserRating: TextView
    var naverImage: ImageView
    var naverActor: TextView

    init {
        naverTitle = itemView.findViewById(R.id.naver_title)
        naverDate = itemView.findViewById(R.id.naver_pubDate)
        naverUserRating = itemView.findViewById(R.id.naver_userRating)
        naverImage = itemView.findViewById(R.id.naver_imageView)
        naverActor = itemView.findViewById(R.id.naver_actor)
    }

    fun setItem(item: NMItem) {
        naverTitle.text = item.title
        naverDate.text = item.pubDate
        naverUserRating.text = item.userRating
        Glide.with(itemView).load(item.image).into(naverImage)
        naverActor.text = item.actor
    }
}