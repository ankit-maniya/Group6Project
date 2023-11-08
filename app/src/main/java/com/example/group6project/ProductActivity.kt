package com.example.group6project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class ProductActivity : AppCompatActivity() {
    private var adapter: PizzaDataAdaptor? =  null;

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.products)

        val query = FirebaseDatabase.getInstance().reference.child("products")
        val options = FirebaseRecyclerOptions.Builder<PizzaData>().setQuery(query,PizzaData::class.java).build()
        adapter = PizzaDataAdaptor(this, options)

        val rView : RecyclerView = findViewById(R.id.recycler_view_products)
        rView.layoutManager = LinearLayoutManager(this)
        rView.adapter = adapter

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

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }
}