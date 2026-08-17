package com.example.islandgame.sounds
import android.content.Context
import android.media.MediaPlayer
import com.example.islandgame.R

class SoundManager(private val context: Context) {
    var soundEnabled: Boolean = true

    fun playSound(){

        if(!soundEnabled) return
        val mediaPlayer = MediaPlayer.create(
            context,
            R.raw.button_click
        )

        mediaPlayer?.setOnCompletionListener {
            it.release()
        }
        mediaPlayer?.start()
    }

}