package com.upc.teoguide.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.upc.teoguide.databinding.FragmentSigninBinding

class SignInFragment : Fragment() {
    private var _binding : FragmentSigninBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSigninBinding.inflate(inflater,container,false)
        _binding = binding
        binding.signupTextView.setOnClickListener {
            val action = SignInFragmentDirections.actionNavigationSigninToNavigationSignup()
            NavHostFragment.findNavController(this)
                .navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}