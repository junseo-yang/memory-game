/*  HighScoreViewModel.kt
    PROG3210 Assignment 3

    Revision History
        Junseo Yang, 2023-12-10: Created
 */

package com.example.assignment3

/**
 * Class for HighScoreViewModel
 * @param fragment HighScoreFragment
 */
class HighScoreViewModel(fragment: HighScoreFragment) {
    // Model
    private var highScoreModel: HighScoreModel = HighScoreModel(fragment)

    /**
     * Method for readyHighScores
     */
    fun readyHighScores() {
        // Get HighScoreData from SharedPreferences
        highScoreModel.getHighScoreDataFromSharedPreferences()

        // Display High Scores
        highScoreModel.displayHighScores()
    }
}