package com.example.assignment3

import android.widget.GridLayout
import com.google.android.material.button.MaterialButton

class GameModel {
    var title = ""

    var btnStartText = ""

    var gameLifeTitle = ""

    var gameLife = 3

    var gameScoreTitle = ""

    var gameScore = 0

    var gameTimerTitle = ""

    var gameTimer = 0

    var gameTileCount = 4

    var gameQuestion = ""

    var gameAnswer = ""

    var gameGrids = mutableListOf<MaterialButton>()
}