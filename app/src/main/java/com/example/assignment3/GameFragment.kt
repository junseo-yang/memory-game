package com.example.assignment3

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

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
        val gameLifeTitle: TextView? = view?.findViewById(R.id.gameLifeTitle)
        gameLifeTitle?.text = viewModel.getGameLifeTitle()

        // Data Binding for gameLife
        viewModel.setGameLife(getString(R.string.game_life).toInt())
        val gameLife: TextView? = view?.findViewById(R.id.gameLife)
        gameLife?.text = viewModel.getGameLife().toString()

        // Data Binding for gameScoreTitle
        viewModel.setGameScoreTitle(getString(R.string.game_score_title))
        val gameScoreTitle: TextView? = view?.findViewById(R.id.gameScoreTitle)
        gameScoreTitle?.text = viewModel.getGameScoreTitle()

        // Data Binding for gameScore
        viewModel.setGameScore(getString(R.string.game_score).toInt())
        val gameScore: TextView? = view?.findViewById(R.id.gameScore)
        gameScore?.text = viewModel.getGameScore().toString()

        // Data Binding for gameTimerTitle
        viewModel.setGameTimerTitle(getString(R.string.game_timer_title))
        val gameTimerTitle: TextView? = view?.findViewById(R.id.gameTimerTitle)
        gameTimerTitle?.text = viewModel.getGameTimerTitle()

        // Data Binding for gameTimer
        viewModel.setGameTimer(getString(R.string.game_timer).toInt())
        val gameTimer: TextView? = view?.findViewById(R.id.gameTimer)
        gameTimer?.text = viewModel.getGameTimer().toString()

        // Data Binding for gameTitle
        viewModel.setTitle(getString(R.string.game_title))
        val gameTitle: TextView? = view?.findViewById(R.id.gameTitle)
        gameTitle?.text = viewModel.getTitle()

        // Data Binding for gameStartButton
        viewModel.setStartButtonText(getString(R.string.game_start_button))
        val gameStartButton: Button? = view?.findViewById(R.id.gameStartButton)
        gameStartButton?.text = viewModel.getStartButtonText()

        // SetOnClickListener on gameStartButton
        gameStartButton?.setOnClickListener { view ->
            Toast.makeText(this.context, "Start!", Toast.LENGTH_SHORT).show()
        }

        // Get GridLayout
        var gridLayout: GridLayout? = view?.findViewById(R.id.gridLayout)

        // Get gridCount
        val gridCount: Int? = gridLayout?.childCount

        // Create List of Buttons
        var grids = mutableListOf<MaterialButton>()
        for (i in 0 until gridCount!!) {
            grids.add(gridLayout?.getChildAt(i) as MaterialButton)
        }

        // SetOnClickListener for each grid
        for (grid in grids) {
            grid.setOnClickListener {
                onClickGrid(it)
                Log.d("asdf", it.tag.toString())
            }
        }
    }

    fun onClickGrid(view: View) {
        view.setBackgroundColor(Color.BLACK)
    }
}