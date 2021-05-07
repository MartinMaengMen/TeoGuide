package com.upc.teoguide.ui.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upc.teoguide.data.entities.Usuario
import com.upc.teoguide.data.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(database: AppDatabase) : ViewModel() {
    var _database : AppDatabase = database

    fun postUsuario(usuario : Usuario){
        viewModelScope.launch(Dispatchers.IO) {
                _database.usuarioDAO().insert(usuario)
        }
    }
}