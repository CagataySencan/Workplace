package com.csencan.sepetim.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.csencan.sepetim.R
import com.csencan.sepetim.models.base.BaseResponse
import com.csencan.sepetim.models.base.User
import com.google.gson.Gson

object Util {
    fun createUnsuccessfullResponseDialog(context : Context, title : String?, message : String?) : AlertDialog {
        // TODO alert dialogların tasarımı düzenlenecek
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        if(message == null) {
            builder.setMessage(title)
            builder.setTitle(R.string.warning)
        }

        if (message == null && title == null) {
            builder.setTitle(R.string.warning)
            builder.setMessage(R.string.unknown_error)
        }

        builder.setPositiveButton(R.string.ok) { dialog, which ->
            dialog.dismiss()
        }
        return builder.create()
    }
    // TODO setAlertTextViews için genel bir fonksiyon yazmayı dene
}