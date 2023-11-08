package com.example.group6project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class CartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        val recyclerView = findViewById<RecyclerView>(R.id.cart_products_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val cartAdapter = ProductCartAdapter(cartProducts)
        recyclerView.adapter = cartAdapter
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            startActivity(
                Intent(
                    this@CartActivity,
                    ProductActivity::class.java
                )
            )
        }
        val btnCheckout = findViewById<Button>(R.id.checkout)
        btnCheckout.setOnClickListener {
            val intent = Intent(this@CartActivity, CheckoutActivity::class.java)
            val totalAmt = df.format(ProductCartAdapter.totalAmt) + "$"
            intent.putExtra("totalAmt", totalAmt)
            startActivity(intent)
        }
    }

    companion object {
        private var cartProducts: MutableList<ProductCart> = ArrayList()
        private val df = DecimalFormat("0.00")
        fun addCartProduct(itemName: String, price: String) {
            cartProducts.add(ProductCart(itemName, price, price.substring(1).toDouble(), 1))
        }

        @JvmStatic
        fun resetCartProductArray() {
            cartProducts = ArrayList()
        }

        fun checkProductExists(itemName: String?): Boolean {
            for (cartProduct in cartProducts) {
                if (cartProduct.itemName == itemName) {
                    return true
                }
            }
            return false
        }
    }
}