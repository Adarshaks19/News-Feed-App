package com.example.newsfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val views= LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=NewsViewHolder(views);
        views.setOnClickListener{
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])

        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem= items[position]
        holder.textView.text=currentItem.title
        holder.author.text=currentItem.author
//        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
        Picasso.get().load(currentItem.imageUrl).into(holder.image);

    }
    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

}
class NewsViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
    val textView=itemView.findViewById<TextView>(R.id.tvDescription)
    val image=itemView.findViewById<ImageView>(R.id.ivImage)
    val author = itemView.findViewById<TextView>(R.id.tvAuthor)

}
interface NewsItemClicked {
    fun onItemClicked(item:News)
}