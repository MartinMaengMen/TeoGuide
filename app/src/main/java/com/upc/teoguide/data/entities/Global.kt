package com.upc.teoguide.data.entities

import android.app.Application

public class Global : Application() {
    companion object {
        @JvmField
        var primerInicio : Boolean = false
    }
}