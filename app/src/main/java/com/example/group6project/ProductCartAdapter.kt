package com.example.group6project

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductCartAdapter(cartItems: List<ProductCart>?) : RecyclerView.Adapter<ProductCartAdapter.CartViewHolder>() {
    var al: MutableList<String> = ArrayList()

    init {
        Companion.cartItems = cartItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_card, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val CartProduct = cartItems!![position]
        holder.bind(CartProduct)
    }

    override fun getItemCount(): Int {
        return cartItems!!.size
    }

    fun updateItemCount(productId: String, newCount: Int, type: String?) {
        for (CartProduct in cartItems!!) {
            if (CartProduct.itemName == productId) {
                val amount = CartProduct.itemPrice.toDouble()
                if (newCount == 0) {
                    if (al.contains(productId)) {
                        al.remove(productId)
                        if (totalAmt > 0) {
                            totalAmt -= amount
                        }
                    }
                    CartProduct.totalAmount = 0.0
                } else {
                    val newAmount = amount * newCount
                    val oldAmount = CartProduct.totalAmount
                    if (totalAmt != 0.0 && oldAmount > amount) {
                        totalAmt -= oldAmount
                    }
                    totalAmt += newAmount
                    CartProduct.totalAmount = newAmount
                }
                Log.i("totalPrice", totalAmt.toString())
                CartProduct.quantity = newCount
                break
            }
        }
        notifyDataSetChanged()
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView
        private val productPrice: TextView
        private val productCount: TextView
        private val increaseButton: ImageView
        private val decreaseButton: ImageView

        init {
            productName = itemView.findViewById(R.id.cart_name)
            productPrice = itemView.findViewById(R.id.cart_price)
            productCount = itemView.findViewById(R.id.quantity)
            increaseButton = itemView.findViewById(R.id.btn_incr)
            decreaseButton = itemView.findViewById(R.id.btn_dec)
        }

        fun bind(CartProduct: ProductCart) {
            productName.text = CartProduct.itemName
            productPrice.text = CartProduct.itemPrice
            productCount.text = CartProduct.quantity.toString()
            if (!al.contains(CartProduct.itemName)) {
                al.add(CartProduct.itemName)
            }
            increaseButton.setOnClickListener {
                val newCount = CartProduct.quantity + 1
                updateItemCount(CartProduct.itemName, newCount, "inc")
            }
            decreaseButton.setOnClickListener {
                if (CartProduct.quantity > 0) {
                    val newCount = CartProduct.quantity - 1
                    updateItemCount(CartProduct.itemName, newCount, "deduct")
                }
            }
        }
    }

    companion object {
        private var cartItems: List<ProductCart>? = null
        var totalAmt = 0.0
            private set

        @JvmStatic
        fun resetTotalPrice() {
            totalAmt = 0.0
        }
    }
}