package com.example.group6project

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.products)
        val recyclView = findViewById<RecyclerView>(R.id.recycler_view_products)
        recyclView.layoutManager = LinearLayoutManager(this)

        // Initialize your PizzaList with sample data (e.g., 4 different Pizzas)
        val pizzaList: MutableList<Pizza> = ArrayList()
        pizzaList.add(
            Pizza(
                "Pizza 1",
                "Dominos",
                R.drawable.img1,
                "$49.99",
                "This is my first Demo Product. I hope you will like!. if you like it please share it with your friends and Happy Pizza Day from Me!"
            )
        )
        pizzaList.add(
            Pizza(
                "Pizza 2",
                "Dominos",
                R.drawable.img2,
                "$59.99",
                "This is my first Demo Product. I hope you will like!. if you like it please share it with your friends and Happy Pizza Day from Me!"
            )
        )
        pizzaList.add(
            Pizza(
                "Pizza 3",
                "Dominos",
                R.drawable.img3,
                "$100.99",
                "This is my first Demo Product. I hope you will like!. if you like it please share it with your friends and Happy Pizza Day from Me!"
            )
        )
        pizzaList.add(
            Pizza(
                "Pizza 4",
                "Dominos",
                R.drawable.img4,
                "$99.99",
                "This is my first Demo Product. I hope you will like!. if you like it please share it with your friends and Happy Pizza Day from Me!"
            )
        )
        val productAdapter = ProductAdapter(pizzaList, this)
        recyclView.adapter = productAdapter
        val btnViewCart = findViewById<Button>(R.id.btnCart)
        btnViewCart.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this@ProductActivity,
                    CartActivity::class.java
                )
            )
        }
    }
}