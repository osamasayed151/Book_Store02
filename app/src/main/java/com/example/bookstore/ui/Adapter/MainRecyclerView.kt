package com.example.bookstore.ui.Adapter

import android.media.Image
import android.media.Rating
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.model.data.BookShopItem
import com.squareup.picasso.Picasso

class MainRecyclerView : RecyclerView.Adapter<MainRecyclerView.MyViewHolder>() {

    private var list: List<BookShopItem> = emptyList()
    var listener: onBookClickListener? = null

    fun setData(dataList: List<BookShopItem>) {
        this.list = dataList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.item_image)
        var mName: TextView = itemView.findViewById(R.id.item_name)
        var author: TextView = itemView.findViewById(R.id.item_author)
        var reviews: RatingBar = itemView.findViewById(R.id.item_reviews)

        fun bind(book: BookShopItem) {
            Picasso.get().load(book.iamge).into(image)
            mName.text = book.name
            author.text = book.author
            reviews.rating = book.reviews.toFloat()
            itemView.setOnClickListener {
                listener?.onBookClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book: BookShopItem = list.get(position)
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}