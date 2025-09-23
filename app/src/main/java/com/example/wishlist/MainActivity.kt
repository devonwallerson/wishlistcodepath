package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var adapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val itemNameEditText = findViewById<EditText>(R.id.itemNameEt)
        val itemPriceEditText = findViewById<EditText>(R.id.itemPriceEt)
        val itemUrlEditText = findViewById<EditText>(R.id.itemUrlEt)
        val submitButton = findViewById<Button>(R.id.submitBtn)
        val wishlistRecyclerView = findViewById<RecyclerView>(R.id.wishlistRv)
        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)

        adapter = WishlistAdapter(wishlistItems)
        wishlistRecyclerView.adapter = adapter
        wishlistRecyclerView.layoutManager = LinearLayoutManager(this)

        submitButton.setOnClickListener {
            val itemName = itemNameEditText.text.toString()
            val itemPrice = itemPriceEditText.text.toString()
            val itemUrl = itemUrlEditText.text.toString()

            if (itemName.isNotBlank() && itemPrice.isNotBlank() && itemUrl.isNotBlank()) {
                val newItem = WishlistItem(itemName, itemPrice, itemUrl)
                wishlistItems.add(newItem)
                adapter.notifyItemInserted(wishlistItems.size - 1)

                itemNameEditText.text.clear()
                itemPriceEditText.text.clear()
                itemUrlEditText.text.clear()
            }
        }
    }
}
