package com.example.group6project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val pizzaList: List<Pizza>, private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_details_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val Pizza = pizzaList[position]
        holder.title.text = Pizza.title
        holder.sellerName.text = Pizza.sellerName
        holder.productImage.setImageResource(Pizza.imageSrc)
        holder.price.text = Pizza.price
        holder.description.text = Pizza.description

        // Implement the click listener for opening the DetailActivity
        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("title", Pizza.title)
            intent.putExtra("sellerName", Pizza.sellerName)
            intent.putExtra("productImage", Pizza.imageSrc)
            intent.putExtra("price", Pizza.price)
            intent.putExtra("description", Pizza.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pizzaList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val title: TextView
         val sellerName: TextView
         val productImage: ImageView
         val price: TextView
         val description: TextView

        init {
            title = itemView.findViewById(R.id.name)
            sellerName = itemView.findViewById(R.id.sellerName)
            productImage = itemView.findViewById(R.id.productImage)
            price = itemView.findViewById(R.id.price)
            description = itemView.findViewById(R.id.description)
        }
    }
}