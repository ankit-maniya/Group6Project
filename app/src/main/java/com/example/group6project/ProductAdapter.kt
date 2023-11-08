package com.example.group6project

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private val pizzaList: List<PizzaData>, private val context: Context) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_details_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val Pizza = pizzaList[position]
        holder.title.text = Pizza.pName
        holder.sellerName.text = "Spiffy"

        val imageUri = Uri.parse(Pizza.pImg)
        Glide.with(context)
            .load(imageUri)
            .into(holder.productImage)

        val pPriceString = Pizza.pPrice.toString()
        holder.pPrice.text = pPriceString
        holder.description.text = Pizza.pDesc

        // Implement the click listener for opening the DetailActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("title", Pizza.pName)
            intent.putExtra("sellerName", "Spiffy")
            intent.putExtra("productImage", Pizza.pImg)
            intent.putExtra("pPrice", pPriceString)
            intent.putExtra("description", Pizza.pDesc)
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
         val pPrice: TextView
         val description: TextView

        init {
            title = itemView.findViewById(R.id.name)
            sellerName = itemView.findViewById(R.id.sellerName)
            productImage = itemView.findViewById(R.id.productImage)
            pPrice = itemView.findViewById(R.id.pPrice)
            description = itemView.findViewById(R.id.description)
        }
    }
}