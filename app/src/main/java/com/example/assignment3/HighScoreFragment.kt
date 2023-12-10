/*  HighScoreFragment.kt
    PROG3210 Assignment 3

    Revision History
        Junseo Yang, 2023-12-10: Created
 */

package com.example.assignment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Class for HighScoreFragment inherited from Fragment
 */
class HighScoreFragment : Fragment() {
    // ViewModel
    private lateinit var viewModel: HighScoreViewModel

    /**
     * Method that override onCreateView method
     * @param inflater LayoutInflater
     * @param container ViewGroup?
     * @param savedInstanceState Bundle?
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_high_score, container, false)
    }

    /**
     * Method that override onActivityCreated method
     * @param savedInstanceState Bundle?
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = HighScoreViewModel(this)

        viewModel.readyHighScores()
    }
}