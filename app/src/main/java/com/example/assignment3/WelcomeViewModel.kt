package com.example.assignment3

import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    private var welcomeModel: WelcomeModel = WelcomeModel()

    fun setTitle(title: String) {
        welcomeModel.title = title
    }

    fun getTitle(): String {
        return welcomeModel.title
    }

    fun setInstruction(instruction: String) {
        welcomeModel.instruction = instruction
    }

    fun getInstruction(): String {
        return welcomeModel.instruction
    }

    fun setUsernameHint(usernameHint: String) {
        welcomeModel.usernameHint = usernameHint
    }

    fun getUsernameHint(): String {
        return welcomeModel.usernameHint
    }

    fun setUsername(username: String) {
        welcomeModel.username = username
    }

    fun getUsername(): String {
        return welcomeModel.username
    }

    fun setWelcomePlayButtonText(btnGoText: String) {
        welcomeModel.btnGoText = btnGoText
    }

    fun getWelcomePlayButtonText(): String {
        return welcomeModel.btnGoText
    }
}