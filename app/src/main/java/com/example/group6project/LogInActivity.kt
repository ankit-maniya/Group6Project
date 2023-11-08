package com.example.group6project


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.group6project.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUplink.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.loginEmailId.text.toString()
            val password = binding.loginPasswordId.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "User Fetched successfully!", Toast.LENGTH_SHORT)
                                .show()

                            UserSessionManager.saveCurrentUserEmail()

                            startActivity(
                                Intent(
                                    this,
                                    ProductActivity::class.java
                                )
                            )
                        } else {
                            Toast.makeText(this, "User Not Found!", Toast.LENGTH_SHORT)
                                .show()
//                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
//                                .show()
                        }
                    }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_LONG).show()
            }
        }

    }
}