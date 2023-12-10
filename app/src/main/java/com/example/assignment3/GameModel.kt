package com.example.assignment3

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameModel(private var fragment: GameFragment) {
    // Constants
    private val ZERO = 0
    private val TIMER_START_AT: Long = 5000
    private val TIMER_INTERVAL: Long = 1000
    private val DELAY_INITIAL: Long = 1000
    private val DELAY_BUTTON_CLICK: Long = 100
    private val STANDARD_GAME_ROUND: Int = 3
    private val DELIMITER_COMMA_SPACE = ", "
    private val DELIMITER_EQUAL_SIGN = "="
    private val EMPTY_STRING = ""
    private val REQUEST_KEY = "requestKey"
    private val BUNDLE_KEY = "bundleKey"

    // Common
    private val context = fragment.requireContext()
    private val view = fragment.requireView()
    private val lifecycleScope = fragment.lifecycleScope
    private val parentFragmentManager = fragment.parentFragmentManager

    // View Components
    private val gameStartButton: Button = view.findViewById(R.id.gameStartButton)
    private val gameQuitButton: Button = view.findViewById(R.id.gameQuitButton)
    private val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)
    private val gameLifeTextView: TextView = view.findViewById(R.id.gameLife)
    private val gameScoreTextView: TextView = view.findViewById(R.id.gameScore)
    private val gameTimerTextView: TextView = view.findViewById(R.id.gameTimer)

    // Game
    private var gamePlaying = false

    // Game Life
    private var gameLife = 3
    private var gameLifeInitial = 3
    private var gameLifeInterval = 1

    // Game Score
    private var gameScore = 0
    private var gameScoreInitial = 0
    private val gameScoreInterval: Int
        get() = (this.gameRound / 3 + 1) * 10

    // Game Timer
    private var gameTimer = 0
    private var gameTimerInitial = 5
    private var gameTimerInterval = 1

    // Game Tile Count
    private var gameTileCount: Int = 4
    private var gameTileCountInitial = 4
    private var gameTileCountInterval = 1

    // Game Question
    private var gameQuestion = ""
    private var gameQuestionInitial = ""

    // Game Answer
    private var gameAnswer = ""
    private var gameAnswerInitial = ""

    // Game Round
    private var gameRound = 0
    private var gameRoundInitial = 0
    private var gameRoundInterval = 1

    // Timer
    private var timer = object : CountDownTimer(TIMER_START_AT, TIMER_INTERVAL) {
        var gameTimerTextView: TextView = view.findViewById(R.id.gameTimer)

        override fun onTick(millisUntilFinished: Long) {
            gameTimer -= gameTimerInterval
            gameTimerTextView.text = gameTimer.toString()
        }

        override fun onFinish() {
            lose()
        }
    }

    // Username
    private var gameUsername = ""

    // Game High Score
    private var gameHighScoreData = mutableMapOf<String, Int>()


    // Methods
    private fun win() {
        // Disable Button
        disableGameGrids()

        // Toast win message
        Toast.makeText(
            context,
            context.getString(R.string.game_win_message),
            Toast.LENGTH_SHORT
        ).show()

        // Cancel Timer
        timer.cancel()

        // Increase Game Score
        gameScore += gameScoreInterval
        gameScoreTextView.text = gameScore.toString()

        // Increase Game Round
        gameRound += gameRoundInterval

        // Increase Game Tile Count
        if (gameRound % STANDARD_GAME_ROUND == ZERO) {
            gameTileCount += gameTileCountInterval
        }

        // Reset Game Answer
        gameAnswer = gameAnswerInitial
    }

    private fun lose() {
        // Disable Button
        disableGameGrids()

        // Toast lose message
        Toast.makeText(
            fragment.requireContext(),
            fragment.requireContext().getString(R.string.game_lose_message),
            Toast.LENGTH_SHORT
        ).show()

        // Cancel Timer
        timer.cancel()

        // Decrease Game Life
        gameLife -= gameLifeInterval
        gameLifeTextView.text = gameLife.toString()

        // Reset Game Answer
        gameAnswer = gameAnswerInitial
    }

    fun disableGameGrids() {
        val gridLayout = view.findViewById<GridLayout>(R.id.gridLayout)
        for (i in ZERO until gridLayout.childCount ) {
            gridLayout.getChildAt(i).isEnabled = false
        }
    }

    private fun enableGameGrids() {
        val gridLayout = view.findViewById<GridLayout>(R.id.gridLayout)
        for (i in ZERO until gridLayout.childCount ) {
            gridLayout.getChildAt(i).isEnabled = true
        }
    }

    private fun getGameGrids(): MutableList<MaterialButton> {
        // Get GridLayout
        val gridLayout = view.findViewById<GridLayout>(R.id.gridLayout)

        var list = mutableListOf<MaterialButton>()

        // Create List of Buttons
        for (i in 0 until gridLayout.childCount) {
            list.add(gridLayout.getChildAt(i) as MaterialButton)
        }

        return list
    }

    private fun startGame() {
        // Reset Game
        reset()

        // View Components
        // Hide Start Button
        gameStartButton.visibility = View.INVISIBLE
        // Show Quit Button
        gameQuitButton.visibility = View.VISIBLE
        // Show gridLayout
        gridLayout.visibility = View.VISIBLE

        // Set gamePlaying true
        gamePlaying = true

        // Show Toast Message
        Toast.makeText(
            context,
            context.getString(R.string.game_start_message),
            Toast.LENGTH_SHORT
        ).show()

        lifecycleScope.launch {
            delay(DELAY_INITIAL)

            while (gameLife > ZERO && gamePlaying) {
                // Disable Grids
                disableGameGrids()

                // Generate Random Question
                var question = EMPTY_STRING
                for (i in 1..gameTileCount) {
                    var grid = getGameGrids().random()
                    question += grid.tag
                    grid.setBackgroundColor(Color.BLACK)
                    delay(1000)
                    grid.setBackgroundColor(Color.WHITE)
                }
                if (gamePlaying) {
                    gameQuestion = question

                    // Enable Buttons
                    enableGameGrids()

                    // Let user play
                    timer.start()

                    delay(5100)
                }
            }

            if (gamePlaying) {
                endGame()
            }
        }
    }

    private fun saveHighScore() {
        // Check the user name
        if (gameUsername.isNotEmpty()) {
            // Get High Score Data from Shared Preference
            val gameSharedPreferences = fragment.requireContext().getSharedPreferences(
                fragment.getString(R.string.high_score_shared_preference),
                Context.MODE_PRIVATE
            )

            var gameSharedPreferencesString = gameSharedPreferences.getString(
                fragment.getString(R.string.high_score_shared_preference),
                EMPTY_STRING
            ).toString()

            if (gameSharedPreferencesString.isNotEmpty()) {
                gameSharedPreferencesString = gameSharedPreferencesString.substring(1, gameSharedPreferencesString.length - 1)

                gameHighScoreData = gameSharedPreferencesString.split(DELIMITER_COMMA_SPACE).associate {
                    val (key, value) = it.split(DELIMITER_EQUAL_SIGN)
                    key to value.toInt()
                }.toMutableMap()
            }
            else {
                gameHighScoreData = mutableMapOf(gameUsername to gameScore)
            }

            // Save the score if the score is the higher/new score
            if (gameHighScoreData.contains(gameUsername)) {
                val existingScore = gameHighScoreData[gameUsername]
                if (existingScore!! < gameScore) {
                    gameHighScoreData[gameUsername] = gameScore
                }
            }
            else {
                gameHighScoreData[gameUsername] = gameScore
            }

            val editor = gameSharedPreferences.edit()
            editor.putString(fragment.getString(R.string.high_score_shared_preference), gameHighScoreData.toString())
            editor.commit()
        }
    }

    private fun endGame() {
        // Show Start Button
        gameStartButton.visibility = View.VISIBLE

        // Hide Quit Button
        gameQuitButton.visibility = View.INVISIBLE

        // Hide gridLayout
        gridLayout.visibility = View.INVISIBLE

        // Show Toast Message
        Toast.makeText(
            context,
            context.getString(R.string.game_end_message),
            Toast.LENGTH_SHORT
        ).show()

        // Cancel Timer
        timer.cancel()

        // Set gamePlaying false
        gamePlaying = false

        // Save the score if it's the high score of the user
        saveHighScore()
    }

    fun reset() {
        // Game Life
        gameLife = gameLifeInitial
        gameLifeTextView.text = gameLife.toString()

        // Game Score
        gameScore = gameScoreInitial
        gameScoreTextView.text = gameScore.toString()

        // Game Timer
        gameTimer = gameTimerInitial
        gameTimerTextView.text = gameTimer.toString()

        // Reset Game Tile count
        gameTileCount = gameTileCountInitial

        // Reset Game Question
        gameQuestion = gameQuestionInitial

        // Reset Game Answer
        gameAnswer = gameAnswerInitial

        // Reset Game Round
        gameRound = gameRoundInitial
    }

    fun setOnClickListeners() {
        // SetOnClickListener GameStartButton
        gameStartButton.setOnClickListener {
            startGame()
        }

        // SetOnClickListener GameQuitButton
        gameQuitButton.setOnClickListener {
            endGame()
        }

        // SetOnClickListener GameGridLayout
        for (i in ZERO until gridLayout.childCount ) {
            gridLayout.getChildAt(i).setOnClickListener {
                lifecycleScope.launch {
                    gameAnswer += it.tag

                    if (gameQuestion == gameAnswer)
                    {
                        win()
                    } else if (gameAnswer.length >= gameQuestion.length)
                    {
                        lose()
                    }

                    // Toggle Button Background Colour
                    it.setBackgroundColor(Color.BLACK)
                    delay(DELAY_BUTTON_CLICK)
                    it.setBackgroundColor(Color.WHITE)
                }
            }
        }
    }

    fun getUsername() {
        parentFragmentManager.setFragmentResultListener(
            REQUEST_KEY, fragment
        ) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported.
            val result = bundle.getString(BUNDLE_KEY)

            // Set GameUsername in Game Model
            gameUsername = result.toString()
        }
    }
}