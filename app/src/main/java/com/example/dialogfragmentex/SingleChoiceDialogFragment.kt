package com.example.dialogfragmentex

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SingleChoiceDialogFragment: DialogFragment() {

    internal lateinit var listener: SingleChoiceDialogListener
    private var selectedItemIndex: Int = -1
    private val options = arrayOf("Kotlin", "Java", "Swift", "Dart")
    interface SingleChoiceDialogListener {
        fun onOptionSelected(selectedOption: String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SingleChoiceDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement SingleChoiceDialogListener"))
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Choose Your Favorite Language")
                .setSingleChoiceItems(options, -1) { dialog, which ->
                    selectedItemIndex = which
                }
                .setPositiveButton("OK") { dialog, id ->
                    if (selectedItemIndex != -1) {
                        listener.onOptionSelected(options[selectedItemIndex])
                    }
                }
                .setNegativeButton("Cancel", null)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}