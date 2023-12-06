package com.example.assignment3

import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    fun setGameTileCount(count: Int) {
        gameModel.gameTileCount = count
    }

    fun getGameTileCount(): Int {
        return gameModel.gameTileCount
    }

    fun setGameQuestion(question: String) {
        gameModel.gameQuestion = question
    }

    fun getGameQuestion(): String {
        return gameModel.gameQuestion
    }

    fun setGameAnswer(answer: String) {
        gameModel.gameAnswer = answer
    }

    fun getGameAnswer(): String {
        return gameModel.gameAnswer
    }

    fun addGameGrid(grid: MaterialButton) {
        gameModel.gameGrids.add(grid)
    }

    fun getGameGrids(): List<MaterialButton>? {
        return gameModel.gameGrids
    }

    fun enableGameGrid(grid: MaterialButton) {
        grid.isEnabled = true
    }

    fun disableGameGrid(grid: MaterialButton) {
        grid.isEnabled = false
    }

    fun startGame() {
        
    }
}