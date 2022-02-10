package pl.mwaszczuk.hellocompose.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Use to start another Activity.
 * Use only within a Composable on a LocalContext.
 * @sample LocalContext.current.startActivity()
 *
 * @param activity - Class of the Activity to be launched
 * @param clearTop - Flag that specified whether to clear the top of the Activities stack.
 */
fun Context.startActivity(
    activity: Class<out Activity>,
    clearTop: Boolean = false
) {
    val intent = Intent(this, activity)
    if (clearTop) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }
    startActivity(intent)
}

