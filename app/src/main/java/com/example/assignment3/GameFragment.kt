package com.example.assignment3

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    // Global Variables
    private var grids = mutableListOf<MaterialButton>()
    private var gameLifeTitle: TextView? = null
    private var gameLife: TextView? = null
    private var gameScoreTitle: TextView? = null
    private var gameScore: TextView? = null
    private var gameTimerTitle: TextView? = null
    private var gameTimer: TextView? = null
    private var gameTitle: TextView? = null
    private var gameStartButton: TextView? = null
    private var gridLayout: GridLayout? = null
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // Data Binding for gameLifeTitle
        viewModel.setGameLifeTitle(getString(R.string.game_life_title))
        gameLifeTitle = view?.findViewById(R.id.gameLifeTitle)
        gameLifeTitle?.text = viewModel.getGameLifeTitle()

        // Data Binding for gameLife
        viewModel.setGameLife(getString(R.string.game_life).toInt())
        gameLife = view?.findViewById(R.id.gameLife)
        gameLife?.text = viewModel.getGameLife().toString()

        // Data Binding for gameScoreTitle
        viewModel.setGameScoreTitle(getString(R.string.game_score_title))
        gameScoreTitle = view?.findViewById(R.id.gameScoreTitle)
        gameScoreTitle?.text = viewModel.getGameScoreTitle()

        // Data Binding for gameScore
        viewModel.setGameScore(getString(R.string.game_score).toInt())
        gameScore = view?.findViewById(R.id.gameScore)
        gameScore?.text = viewModel.getGameScore().toString()

        // Data Binding for gameTimerTitle
        viewModel.setGameTimerTitle(getString(R.string.game_timer_title))
        gameTimerTitle = view?.findViewById(R.id.gameTimerTitle)
        gameTimerTitle?.text = viewModel.getGameTimerTitle()

        // Data Binding for gameTimer
        viewModel.setGameTimer(getString(R.string.game_timer).toInt())
        gameTimer = view?.findViewById(R.id.gameTimer)
        gameTimer?.text = viewModel.getGameTimer().toString()

        // Data Binding for gameTitle
        viewModel.setTitle(getString(R.string.game_title))
        gameTitle = view?.findViewById(R.id.gameTitle)
        gameTitle?.text = viewModel.getTitle()

        // Data Binding for gameStartButton
        viewModel.setStartButtonText(getString(R.string.game_start_button))
        gameStartButton = view?.findViewById(R.id.gameStartButton)
        gameStartButton?.text = viewModel.getStartButtonText()

        // SetOnClickListener on gameStartButton
        gameStartButton?.setOnClickListener {
            startGame()
        }

        // Get GridLayout
        gridLayout = view?.findViewById(R.id.gridLayout)

        // Get gridCount
        val gridCount: Int? = gridLayout?.childCount

        // Create List of Buttons
        for (i in 0 until gridCount!!) {
            grids.add(gridLayout?.getChildAt(i) as MaterialButton)
        }

        // SetOnClickListener for each grid
        for (grid in grids) {
            grid.setOnClickListener {
                onClickGrid(it)
            }
        }

        // Disable Buttons for Initialization
        disableButtons()
    }

    private fun onClickGrid(view: View) {
        lifecycleScope.launch {
            view.setBackgroundColor(Color.BLACK)

            view.let {
                viewModel.setGameAnswer(viewModel.getGameAnswer() + it.tag)

                if (viewModel.getGameQuestion() == viewModel.getGameAnswer())
                {
                    win()
                }
                else if (viewModel.getGameAnswer().length >= viewModel.getGameQuestion().length)
                {
                    lose()
                }
            }

            delay(200)

            view.setBackgroundColor(Color.WHITE)
        }
    }

    private fun enableButtons()
    {
        grids.forEach {
            it.isEnabled = true
            it.setBackgroundColor(Color.WHITE)
        }
    }

    private fun disableButtons()
    {
        grids.forEach {
            it.isEnabled = false
        }
    }

    private fun startGame()
    {
        lifecycleScope.launch {
            timer = object: CountDownTimer(5000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    viewModel.setGameTimer(viewModel.getGameTimer() - 1)
                    gameTimer?.text = viewModel.getGameTimer().toString()
                }

                override fun onFinish() {
                    lose()
                }
            }
            while (viewModel.getGameLife() > 0) {
                // Disable Buttons
                disableButtons()

                // Reset Timer
                viewModel.setGameTimer(5)
                gameTimer?.text = viewModel.getGameTimer().toString()

                // Generate Random Question
                var question = ""
                for (i in 1..viewModel.getGameTileCount()) {
                    var grid = grids.random()
                    question += grid.tag
                    grid.setBackgroundColor(Color.BLACK)
                    delay(1000)
                    grid.setBackgroundColor(Color.WHITE)
                }
                viewModel.setGameQuestion(question)

                // Enable Buttons
                enableButtons()

                // Let user to play
                viewModel.setGameTimer(5)
                gameTimer?.text = viewModel.getGameTimer().toString()

                (timer as CountDownTimer).start()

                delay(5100)
            }
        }
    }

    private fun win() {
        Toast.makeText(context , "You got it!", Toast.LENGTH_SHORT).show()
        timer?.cancel()
        viewModel.setGameScore(viewModel.getGameScore() + 10)
        gameScore?.text = viewModel.getGameScore().toString()
        viewModel.setGameAnswer("")
        disableButtons()
    }
    private fun lose() {
        Toast.makeText(context , "Not quite!", Toast.LENGTH_SHORT).show()
        timer?.cancel()
        viewModel.setGameLife(viewModel.getGameLife() - 1)
        gameLife?.text = viewModel.getGameLife().toString()
        viewModel.setGameAnswer("")
        disableButtons()
    }
}