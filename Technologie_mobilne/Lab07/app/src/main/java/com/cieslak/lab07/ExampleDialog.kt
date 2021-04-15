package com.cieslak.lab07

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

class ExampleDialog(toast: Toast, snackbar: Snackbar) : DialogFragment() {

    val toast = toast
    val snackbar = snackbar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(getActivity())

        builder.setMessage("Powiadomienie z interakcja")
            .setPositiveButton("Pokaż Toast") { dialog, id ->
                showToast()
            }
            .setNegativeButton("Pokaż Snackbar") { dialog, id ->
                showSnackbar()
            }

        return builder.create()
    }

    fun showToast() {
        var view = layoutInflater.inflate(R.layout.toast_view, null)
        view.findViewById<TextView>(R.id.ToastTextView).text = "Woof! So customized!"
        toast.view = view
        toast.show()
    }

    fun showSnackbar() {
        snackbar.show()
    }
}