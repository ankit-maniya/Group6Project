package com.example.group6project

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        val title = intent.getStringExtra("title")
        val sellerName = intent.getStringExtra("sellerName")
        val productImage = intent.getIntExtra("productImage", R.drawable.spiffy_logo)
        val description = intent.getStringExtra("description")
        val price = intent.getStringExtra("price")
        val titleView = findViewById<TextView>(R.id.name1)
        val sellerNameView = findViewById<TextView>(R.id.sellerName1)
        val productImageView = findViewById<ImageView>(R.id.productImage1)
        val priceView = findViewById<TextView>(R.id.price1)
        val descriptionView = findViewById<TextView>(R.id.description1)
        titleView.text = title
        sellerNameView.text = sellerName
        productImageView.setImageResource(productImage)
        descriptionView.text = description
        priceView.text = price
        val btnAddToCart = findViewById<Button>(R.id.addToCart)
        btnAddToCart.setOnClickListener { v: View? ->
            if (CartActivity.checkProductExists(title)) {
                return@setOnClickListener
            }
            if (title != null) {
                if (price != null) {
                    CartActivity.addCartProduct(title, price)
                }
            }
        }
    }
}