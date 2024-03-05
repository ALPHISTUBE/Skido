package com.tjm.skido

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            if (FirebaseAuth.getInstance().currentUser == null){
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                finish()
            }
        }, 1500)

    }
}