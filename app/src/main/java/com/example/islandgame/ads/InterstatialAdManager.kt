package com.example.islandgame.ads


import android.app.Activity
import com.google.android.libraries.ads.mobile.sdk.common.AdRequest
import com.google.android.libraries.ads.mobile.sdk.common.PreloadConfiguration
import com.google.android.libraries.ads.mobile.sdk.interstitial.InterstitialAdPreloader

class InterstitialAdManager {

    companion object {
        private const val AD_UNIT_ID =
            "ca-app-pub-3940256099942544/1033173712"
    }

    fun startPreloading() {
        val adRequest = AdRequest.Builder(AD_UNIT_ID).build()

        val preloadConfig = PreloadConfiguration(adRequest)

        InterstitialAdPreloader.start(
            AD_UNIT_ID,
            preloadConfig
        )
    }

    fun showAd(activity: Activity) {
        val ad = InterstitialAdPreloader.pollAd(AD_UNIT_ID)

        if (ad != null) {
            ad.show(activity)
        }
    }
}
