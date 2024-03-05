package com.tjm.skido.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.tjm.skido.HomeActivity
import com.tjm.skido.LoginActivity
import com.tjm.skido.R
import com.tjm.skido.SignUpActivity
import com.tjm.skido.databinding.FragmentProfileBinding
import com.tjm.skido.model.UserInfo
import com.tjm.skido.utility.USER_NODE

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.Edit.setOnClickListener {
            val intent = Intent(activity, SignUpActivity::class.java)
            intent.putExtra("MODE", 1)
            activity?.startActivity(intent)
            activity?.finish()
        }

        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            result ->
            val userInfo : UserInfo = result.toObject<UserInfo>()!!
            binding.username.text = userInfo.name
            binding.Bio.text = userInfo.bio
            if (!userInfo.image.isNullOrEmpty()){
                Picasso.get().load(userInfo.image?.toUri()).into(binding.profileImage)
            }
        }

    }
}