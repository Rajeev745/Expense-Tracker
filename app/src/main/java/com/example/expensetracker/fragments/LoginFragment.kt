package com.example.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.HomeActivity
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentLoginBinding
import com.example.expensetracker.viewmodel.UserDataViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userDataViewmodel by viewModels<UserDataViewmodel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginBtn.setOnClickListener {
            getUserData()
        }

    }

    private fun getUserData() {
        val userName = binding.loginUsername.text.toString().trim()
        val userPassword = binding.userpasswordLogin.text.toString().trim()

        userDataViewmodel.getUserName(userName).observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(requireContext(), "UserName doesn't exists", Toast.LENGTH_LONG)
                    .show()
                return@observe
            }
            if (userPassword != it.password) {
                Toast.makeText(requireContext(), "Wrong Password", Toast.LENGTH_LONG).show()
                return@observe
            }
            if (it.isLoggedIn != true) {
                userDataViewmodel.updateFlag(userName, true)
            }
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
        }

    }

}