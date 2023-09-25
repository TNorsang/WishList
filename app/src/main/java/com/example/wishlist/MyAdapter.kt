package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val wishList: ArrayList<WishListItems>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,
            parent, false)
        return MyViewHolder(itemView)

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = wishList[position]
        holder.title.text = currentItem.title
        holder.price.text = currentItem.price
        holder.url.text = currentItem.url

        // Set a click listener to open the URL when clicked
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(currentItem.url)
        }

        // Set a long click listener to remove the item (as shown in the previous response)
        holder.itemView.setOnLongClickListener {
            removeItem(position)
            true // Return true to consume the long click event
        }
    }



    override fun getItemCount(): Int {
        return wishList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.title)
        val price : TextView = itemView.findViewById(R.id.price)
        val url : TextView = itemView.findViewById(R.id.url)
    }
    fun removeItem(position: Int) {
        if (position >= 0 && position < wishList.size) {
            wishList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(url: String)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }




}