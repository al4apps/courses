package com.al4apps.courses.utils.validation

import com.google.android.material.textfield.TextInputLayout

class EmailValidator(
    view: TextInputLayout,
    action: () -> Unit
) : TextFieldValidator(view, action) {

    override val rules: List<ValidationRule> = listOf(
        NotEmptyValidationRule,
        EmailValidationRule
    )
}