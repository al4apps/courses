package com.al4apps.courses.utils.validation

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

open class TextFieldValidator(
    private val view: TextInputLayout,
    private val action: () -> Unit
) : TextWatcher, Validator {

    init {
        view.editText?.addTextChangedListener(this)
    }

    private var _isValid = false
    override val isValid: Boolean get() = _isValid
    open val rules: List<ValidationRule> = listOf(NotEmptyValidationRule)

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        doValidate(text.toString())
    }

    override fun afterTextChanged(p0: Editable?) {}

    override fun validate() {
        doValidate()
    }

    private fun doValidate(text: String = view.editText?.text.toString()) {
        rules.forEach { rule ->
            _isValid = rule(text)
            if (!_isValid) {
                action()
                return
            }
        }
        action()
    }
}