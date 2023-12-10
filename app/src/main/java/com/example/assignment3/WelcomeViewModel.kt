package com.example.assignment3

class WelcomeViewModel(fragment: WelcomeFragment) {
    private var welcomeModel: WelcomeModel = WelcomeModel(fragment)

    fun readyWelcome() {
        // SetOnClickListeners
        welcomeModel.setOnClickListeners()
    }
}