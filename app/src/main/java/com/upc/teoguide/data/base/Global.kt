package com.upc.teoguide.data.base

import android.app.Application

class Global : Application() {
    companion object {
        @JvmField
        var usuarioId : Int? = null
    }
}