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

    // Game Score
    var gameScoreTitle = ""
    var gameScore = 0
    var gameScoreInitial = 0

    // Game Timer
    var gameTimerTitle = ""
    var gameTimer = 0
    var gameTimerInitial = 5

    // Game Tile Count
    var gameTileCount = 4
    var gameTileCountInitial = 4

    // Game Question
    var gameQuestion = ""
    var gameQuestionInitial = ""

    // Game Answer
    var gameAnswer = ""
    var gameAnswerInitial = ""

    // Game Round
    var gameRound = 1
    var gameRoundInitial = 1

    var gameGrids = mutableListOf<MaterialButton>()

    var timer: CountDownTimer? = null
}