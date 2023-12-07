package com.example.assignment3

import android.os.CountDownTimer
import com.google.android.material.button.MaterialButton

class GameModel {
    var title = ""

    var btnStartText = ""

    var btnQuitText = ""

    // Game Life
    var gameLifeTitle = ""
    var gameLife = 3
    var gameLifeInitial = 3
    var gameLifeInterval = 1

    // Game Score
    var gameScoreTitle = ""
    var gameScore = 0
    var gameScoreInitial = 0
    val gameScoreInterval: Int
        get() = (this.gameRound / 3 + 1) * 10

    // Game Timer
    var gameTimerTitle = ""
    var gameTimer = 0
    var gameTimerInitial = 5

    // Game Tile Count
    var gameTileCount: Int = 4
    var gameTileCountInitial = 4
    var gameTileCountInterval = 1

    // Game Question
    var gameQuestion = ""
    var gameQuestionInitial = ""

    // Game Answer
    var gameAnswer = ""
    var gameAnswerInitial = ""

    // Game Round
    var gameRound = 0
    var gameRoundInitial = 0
    var gameRoundInterval = 1

    var gameGrids = mutableListOf<MaterialButton>()

    // Timer
    var timer: CountDownTimer? = null
}