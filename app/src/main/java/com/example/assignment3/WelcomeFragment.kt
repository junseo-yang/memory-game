package com.example.assignment3

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class WelcomeFragment : Fragment() {
    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[WelcomeViewModel::class.java]

        // Data Binding for welcomeTitle
        viewModel.setTitle(getString(R.string.welcome_title))
        val welcomeTitle: TextView? = view?.findViewById(R.id.welcomeTitle)
        welcomeTitle?.text = viewModel.getTitle()

        // Data Binding for welcomeInstruction
        viewModel.setInstruction(getString(R.string.welcome_instruction))
        val welcomeInstruction: TextView? = view?.findViewById(R.id.welcomeInstruction)
        welcomeInstruction?.text = viewModel.getInstruction()

        // Data Binding for welcomeUsername
        viewModel.setUsernameHint(getString(R.string.welcome_username_hint))
        val welcomeUsername: EditText? = view?.findViewById(R.id.welcomeUsername)
        welcomeUsername?.hint = viewModel.getUsernameHint()

        // Data Binding for welcomePlayButton
        viewModel.setWelcomePlayButtonText(getString(R.string.welcome_play_button))
        val welcomePlayButton: Button? = view?.findViewById(R.id.welcomePlayButton)
        welcomePlayButton?.text = viewModel.getWelcomePlayButtonText()

        // SetOnClickListener on welcomePlayButton
        welcomePlayButton?.setOnClickListener {
            // Username
            if (welcomeUsername?.text.toString() == "") {
                    val dialogClickListener =
                        DialogInterface.OnClickListener { dialog, which ->
                            when (which) {
                                // on below line we are setting a click listener
                                // for our positive button
                                DialogInterface.BUTTON_POSITIVE -> {
                                    val result = Bundle()
                                    result.putString("bundleKey", welcomeUsername?.text.toString())
                                    parentFragmentManager.setFragmentResult("requestKey", result)

                                    parentFragmentManager.beginTransaction()
                                        .replace(R.id.fragmentContainer, GameFragment())
                                        .commit()
                                }

                                // on below line we are setting click listener
                                // for our negative button.
                                DialogInterface.BUTTON_NEGATIVE -> {
                                    // on below line we are dismissing our dialog box.
                                    dialog.dismiss()
                                }
                            }
                        }

                    // on below line we are creating a builder variable for our alert dialog
                    val builder: AlertDialog.Builder = AlertDialog.Builder(context)

                    // on below line we are setting message for our dialog box.
                    builder.setMessage("Username is empty. You can't record your high score. Are you sure you want to proceed?")
                        // on below line we are setting negative button
                        // and setting text to it.
                        .setNegativeButton("No", dialogClickListener)
                        // on below line we are setting positive
                        // button and setting text to it.
                        .setPositiveButton("Yes", dialogClickListener)
                        // on below line we are calling
                        // show to display our dialog.
                        .show()
            }
            else {
                val result = Bundle()
                result.putString("bundleKey", welcomeUsername?.text.toString())
                parentFragmentManager.setFragmentResult("requestKey", result)

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, GameFragment())
                    .commit()
            }
        }
    }
}