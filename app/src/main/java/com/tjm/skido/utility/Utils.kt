package com.tjm.skido.utility

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID
import javax.security.auth.callback.Callback

fun UploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit) {
    var imgUrl: String? = null
    var ruuid = UUID.randomUUID().toString()
    FirebaseStorage.getInstance().getReference(folderName).child(ruuid)
        .putFile(uri).addOnSuccessListener {
            FirebaseStorage.getInstance().getReference(folderName)
                .child(ruuid).downloadUrl.addOnSuccessListener {
                    imgUrl = it.toString()
                    callback(imgUrl)
                }
        }
}