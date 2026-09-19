package com.example.islandgame.ads

import android.app.Activity
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
        onAdUnavailable: () -> Unit
    ) {

        val ad = RewardedAdPreloader.pollAd(AD_UNIT_ID)

        if (ad == null) {
            onAdUnavailable()
            return
        }

        ad.adEventCallback = object : RewardedAdEventCallback {

            override fun onAdDismissedFullScreenContent() {
                onAdFinished()
            }

            override fun onAdFailedToShowFullScreenContent(
                fullScreenContentError: FullScreenContentError
            ) {
                onAdFinished()
            }
        }

        ad.show(activity) { rewardItem ->
            onRewardEarned()
        }
    }
}