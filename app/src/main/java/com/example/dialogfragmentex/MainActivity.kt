package com.example.dialogfragmentex

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.dialogfragmentex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    SimpleDialogFragment.SimpleDialogListener,
    CustomInputDialogFragment.CustomInputDialogListener,
    SingleChoiceDialogFragment.SingleChoiceDialogListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Simple AlertDialog (The "Old Way")
        binding.btnSimpleDialog.setOnClickListener {
            showSimpleAlertDialog()
        }

        // 2. Simple DialogFragment
        binding.btnSimpleDialogFragment.setOnClickListener {
            SimpleDialogFragment().show(supportFragmentManager, "SimpleDialogFragment")
        }
        // 3. Custom Input DialogFragment
        binding.btnCustomDialogFragment.setOnClickListener {
            CustomInputDialogFragment().show(supportFragmentManager, "CustomInputDialogFragment")
        }
        // 4. Single Choice DialogFragment
        binding.btnChoiceDialogFragment.setOnClickListener {
            SingleChoiceDialogFragment().show(supportFragmentManager, "SingleChoiceDialogFragment")
        }
    }

    private fun showSimpleAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Simple Alert")
            .setMessage("This is the 'old' way. It does not survive screen rotation.")
            .setPositiveButton("OK") { dialog, which ->
                binding.tvResult.text = "Result: Clicked OK on the simple alert."
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // --- Implementing the Listener Interfaces ---
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        binding.tvResult.text = "Result: Clicked YES on SimpleDialogFragment."
    }
    override fun onDialogNegativeClick(dialog: DialogFragment) {
        binding.tvResult.text = "Result: Clicked NO on SimpleDialogFragment."
    }
    override fun onDialogSubmit(input: String) {
        binding.tvResult.text = "Result: You entered '${input}'."
    }
    override fun onOptionSelected(selectedOption: String) {
        binding.tvResult.text = "Result: You chose '${selectedOption}'."
    }
}