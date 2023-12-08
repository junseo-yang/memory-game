package com.example.assignment3

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.TextView

class HighScoreViewModel(private var context: Context,
                         private var entireView: View) {
    private var highScoreModel: HighScoreModel = HighScoreModel()

    // High Score Data
    fun setHighScoreData(highScoreData: MutableMap<String, Int>) {
        highScoreModel.highScoreData = highScoreData
    }

    fun getHighScoreData(): MutableMap<String, Int> {
        return highScoreModel.highScoreData
    }

    fun getHighScoreDataFromSharedPreferences() {
        var highScoreSharedPreferences: SharedPreferences? = context.getSharedPreferences(
            context.getString(R.string.high_score_shared_preference),
            Context.MODE_PRIVATE
        )

        var highScoreSharedPreferencesString = highScoreSharedPreferences?.getString(
            context?.getString(R.string.high_score_shared_preference),
            ""
        )!!

        if (highScoreSharedPreferencesString.isNotEmpty()) {
            highScoreSharedPreferencesString = highScoreSharedPreferencesString.substring(1, highScoreSharedPreferencesString.length - 1)

            setHighScoreData(
                highScoreSharedPreferencesString.split(", ").associate {
                    val (key, value) = it.split("=")
                    key to value.toInt()
                }.toMutableMap()
            )
        }
    }

    fun displayHighScores() {
        getHighScoreDataFromSharedPreferences()

        var highScoreData = entireView.findViewById<TextView>(R.id.high_score_data)

        if (getHighScoreData().isEmpty()) {
            highScoreData?.text = "There's no high score data"
        } else {
            var output = ""
            for (datum in getHighScoreData()) {
                output += "${datum.key} | ${datum.value}\n"
            }
            highScoreData?.text = output
        }
    }
}