package com.example.group6project


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.group6project.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginlink.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            val email = binding.emailId.text.toString()
            val passwordInfo = binding.passwordId.text.toString()
            val confirmPassword = binding.repeatPasswordId.text.toString()

            if (email.isNotEmpty() && passwordInfo.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (passwordInfo == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, passwordInfo)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(this, LogInActivity::class.java);
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not Matching!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}