package com.upc.teoguide.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.upc.teoguide.R
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.AppDatabase
import com.upc.teoguide.databinding.FragmentSigninBinding
import com.upc.teoguide.databinding.FragmentSignupBinding
import com.upc.teoguide.ui.login.viewmodels.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpFragment : Fragment(){
    private var _binding: FragmentSignupBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignupBinding.inflate(inflater,container,false)
        val database = this.context?.let { AppDatabase.getInstance(it)}
        _binding = binding
        CoroutineScope(Dispatchers.IO).launch {
            onClickRegisterButton(database!!)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun onClickRegisterButton(database: AppDatabase){
        _binding?.registerButton?.setOnClickListener {
            var email = _binding?.userTextInput?.text.toString()
            var name = _binding?.nameTextInput?.text.toString()
            var password = _binding?.passwordTextInput?.text.toString()
            var verifyPassword = _binding?.verifyPasswordTextInput?.text.toString()
            if(email.contains("@")){
                if(name !="" && email!="" && password!="" && password == verifyPassword){
                    var usuario = Usuario(
                        0,
                        _binding?.userTextInput?.text.toString(),
                        _binding?.passwordTextInput?.text.toString(),
                        name)
                    SignUpViewModel(database!!).postUsuario(usuario)
                    val action = SignUpFragmentDirections.actionNavigationSignupToNavigationSignin()
                    NavHostFragment.findNavController(this).navigate(action)
                }
                else
                    Toast.makeText(this@SignUpFragment.context,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this@SignUpFragment.context,"El email no es valido",Toast.LENGTH_LONG).show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}