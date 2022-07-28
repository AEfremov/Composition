package ru.efremov.composition.domain.repository

import ru.efremov.composition.domain.entity.GameSettings
import ru.efremov.composition.domain.entity.Level
import ru.efremov.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}