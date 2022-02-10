package pl.mwaszczuk.hellocompose.mock

import android.util.Log

class AnalyticsLogger {

    fun log(currentRoute: String) {
        Log.d("no elo", currentRoute)
    }
}
