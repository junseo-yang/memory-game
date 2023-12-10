package com.example.assignment3

import android.content.Context
import android.widget.TextView

class HighScoreModel(fragment: HighScoreFragment) {
    // Constants
    private val NUMBER_OF_TOP_SCORES = 3
    private val DELIMITER_COMMA_SPACE = ", "
    private val DELIMITER_EQUAL_SIGN = "="
    private val EMPTY_STRING = ""

    // Common
    private val context = fragment.requireContext()
    private val view = fragment.requireView()
    private val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.high_score_shared_preference),
        Context.MODE_PRIVATE
    )

    // View components
    private val highScoreDataTextView: TextView = view.findViewById(R.id.highScoreData)

    // High Score Data
    private var highScoreData: MutableMap<String, Int> = mutableMapOf()

    fun getHighScoreDataFromSharedPreferences() {
        var sharedPreferencesString = sharedPreferences.getString(
            context.getString(R.string.high_score_shared_preference),
            EMPTY_STRING
        ).toString()

        if (sharedPreferencesString.isNotEmpty()) {
            sharedPreferencesString = sharedPreferencesString.substring(1, sharedPreferencesString.length - 1)

            highScoreData = sharedPreferencesString.split(DELIMITER_COMMA_SPACE).associate {
                val (key, value) = it.split(DELIMITER_EQUAL_SIGN)
                key to value.toInt()
            }.toMutableMap()
        }
    }

    fun displayHighScores() {
        if (highScoreData.isNotEmpty()) {
            // Sort High Scores
            val sortedList = highScoreData.toList().sortedByDescending { (_, value) -> value}.take(NUMBER_OF_TOP_SCORES)

            // Concatenate High Score Data
            var output = EMPTY_STRING
            for (e in sortedList) {
                output += "${e.first} | ${e.second}\n\n\n"
            }

            // Display High Score Data
            highScoreDataTextView.text = output
        }
    }
}