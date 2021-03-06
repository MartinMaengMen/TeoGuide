package com.upc.teoguide.ui.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInViewModel(database : AppDatabase) : ViewModel() {
    var _database : AppDatabase = database

    fun checkUser(usuario : Usuario) : Boolean{
        var logeado = false
        viewModelScope.launch(Dispatchers.IO) {
            var response = _database.usuarioDAO().findUser(usuario.email!!,usuario.password!!)

        }
        return logeado
    }
}