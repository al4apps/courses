package com.al4apps.domain.models

enum class LaunchState {
    FIRST_START,
    UNAUTHORIZED,
    AUTHORIZED;

    fun toPrefNumber(): Int = when (this) {
        FIRST_START -> FIRST_START_NUMBER
        UNAUTHORIZED -> UNAUTHORIZED_NUMBER
        AUTHORIZED -> AUTHORIZED_NUMBER
    }

    companion object {
        private const val FIRST_START_NUMBER = 0
        private const val UNAUTHORIZED_NUMBER = 1
        private const val AUTHORIZED_NUMBER = 2

        fun fromPrefNumber(number: Int?): LaunchState = when (number) {
            FIRST_START_NUMBER -> FIRST_START
            UNAUTHORIZED_NUMBER -> UNAUTHORIZED
            AUTHORIZED_NUMBER -> AUTHORIZED
            else -> FIRST_START
        }
    }
}