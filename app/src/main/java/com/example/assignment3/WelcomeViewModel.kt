/*  WelcomeViewModel.kt
    PROG3210 Assignment 3

    Revision History
        Junseo Yang, 2023-12-10: Created
 */

package com.example.assignment3

/**
 * Class for HighScoreViewModel
 * @param fragment WelcomeFragment
 */
class WelcomeViewModel(fragment: WelcomeFragment) {
    // Model
    private var welcomeModel: WelcomeModel = WelcomeModel(fragment)

    /**
     * Method for readyWelcome
     */
    fun readyWelcome() {
        // SetOnClickListeners
        welcomeModel.setOnClickListeners()
    }
}