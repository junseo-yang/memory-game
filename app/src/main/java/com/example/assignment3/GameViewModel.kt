package com.example.assignment3

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var gameModel: GameModel = GameModel()

    fun setTitle(title: String) {
        gameModel.title = title
    }

    fun getTitle(): String {
        return gameModel.title
    }

    fun setStartButtonText(btnStartText: String) {
        gameModel.btnStartText = btnStartText
    }

    fun getStartButtonText(): String {
        return gameModel.btnStartText
    }

    fun setGameLifeTitle(title: String) {
        gameModel.gameLifeTitle = title
    }

    fun getGameLifeTitle(): String {
        return gameModel.gameLifeTitle
    }

    fun setGameLife(life: Int) {
        gameModel.gameLife = life
    }

    fun getGameLife(): Int {
        return gameModel.gameLife
    }

    fun setGameScoreTitle(title: String) {
        gameModel.gameScoreTitle = title
    }

    fun getGameScoreTitle(): String {
        return gameModel.gameScoreTitle
    }

    fun setGameScore(score: Int) {
        gameModel.gameScore = score
    }

    fun getGameScore(): Int {
        return gameModel.gameScore
    }

    fun setGameTimerTitle(title: String) {
        gameModel.gameTimerTitle = title
    }

    fun getGameTimerTitle(): String {
        return gameModel.gameTimerTitle
    }

    fun setGameTimer(timer: Int) {
        gameModel.gameTimer = timer
    }

    fun getGameTimer(): Int {
        return gameModel.gameTimer
    }
}