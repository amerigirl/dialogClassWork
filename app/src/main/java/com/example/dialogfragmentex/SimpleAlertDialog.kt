package com.example.dialogfragmentex

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment: DialogFragment() {

    internal lateinit var listener: SimpleDialogListener
    interface SimpleDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SimpleDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement SimpleDialogListener"))
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("This is a simple DialogFragment. Do you understand?")
                .setTitle("Simple Dialog")
                .setPositiveButton("Yes") { dialog, id ->
                    listener.onDialogPositiveClick(this)
                }
                .setNegativeButton("No") { dialog, id ->
                    listener.onDialogNegativeClick(this)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



}