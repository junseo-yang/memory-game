package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        // Data Binding for gameLifeTitle
        viewModel.setGameLifeTitle(getString(R.string.game_life_title))
        var gameLifeTitle = view?.findViewById<TextView>(R.id.gameLifeTitle)
        gameLifeTitle?.text = viewModel.getGameLifeTitle()

        // Data Binding for gameLife
        viewModel.setGameLife(getString(R.string.game_life).toInt())
        var gameLife = view?.findViewById<TextView>(R.id.gameLife)
        gameLife?.text = viewModel.getGameLife().toString()

        // Data Binding for gameScoreTitle
        viewModel.setGameScoreTitle(getString(R.string.game_score_title))
        var gameScoreTitle = view?.findViewById<TextView>(R.id.gameScoreTitle)
        gameScoreTitle?.text = viewModel.getGameScoreTitle()

        // Data Binding for gameScore
        viewModel.setGameScore(getString(R.string.game_score).toInt())
        var gameScore = view?.findViewById<TextView>(R.id.gameScore)
        gameScore?.text = viewModel.getGameScore().toString()

        // Data Binding for gameTimerTitle
        viewModel.setGameTimerTitle(getString(R.string.game_timer_title))
        var gameTimerTitle = view?.findViewById<TextView>(R.id.gameTimerTitle)
        gameTimerTitle?.text = viewModel.getGameTimerTitle()

        // Data Binding for gameTimer
        viewModel.setGameTimer(getString(R.string.game_timer).toInt())
        var gameTimer = view?.findViewById<TextView>(R.id.gameTimer)
        gameTimer?.text = viewModel.getGameTimer().toString()

        // Data Binding for gameTitle
        viewModel.setTitle(getString(R.string.game_title))
        var gameTitle = view?.findViewById<TextView>(R.id.gameTitle)
        gameTitle?.text = viewModel.getTitle()

        // Data Binding for gameStartButton
        viewModel.setStartButtonText(getString(R.string.game_start_button))
        var gameStartButton = view?.findViewById<Button>(R.id.gameStartButton)
        gameStartButton?.text = viewModel.getStartButtonText()

        // SetOnClickListener on gameStartButton
        gameStartButton?.setOnClickListener {
            viewModel.startGame(lifecycleScope, context, view)
        }

        // Get GridLayout
        var gridLayout = view?.findViewById<GridLayout>(R.id.gridLayout)

        // Create List of Buttons
        for (i in 0 until gridLayout?.childCount!!) {
            viewModel.addGameGrid(gridLayout.getChildAt(i) as MaterialButton)
        }

        // SetOnClickListener for each grid
        for (grid in viewModel.getGameGrids()!!) {
            grid.setOnClickListener {
                viewModel.onClickGrid(lifecycleScope, context, it, view)
            }
        }

        // Disable Buttons for Initialization
        viewModel.getGameGrids()?.forEach { viewModel.disableGameGrid(it) }
    }
}