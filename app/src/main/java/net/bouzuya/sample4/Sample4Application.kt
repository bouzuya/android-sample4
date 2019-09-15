package net.bouzuya.sample4

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class Sample4Application : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
