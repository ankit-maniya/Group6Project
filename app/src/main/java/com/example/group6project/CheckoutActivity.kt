package com.example.group6project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.group6project.CartActivity.Companion.resetCartProductArray
import com.example.group6project.ProductCartAdapter.Companion.resetTotalPrice
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CheckoutActivity:AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout)
        val totalAmt = findViewById<TextView>(R.id.total_price)
        val displayTotal = intent.getStringExtra("totalAmt")
        totalAmt.text = displayTotal


        val addressEditText: EditText = findViewById(R.id.address)
        val mobileNumberEditText: EditText = findViewById(R.id.mobile)
        val cardNameEditText: EditText = findViewById(R.id.name)
        val cardExpiryDateEditText: EditText = findViewById(R.id.ExpiryDate)
        val cardEmailEditText: EditText = findViewById(R.id.email)
        val cardCardNumberEditText: EditText = findViewById(R.id.CardNumber)
        val errorMsg: TextView = findViewById(R.id.errorMsg)

        val btnSubmit = findViewById<Button>(R.id.buttonSubmit)
        btnSubmit.setOnClickListener {
            val address = addressEditText.text.toString()
            val mobileNumber = mobileNumberEditText.text.toString()
            val cardName = cardNameEditText.text.toString()
            val carExpiryDate = cardExpiryDateEditText.text.toString()
            val cardEmail = cardEmailEditText.text.toString()
            val cardNumber = cardCardNumberEditText.text.toString()

            var isValidated: Boolean = CheckOutFormValidation.submitDataToFirebase(
                address,
                mobileNumber,
                cardName,
                carExpiryDate,
                cardEmail,
                cardNumber
            )

            if(isValidated) {
                errorMsg.text = ""
                val currUser = UserSessionManager.getCurrentUserEmail().toString()
                val newOrder = Order(currUser, address, mobileNumber, cardName, carExpiryDate, cardEmail, cardNumber, displayTotal.toString())
                val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                val reference: DatabaseReference = database.reference
                val dataReference = reference.child("orders")

                val newEntryReference = dataReference.push()
                newEntryReference.setValue(newOrder)
                    .addOnSuccessListener {
                        // DocumentSnapshot added with ID: documentReference.id
                        Toast.makeText(this, "Order is added!", Toast.LENGTH_SHORT)
                            .show()
                        onClick()
                    }
                    .addOnFailureListener { e ->
                        Log.e("Firestore", "Error adding document", e)
                        // Handle errors here
                        Toast.makeText(this, "Order is not added!", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                Toast.makeText(this, "Please Enter a valid data!", Toast.LENGTH_SHORT)
                    .show()
                errorMsg.text = "Please Enter a valid data!"
            }
        }
    }

    private fun onClick() {
        resetCartProductArray()
        resetTotalPrice()
        startActivity(Intent(this@CheckoutActivity, ProductActivity::class.java))
    }
}