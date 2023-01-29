package com.csencan.sepetim.utils

import android.app.AlertDialog
import android.content.Context
import com.csencan.sepetim.R

object Util {
    fun createAlertDialogWithoutAction(context : Context, title : String, message : String) : AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton(R.string.tamam) { dialog, which ->
            dialog.dismiss()
        }
        return builder.create()
    }

}