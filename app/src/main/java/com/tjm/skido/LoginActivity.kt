package com.tjm.skido

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.tjm.skido.databinding.ActivityLoginBinding
import com.tjm.skido.model.UserInfo

class LoginActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.createAccountButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }

        binding.loginButton.setOnClickListener {
            if ((binding.email.editText?.text.toString() == "") or (binding.password.editText?.text.toString() == "")) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please fill the required field",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val userInfo = UserInfo(
                    binding.email.editText?.text.toString(),
                    binding.password.editText?.text.toString()
                )

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    userInfo.email!!, userInfo.password!!
                ).addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }

    }
}