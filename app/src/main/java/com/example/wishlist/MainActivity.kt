package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.net.Uri

class MainActivity : AppCompatActivity(), MyAdapter.OnItemClickListener {

    private lateinit var newRecyclerView : RecyclerView
    private lateinit var newArrayList: ArrayList<WishListItems> // where we grab the Data from WishListItems

    lateinit var title : Array<String>
    lateinit var price : Array<String>
    lateinit var url : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = arrayOf(
        )

        price = arrayOf(
        )

        url = arrayOf(
        )

        newRecyclerView = findViewById(R.id.RecyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf()
        getUserData()

        // Initialize the submit button and set a click listener
        val submitButton = findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            onUserSubmit()
        }

        val adapter = MyAdapter(newArrayList)
        adapter.setOnItemClickListener(this) // Assuming 'this' refers to the MainActivity instance
        newRecyclerView.adapter = adapter


    }

    private fun getUserData() {
        for (i in title.indices) {
            val wish = WishListItems(title[i], price[i], url[i])
            newArrayList.add(wish)
        }

        newRecyclerView.adapter = MyAdapter(newArrayList)
    }

    private fun onUserSubmit() {
        val newItemTitle = findViewById<EditText>(R.id.titleInput).text.toString()
        val newItemPrice = findViewById<EditText>(R.id.priceInput).text.toString()
        val newItemUrl = findViewById<EditText>(R.id.urlInput).text.toString()

        // Check if any of the input fields is empty
        if (newItemTitle.isEmpty() || newItemPrice.isEmpty() || newItemUrl.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a new WishListItems object and add it to the list
        val newItem = WishListItems(newItemTitle, newItemPrice, newItemUrl)
        newArrayList.add(newItem)

        // Notify the adapter that the data has changed
        newRecyclerView.adapter?.notifyDataSetChanged()

        // Clear input fields
        clearInputFields()
    }

    private fun clearInputFields() {
        val titleInput = findViewById<EditText>(R.id.titleInput)
        val priceInput = findViewById<EditText>(R.id.priceInput)
        val urlInput = findViewById<EditText>(R.id.urlInput)

        titleInput.text.clear()
        priceInput.text.clear()
        urlInput.text.clear()
    }
    override fun onItemClick(url: String) {
        // Launch the URL in a web browser
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}