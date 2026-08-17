package com.example.islandgame.sounds

import android.content.Context
import android.media.MediaPlayer
import com.example.islandgame.R

class MusicManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    fun play(){
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(
                context, R.raw.backgroundmusic
            )
            mediaPlayer?.isLooping = true
        }
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }
    }
    fun pause(){
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }
    fun stop(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}