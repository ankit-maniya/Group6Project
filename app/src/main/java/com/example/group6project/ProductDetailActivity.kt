package com.example.group6project

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)
        val title = intent.getStringExtra("dPName")
        val sellerName = intent.getStringExtra("dPSeller")
        val productImage = intent.getIntExtra("dPImg", R.drawable.img1)
        val description = intent.getStringExtra("dPDesc")
        val pPrice = intent.getStringExtra("dPPrice")
        val titleView = findViewById<TextView>(R.id.name1)
        val sellerNameView = findViewById<TextView>(R.id.sellerName1)
        val productImageView = findViewById<ImageView>(R.id.productImage1)
        val priceView = findViewById<TextView>(R.id.price1)
        val descriptionView = findViewById<TextView>(R.id.description1)
        titleView.text = title
        sellerNameView.text = sellerName
        productImageView.setImageResource(productImage)
        descriptionView.text = description
        priceView.text = pPrice
        val btnAddToCart = findViewById<Button>(R.id.addToCart)
        btnAddToCart.setOnClickListener {
            if (CartActivity.checkProductExists(title)) {
                Toast.makeText(this, "Product is Already added!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (title != null) {
                if (pPrice != null) {
                    Toast.makeText(this, "Product added to Cart!", Toast.LENGTH_SHORT)
                        .show()
                    CartActivity.addCartProduct(title, pPrice)
                }
            }
        }
    }
}