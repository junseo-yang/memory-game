package com.example.assignment3

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val ZERO = 0
    private val DELAY_INITIAL: Long = 1000
    private val DELAY_BUTTON_CLICK: Long = 100
    private val TIMER_INTERVAL: Long = 1000
    private val TIMER_START_AT: Long = 5000
    private val STANDARD_GAME_ROUND: Int = 3
    private var gamePlaying = true
    private var gameModel: GameModel = GameModel()

    // Title
    fun setTitle(title: String) {
        gameModel.title = title
    }

    fun getTitle(): String {
        return gameModel.title
    }

    // Start Button
    fun setStartButtonText(btnStartText: String) {
        gameModel.btnStartText = btnStartText
    }

    fun getStartButtonText(): String {
        return gameModel.btnStartText
    }

    // Quit Button
    fun setQuitButtonText(btnQuitText: String) {
        gameModel.btnQuitText = btnQuitText
    }

    fun getQuitButtonText(): String {
        return gameModel.btnQuitText
    }

    // Game Life
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

    fun getGameLifeInitial(): Int {
        return gameModel.gameLifeInitial
    }

    fun getGameLifeInterval(): Int {
        return gameModel.gameLifeInterval
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

    fun getGameScoreInitial(): Int {
        return gameModel.gameScoreInitial
    }

    fun getGameScoreInterval(): Int {
        return gameModel.gameScoreInterval
    }

    // Game Timer
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

    fun getGameTimerInitial(): Int {
        return gameModel.gameTimerInitial
    }

    // Game Tile Count
    fun setGameTileCount(count: Int) {
        gameModel.gameTileCount = count
    }

    fun getGameTileCount(): Int {
        return gameModel.gameTileCount
    }

    fun getGameTileCountInitial(): Int {
        return gameModel.gameTileCountInitial
    }

    fun getGameTileCountInterval(): Int {
        return gameModel.gameTileCountInterval
    }

    // Game Question
    fun setGameQuestion(question: String) {
        gameModel.gameQuestion = question
    }

    fun getGameQuestion(): String {
        return gameModel.gameQuestion
    }

    fun getGameQuestionInitial(): String {
        return gameModel.gameQuestionInitial
    }

    // Game Answer
    fun setGameAnswer(answer: String) {
        gameModel.gameAnswer = answer
    }

    fun getGameAnswer(): String {
        return gameModel.gameAnswer
    }

    fun getGameAnswerInitial(): String {
        return gameModel.gameAnswerInitial
    }

    // Game Round
    fun setGameRound(round: Int) {
        gameModel.gameRound = round
    }

    fun getGameRound(): Int {
        return gameModel.gameRound
    }

    fun getGameRoundInitial(): Int {
        return gameModel.gameRoundInitial
    }

    fun getGameRoundInterval(): Int {
        return gameModel.gameRoundInterval
    }

    // Game Grid
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

    fun setTimer(
        context: Context?,
        entireView: View?
    ) {
        var gameTimer: TextView = entireView!!.findViewById(R.id.gameTimer)
        setGameTimer(getGameTimerInitial())

        gameModel.timer = object : CountDownTimer(TIMER_START_AT, TIMER_INTERVAL) {

            override fun onTick(millisUntilFinished: Long) {
                setGameTimer(getGameTimer() - 1)
                gameTimer.text = getGameTimer().toString()
            }

            override fun onFinish() {
                lose(context, entireView)
            }
        }
    }

    fun getTimer(): CountDownTimer? {
        return gameModel.timer
    }

    fun win(
        context: Context?,
        entireView: View?
    ) {
        var gameScore: TextView? = entireView?.findViewById(R.id.gameScore)

        // Disable Button
        getGameGrids()?.forEach { disableGameGrid(it) }

        // Toast win message
        Toast.makeText(
            context,
            context?.getString(R.string.game_win_message),
            Toast.LENGTH_SHORT
        ).show()

        // Cancel Timer
        getTimer()?.cancel()

        // Increase Game Score
        setGameScore(getGameScore() + getGameScoreInterval())
        gameScore?.text = getGameScore().toString()

        // Increase Game Round
        setGameRound(getGameRound() + getGameRoundInterval())

        // Increase Game Tile Count
        if (getGameRound() % STANDARD_GAME_ROUND == ZERO) {
            setGameTileCount(getGameTileCount() + getGameTileCountInterval())
        }

        // Reset Game Answer
        setGameAnswer(getGameAnswerInitial())
    }

    fun lose(
        context: Context?,
        entireView: View?
    ) {
        var gameLife: TextView? = entireView?.findViewById(R.id.gameLife)

        // Disable Button
        getGameGrids()?.forEach { disableGameGrid(it) }

        // Toast lose message
        Toast.makeText(
            context,
            context?.getString(R.string.game_lose_message),
            Toast.LENGTH_SHORT
        ).show()

        // Cancel Timer
        getTimer()?.cancel()

        // Decrease Game Life
        setGameLife(getGameLife() - getGameLifeInterval())
        gameLife?.text = getGameLife().toString()

        // Reset Game Answer
        setGameAnswer(getGameAnswerInitial())
    }

    fun startGame(
        lifecycleScope: LifecycleCoroutineScope,
        context: Context?,
        entireView: View?
    ) {
        var gameTimer: TextView = entireView!!.findViewById(R.id.gameTimer)

        // Reset Game
        reset(context, entireView)

        lifecycleScope.launch {
            delay(DELAY_INITIAL)

            // Set Timer
            setTimer(context, entireView)

            while (getGameLife() > ZERO && gamePlaying) {
                // Disable Grids
                getGameGrids()?.forEach { disableGameGrid(it) }

                // Set Timer
                setTimer(context, entireView)

                // Generate Random Question
                var question = ""
                for (i in 1..getGameTileCount()) {
                    var grid = getGameGrids()?.random()
                    question += grid?.tag
                    grid?.setBackgroundColor(Color.BLACK)
                    delay(1000)
                    grid?.setBackgroundColor(Color.WHITE)
                }
                if (gamePlaying) {
                    setGameQuestion(question)

                    // Enable Buttons
                    getGameGrids()?.forEach { enableGameGrid(it) }

                    // Let user play
                    getTimer()?.start()

                    delay(5100)
                }
            }

            if (gamePlaying) {
                endGame(context, entireView)
            }
        }
    }

    fun onClickGrid(
        lifecycleScope: LifecycleCoroutineScope,
        context: Context?,
        view: View,
        entireView: View?
    ) {
        lifecycleScope.launch {
            view.setBackgroundColor(Color.BLACK)

            view.let {
                setGameAnswer(getGameAnswer() + it.tag)

                if (getGameQuestion() == getGameAnswer())
                {
                    win(context, entireView)
                }
                else if (getGameAnswer().length >= getGameQuestion().length)
                {
                    lose(context, entireView)
                }
            }

            delay(DELAY_BUTTON_CLICK)

            view.setBackgroundColor(Color.WHITE)
        }
    }

    fun reset(
        context: Context?,
        entireView: View?
    ) {
        var gameStartButton: Button? = entireView?.findViewById(R.id.gameStartButton)
        var gameQuitButton: Button? = entireView?.findViewById(R.id.gameQuitButton)
        var gridLayout: GridLayout? = entireView?.findViewById(R.id.gridLayout)
        var gameLife: TextView? = entireView?.findViewById(R.id.gameLife)
        var gameScore: TextView? = entireView?.findViewById(R.id.gameScore)
        var gameTimer: TextView? = entireView?.findViewById(R.id.gameTimer)

        // Hide Start Button
        gameStartButton?.visibility = View.INVISIBLE

        // Show Quit Button
        gameQuitButton?.visibility = View.VISIBLE

        // Show gridLayout
        gridLayout?.visibility = View.VISIBLE

        // Show Toast Message
        Toast.makeText(
            context,
            context?.getString(R.string.game_start_message),
            Toast.LENGTH_SHORT
        ).show()

        // Reset Game Life
        setGameLife(getGameLifeInitial())
        gameLife?.text = getGameLife().toString()

        // Reset Game Score
        setGameScore(getGameScoreInitial())
        gameScore?.text = getGameScore().toString()

        // Reset Game Timer
        setGameTimer(getGameTimerInitial())
        gameTimer?.text = getGameTimer().toString()

        // Reset Game Tile count
        setGameTileCount(getGameTileCountInitial())

        // Reset Game Question
        setGameQuestion(getGameQuestionInitial())

        // Reset Game Answer
        setGameAnswer(getGameAnswerInitial())

        // Reset Game Round
        setGameRound(getGameRoundInitial())

        // Set gamePlaying true
        gamePlaying = true
    }

    fun endGame(
        context: Context?,
        entireView: View?
    ) {
        var gameStartButton: Button? = entireView?.findViewById(R.id.gameStartButton)
        var gameQuitButton: Button? = entireView?.findViewById(R.id.gameQuitButton)
        var gridLayout: GridLayout? = entireView?.findViewById(R.id.gridLayout)

        // Show Start Button
        gameStartButton?.visibility = View.VISIBLE

        // Hide Quit Button
        gameQuitButton?.visibility = View.INVISIBLE

        // Hide gridLayout
        gridLayout?.visibility = View.INVISIBLE

        // Show Toast Message
        Toast.makeText(
            context,
            context?.getString(R.string.game_end_message),
            Toast.LENGTH_SHORT
        ).show()

        // Cancel Timer
        getTimer()?.cancel()

        // Set gamePlaying false
        gamePlaying = false
    }
}