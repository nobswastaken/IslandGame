package com.example.islandgame.ads

import android.app.Activity
import com.example.islandgame.sounds.MusicManager
import com.google.android.libraries.ads.mobile.sdk.common.FullScreenContentError
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAdEventCallback
import com.google.android.libraries.ads.mobile.sdk.rewarded.RewardedAdPreloader
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.PreloadConfiguration

class RewardedAdManager {

    companion object {
        private const val AD_UNIT_ID =
            "ca-app-pub-3940256099942544/5224354917"
    }

    fun startPreloading() {

        val adRequest = AdRequest.Builder(AD_UNIT_ID).build()

        val preloadConfig = PreloadConfiguration(adRequest)

        RewardedAdPreloader.start(
            AD_UNIT_ID,
            preloadConfig
        )
    }

    fun showAd(
        activity: Activity,
        onRewardEarned: () -> Unit,
        onAdFinished: () -> Unit,
        onAdUnavailable: () -> Unit,
        musicManager: MusicManager
    ) {

        val ad = RewardedAdPreloader.pollAd(AD_UNIT_ID)

        if (ad == null) {
            onAdUnavailable()
            return
        }

        var rewardEarned = false

        ad.adEventCallback = object : RewardedAdEventCallback {

            override fun onAdShowedFullScreenContent() {
                musicManager.pause()
            }


            override fun onAdDismissedFullScreenContent() {
                musicManager.play()
                onAdFinished()
            }

            override fun onAdFailedToShowFullScreenContent(
                fullScreenContentError: FullScreenContentError
            ) {
                musicManager.play()
                onAdUnavailable()
            }
        }

        ad.show(activity) {

            if (!rewardEarned) {
                rewardEarned = true
                onRewardEarned()
            }
        }
    }
}
