package com.tjm.skido.utility

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import javax.security.auth.callback.Callback

fun UploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit){
    var imgUrl: String? = null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener { store ->
            store.storage.downloadUrl.addOnSuccessListener {
                imgUrl = store.toString()
                callback(imgUrl)
            }
        }
}