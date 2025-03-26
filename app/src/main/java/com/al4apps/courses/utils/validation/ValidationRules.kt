package com.al4apps.courses.utils.validation

interface ValidationRule {
    operator fun invoke(text: String): Boolean
}

object EmailValidationRule : ValidationRule {
    override fun invoke(text: String): Boolean {
        return text.matches(EMAIL_COMMON_VALIDATION_REGEX.toRegex())
    }

    private const val EMAIL_COMMON_VALIDATION_REGEX =
        "^[a-zA-Z0-9#\$%&'*+/=?^_|{}~-]+(?:\\.[a-zA-Z0-9#\$%&'*+/=?^_|{}~-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$"
}

object NotEmptyValidationRule : ValidationRule {
    override fun invoke(text: String): Boolean {
        return text.isNotBlank()
    }

}