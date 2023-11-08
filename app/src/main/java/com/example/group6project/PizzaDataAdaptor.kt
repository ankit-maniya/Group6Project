package com.example.group6project


import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class PizzaDataAdaptor(private val context: Context, options: FirebaseRecyclerOptions<PizzaData>) : FirebaseRecyclerAdapter<PizzaData, PizzaDataAdaptor.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.product_details_card, parent, false))
    {
        val txtName : TextView = itemView.findViewById(R.id.pName)
        val txtSellerName : TextView = itemView.findViewById(R.id.sellerName)
        val txtPrice : TextView = itemView.findViewById(R.id.price)
        val txtDesc : TextView = itemView.findViewById(R.id.description)
        val imgPhoto : ImageView = itemView.findViewById(R.id.pImg)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: PizzaData) {
        holder.txtName.text = model.pName
        holder.txtSellerName.text = "Spiffy"
        holder.txtPrice.text = model.pPrice.toString();
        holder.txtDesc.text = model.pDesc

        val imageRef: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.pImg)
        val imageRefStr = imageRef.toString()
        Log.i("imageRefStr",imageRef.toString())
//        Glide.with(holder.imgPhoto.context).load(imageRefStr)
//            .error(R.drawable.img1).into(holder.imgPhoto)

        Glide.with(holder.imgPhoto.context)
            .load(imageRefStr)
            .error(R.drawable.img1)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // Log or handle the error here
                    Log.e("GlideError", "Image loading failed", e)

                    return false // Return false to allow the error placeholder to be displayed
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }
            })
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("dPName", model.pName)
            intent.putExtra("dPDesc", model.pDesc)
            intent.putExtra("dPSeller", "spiffy")
            intent.putExtra("dPPrice", model.pPrice.toString())
            intent.putExtra("dPImg", imageRefStr)
            context.startActivity(intent)
        }


    }
}