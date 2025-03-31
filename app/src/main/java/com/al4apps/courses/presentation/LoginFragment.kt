package com.al4apps.courses.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.al4apps.courses.R
import com.al4apps.courses.databinding.FragmentLoginBinding
import com.al4apps.courses.presentation.viewmodels.StartViewModel
import com.al4apps.courses.utils.AbstractFragment
import com.al4apps.courses.utils.validation.EmailValidator
import com.al4apps.courses.utils.validation.TextFieldValidator
import com.al4apps.courses.utils.validation.Validator
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class LoginFragment : AbstractFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val validators: MutableList<Validator> = mutableListOf()
    private val startViewModel: StartViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValidators()
        initListeners()
    }

    private fun initListeners() {
        binding.vkIcon.setOnClickListener { onSocialButtonClick(VK_URL) }
        binding.okIcon.setOnClickListener { onSocialButtonClick(OK_URL) }
        binding.continueButton.setOnClickListener {
            navigateToMainScreen()
            startViewModel.onAuthorized()
        }
    }

    private fun navigateToMainScreen() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.loginFragment, inclusive = true, saveState = false)
            .build()
        findNavController().navigate(
            resId = R.id.mainFragment,
            args = null,
            navOptions = navOptions
        )
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
