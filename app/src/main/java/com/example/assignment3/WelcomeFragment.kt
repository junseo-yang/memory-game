package com.example.assignment3

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

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
        viewModel.setGoButtonText(getString(R.string.welcome_go_button))
        val welcomePlayButton: Button? = view?.findViewById(R.id.welcomePlayButton)
        welcomePlayButton?.text = viewModel.getGoButtonText()

        // SetOnClickListener on welcomePlayButton
        welcomePlayButton?.setOnClickListener { view ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, GameFragment())
                .commit()
        }
    }
}