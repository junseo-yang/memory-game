package com.example.assignment3

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
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

    fun setTimer(view: View?) {
        var gameTimer: TextView = view!!.findViewById(R.id.gameTimer)

        gameModel.timer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                setGameTimer(getGameTimer() - 1)
                gameTimer.text = getGameTimer().toString()
            }

            override fun onFinish() {
//                lose()
            }
        }
    }

    fun getTimer(): CountDownTimer? {
        return gameModel.timer
    }

    fun win(context: Context?, view: View?) {
        var gameScore: TextView? = view?.findViewById(R.id.gameScore)
        Toast.makeText(context, "You got it!", Toast.LENGTH_SHORT).show()
        getTimer()?.cancel()
        setGameScore(getGameScore() + 10)
        gameScore?.text = getGameScore().toString()
        setGameAnswer("")
        getGameGrids()?.forEach { disableGameGrid(it) }
    }

    fun lose(context: Context?, view: View?) {
        var gameLife: TextView? = view?.findViewById(R.id.gameLife)
        Toast.makeText(context , "Not quite!", Toast.LENGTH_SHORT).show()
        getTimer()?.cancel()
        setGameLife(getGameLife() - 1)
        gameLife?.text = getGameLife().toString()
        setGameAnswer("")
        getGameGrids()?.forEach { disableGameGrid(it) }
    }

    fun startGame(lifecycleScope: LifecycleCoroutineScope, view: View?) {
        var gameTimer: TextView = view!!.findViewById(R.id.gameTimer)

        lifecycleScope.launch {
            // Set Timer
            setTimer(view)

            while (getGameLife() > 0) {
                // Disable Grids
                getGameGrids()?.forEach { disableGameGrid(it) }

                // Reset Timer
                setGameTimer(5)
                gameTimer.text = getGameTimer().toString()

                // Generate Random Question
                var question = ""
                for (i in 1..getGameTileCount()) {
                    var grid = getGameGrids()?.random()
                    question += grid?.tag
                    grid?.setBackgroundColor(Color.BLACK)
                    delay(1000)
                    grid?.setBackgroundColor(Color.WHITE)
                }
                setGameQuestion(question)

                // Enable Buttons
                getGameGrids()?.forEach { enableGameGrid(it) }

                // Let user to play
                setGameTimer(5)
                gameTimer.text = getGameTimer().toString()

                getTimer()?.start()

                delay(5100)
            }
        }
    }
}