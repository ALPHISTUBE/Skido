package com.tjm.skido

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tjm.skido.databinding.ActivitySignUpPageBinding
import com.tjm.skido.model.UserInfo
import com.tjm.skido.utility.USER_NODE
import com.tjm.skido.utility.USER_PROFILE_FOLDER
import com.tjm.skido.utility.UploadImage

class SignUpPage : AppCompatActivity() {

    val binding by lazy {
        ActivitySignUpPageBinding.inflate(layoutInflater)
    }

    val userInfo = UserInfo()
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            UploadImage(uri, USER_PROFILE_FOLDER) {
                if (it == null) {
                } else {
                    userInfo.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.plus.setOnClickListener {
            launcher.launch("image/*")
        }


        val loginText = "<font color=#000000>Already have an account </font><font color=#1E88E5>Login </font><font color=#fff>?</font>"
        binding.login.setText(Html.fromHtml(loginText));
        binding.login.setOnClickListener {
            startActivity(Intent(this@SignUpPage, LoginActivity::class.java))
            finish()
        }

        binding.SignUpButton.setOnClickListener {
            if ((binding.name.editText?.text.toString() == "") or (binding.email.editText?.text.toString() == "") or (binding.password.editText?.text.toString() == "")) {
                Toast.makeText(
                    this@SignUpPage,
                    "Please fill the required field",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.email.editText?.text.toString(),
                    binding.password.editText?.text.toString()
                ).addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        userInfo.name = binding.name.editText?.text.toString()
                        userInfo.email = binding.email.editText?.text.toString()
                        userInfo.password = binding.password.editText?.text.toString()
                        Firebase.firestore.collection(USER_NODE)
                            .document(Firebase.auth.currentUser!!.uid).set(userInfo)
                            .addOnSuccessListener {
                                startActivity(Intent(this@SignUpPage, Navigation::class.java))
                                finish()
                            }
                    }
                }
            }
        }
    }
}