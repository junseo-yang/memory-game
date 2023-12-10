/*  GameViewModel.kt
    PROG3210 Assignment 3

    Revision History
        Junseo Yang, 2023-12-10: Created
 */

package com.example.assignment3

/**
 * Class for GameViewModel
 * @param fragment GameFragment
 */
class GameViewModel(fragment: GameFragment) {
    // Model
    private var gameModel: GameModel = GameModel(fragment)

    /**
     * Method for readyGame
     */
    fun readyGame() {
        gameModel.reset()

        gameModel.setOnClickListeners()

        gameModel.disableGameGrids()

        gameModel.getUsername()
    }
}