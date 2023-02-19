package com.example.retrofit

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.network.PlutoNetworkPlugin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin("network"))
            .install()
    }
}