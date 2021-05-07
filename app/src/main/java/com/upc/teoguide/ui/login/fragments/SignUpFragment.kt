package com.upc.teoguide.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.upc.teoguide.R
import com.upc.teoguide.databinding.FragmentSigninBinding
import com.upc.teoguide.databinding.FragmentSignupBinding

class SignUpFragment : Fragment(){
    private var _binding: FragmentSignupBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignupBinding.inflate(inflater,container,false)
        _binding = binding
        binding.registerButton.setOnClickListener {
            val action = SignUpFragmentDirections.actionNavigationSignupToNavigationSignin()
            NavHostFragment.findNavController(this).navigate(action)
        }
        // Inflate the layout for this fragment
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