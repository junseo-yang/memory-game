package com.example.assignment3

class HighScoreViewModel(fragment: HighScoreFragment) {
    private var highScoreModel: HighScoreModel = HighScoreModel(fragment)

    fun readyHighScores() {
        // Get HighScoreData from SharedPreferences
        highScoreModel.getHighScoreDataFromSharedPreferences()

        // Display High Scores
        highScoreModel.displayHighScores()
    }
}