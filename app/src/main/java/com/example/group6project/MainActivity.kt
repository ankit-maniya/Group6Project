package com.example.group6project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var topAnimation: Animation? = null
    var bottomAnimation: Animation? = null
    var imageView: ImageView? = null
    var textView: TextView? = null
    var textView2: TextView? = null
    var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_top)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_bottom)
        val btnViewProducts = findViewById<Button>(R.id.btnViewButton)
        btnViewProducts.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this,
                    LogInActivity::class.java
                )
            )
        }

        // hooks
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)
        btn = findViewById(R.id.btnViewButton)

        // load animation
        imageView?.setAnimation(topAnimation)
        textView?.setAnimation(topAnimation)
        textView2?.setAnimation(topAnimation)
        btn?.setAnimation(bottomAnimation)
    }


}