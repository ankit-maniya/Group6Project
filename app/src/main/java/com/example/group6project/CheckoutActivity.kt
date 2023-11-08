package com.example.group6project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.group6project.CartActivity.Companion.resetCartProductArray
import com.example.group6project.ProductCartAdapter.Companion.resetTotalPrice

class CheckoutActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        val totalAmt = findViewById<TextView>(R.id.total_price)
        val displayTotal = intent.getStringExtra("totalAmt")
        totalAmt.text = displayTotal
        val btnSubmit = findViewById<Button>(R.id.buttonSubmit)
        btnSubmit.setOnClickListener { v: View -> onClick() }
    }

    private fun onClick() {
        resetCartProductArray()
        resetTotalPrice()
        startActivity(Intent(this@CheckoutActivity, ProductActivity::class.java))
    }
}