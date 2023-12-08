package com.example.assignment3

class GameViewModel(fragment: GameFragment) {
    private var gameModel: GameModel = GameModel(fragment)

    fun readyGame() {
        gameModel.reset()

        gameModel.setOnClickListeners()

        gameModel.disableGameGrids()

        gameModel.getUsername()
    }
}