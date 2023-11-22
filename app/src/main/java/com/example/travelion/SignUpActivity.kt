package com.example.travelion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travelion.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInTV.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.signUpButton.setOnClickListener() {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()
            val confirmPass = binding.passwordConf.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, SignInActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}