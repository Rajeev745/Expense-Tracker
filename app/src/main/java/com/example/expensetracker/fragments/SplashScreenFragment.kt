package com.example.expensetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.expensetracker.HomeActivity
import com.example.expensetracker.R
import com.example.expensetracker.viewmodel.UserDataViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private val userDataViewmodel by viewModels<UserDataViewmodel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        Handler(Looper.getMainLooper()).postDelayed({
            userDataViewmodel.userList.observe(viewLifecycleOwner) {
                Log.d("UserData", it.toString())
                if (it.size == 0) {
                    findNavController().navigate(R.id.action_splashScreenFragment_to_welcomeFragment)
                } else {
                    if (it[0].isLoggedIn == true) {
                        val intent = Intent(requireContext(), HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                    }
                }
            }
        }, 3000)
    }

}