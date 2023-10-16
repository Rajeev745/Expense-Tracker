package com.example.expensetracker.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.R
import com.example.expensetracker.database.userdatabase.UserDataEntity
import com.example.expensetracker.databinding.FragmentSignUpBinding
import com.example.expensetracker.viewmodel.UserDataViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val userDataViewmodel by viewModels<UserDataViewmodel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnClickListener {
            val userObj: UserDataEntity? = getUserObj()
            if (userObj == null) {
                return@setOnClickListener
            }

            val userData = userDataViewmodel.getUserName(userObj.userName).observe(viewLifecycleOwner, {
                Log.d("Data", it?.userName.toString())
            })
            if(userData == null) {
                return@setOnClickListener
            }

            userDataViewmodel.insertUser(userObj)

            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

        }


    }

    private fun getUserObj(): UserDataEntity? {
        val fullName = binding.fullnameSignup.text.toString().trim()
        val userName = binding.usernameSignup.text.toString().trim()
        val passwordOne = binding.passwordSignup.text.toString().trim()
        val passwordTwo = binding.confirmPasswordSignup.text.toString().trim()

        val userObj = UserDataEntity(
            fullName = fullName,
            userName = userName,
            password = passwordOne,
            age = null,
            budget = null,
            mobile = null
        )

        if (passwordOne != passwordTwo || passwordOne.length < 6) {
            Toast.makeText(requireContext(), "Wrong password", Toast.LENGTH_LONG).show()
            return null
        }
        if (userName.isEmpty()) {
            Toast.makeText(requireContext(), "UserName is empty", Toast.LENGTH_LONG).show()
            return null
        }
        if (fullName.isEmpty()) {
            Toast.makeText(requireContext(), "Full Name is empty", Toast.LENGTH_LONG).show()
            return null
        }

        return userObj

    }

}