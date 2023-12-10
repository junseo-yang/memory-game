package com.example.assignment3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WelcomeModel(fragment: WelcomeFragment) {
    // Constants
    private val REQUEST_KEY = "requestKey"
    private val BUNDLE_KEY = "bundleKey"
    private val NO = "No"
    private val YES = "Yes"

    // Common
    private val context = fragment.requireContext()
    private val view = fragment.requireView()
    private val parentFragmentManager = fragment.parentFragmentManager

    // View Components
    private val welcomeUsernameTextView: TextView = view.findViewById(R.id.welcomeUsername)
    private val welcomePlayButton: Button = view.findViewById(R.id.welcomePlayButton)

    fun setOnClickListeners() {
        // SetOnClickListener WelcomePlayButton
        welcomePlayButton.setOnClickListener {
            // If username has not been provided, show dialog box
            if (welcomeUsernameTextView.text.toString().isEmpty()) {
                // Set DialogInterface.OnClickListener
                val dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            // Yes Clicked
                            DialogInterface.BUTTON_POSITIVE -> {
                                saveUsernameInBundle()
                                navigateToGameFragment()
                            }

                            // No Clicked
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }

                // Show Dialog Box
                AlertDialog.Builder(context)
                    .setMessage(context.getString(R.string.welcome_username_warning))
                    .setNegativeButton(NO, dialogClickListener)
                    .setPositiveButton(YES, dialogClickListener)
                    .show()
            } else {
                saveUsernameInBundle()
                navigateToGameFragment()
            }
        }
    }

    private fun saveUsernameInBundle() {
        val result = Bundle()
        result.putString(BUNDLE_KEY, welcomeUsernameTextView.text.toString())
        parentFragmentManager.setFragmentResult(REQUEST_KEY, result)
    }

    private fun navigateToGameFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, GameFragment())
            .commit()
    }
}