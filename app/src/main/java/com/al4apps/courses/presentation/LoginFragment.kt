package com.al4apps.courses.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.al4apps.courses.databinding.FragmentLoginBinding
import com.al4apps.courses.utils.validation.EmailValidator
import com.al4apps.courses.utils.validation.TextFieldValidator
import com.al4apps.courses.utils.validation.Validator

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val validators: MutableList<Validator> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
