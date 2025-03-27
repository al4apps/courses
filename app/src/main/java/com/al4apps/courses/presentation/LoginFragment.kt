package com.al4apps.courses.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.al4apps.courses.databinding.FragmentLoginBinding
import com.al4apps.courses.utils.AbstractFragment
import com.al4apps.courses.utils.validation.EmailValidator
import com.al4apps.courses.utils.validation.TextFieldValidator
import com.al4apps.courses.utils.validation.Validator

class LoginFragment : AbstractFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val validators: MutableList<Validator> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValidators()
        binding.vkIcon.setOnClickListener { onSocialButtonClick(VK_URL) }
        binding.okIcon.setOnClickListener { onSocialButtonClick(OK_URL) }
    }

    private fun initValidators() {
        val emailValidator = EmailValidator(
            view = binding.emailInputLayout,
            action = { refreshButton() }
        )
        val passwordValidator = TextFieldValidator(
            view = binding.passwordInputLayout,
            action = { refreshButton() }
        )
        validators.addAll(listOf(emailValidator, passwordValidator))
    }

    private fun refreshButton() {
        binding.continueButton.isEnabled = validators.all { it.isValid }
    }

    private fun onSocialButtonClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    companion object {
        private const val VK_URL = "https://vk.com/"
        private const val OK_URL = "https://ok.ru/"
    }
}
