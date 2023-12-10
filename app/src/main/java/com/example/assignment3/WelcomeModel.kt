package com.example.assignment3

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

class WelcomeModel(private var fragment: WelcomeFragment) {
    // Constants
    private val REQUEST_KEY = "requestKey"
    private val BUNDLE_KEY = "bundleKey"
    private val NO = "No"
    private val YES = "Yes"
    private val ZERO = 0

    // Common
    private val context = fragment.requireContext()
    private val view = fragment.requireView()
    private val parentFragmentManager = fragment.parentFragmentManager

    // View Components
    private val welcomeUsernameEditText: EditText = view.findViewById(R.id.welcomeUsername)
    private val welcomePlayButton: Button = view.findViewById(R.id.welcomePlayButton)

    fun setOnClickListeners() {
        // SetOnClickListener WelcomePlayButton
        welcomePlayButton.setOnClickListener {
            // If username has not been provided, show dialog box
            if (welcomeUsernameEditText.text.toString().isEmpty()) {
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

            // Hide Keyboard
            val inputMethodManager =
                fragment.requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, ZERO)
        }
    }

    private fun saveUsernameInBundle() {
        val result = Bundle()
        result.putString(BUNDLE_KEY, welcomeUsernameEditText.text.toString())
        parentFragmentManager.setFragmentResult(REQUEST_KEY, result)
    }

    private fun navigateToGameFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, GameFragment())
            .commit()
    }
}