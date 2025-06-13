package com.example.dialogfragmentex

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CustomInputDialogFragment: DialogFragment() {

    internal lateinit var listener: CustomInputDialogListener
    interface CustomInputDialogListener {
        fun onDialogSubmit(input: String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as CustomInputDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement CustomInputDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_custom_input, null)
            val editText = dialogView.findViewById<EditText>(R.id.etInput)
            builder.setView(dialogView)
                .setTitle("Custom Dialog")
                .setPositiveButton("Submit") { dialog, id ->
                    listener.onDialogSubmit(editText.text.toString())
                }
                .setNegativeButton("Cancel") { dialog, id ->
                    getDialog()?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}