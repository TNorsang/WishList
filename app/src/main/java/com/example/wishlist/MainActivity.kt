package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList: ArrayList<WishListItems> // where we grab the Data from WishListItems


    lateinit var title : Array<String>
    lateinit var price : Array<String>
    lateinit var url : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = arrayOf(
            "DJI Mini 3 Pro",
            "DJI Mini 3",
            "DJI Mini 2",
            "DJI Mini 2 SE"
        )

        price = arrayOf(
            "$990",
            "$600",
            "$400",
            "$350"
        )

        url = arrayOf(
            "www.dji.com",
            "www.dji.com",
            "www.dji.com",
            "www.dji.com"
        )

        newRecyclerView = findViewById(R.id.RecyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf()
        getUserData()


    }

    private fun getUserData() {
        for(i in title.indices){
            val wish = WishListItems(title[i],price[i],url[i])
            newArrayList.add(wish)
        }

        newRecyclerView.adapter = MyAdapter(newArrayList)
    }
}