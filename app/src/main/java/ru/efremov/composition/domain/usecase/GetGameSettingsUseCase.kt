package ru.efremov.composition.domain.usecase

import ru.efremov.composition.domain.entity.GameSettings
import ru.efremov.composition.domain.entity.Level
import ru.efremov.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}