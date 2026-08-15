package com.example.islandgame.data


import com.example.islandgame.R

data class Countryflags (
    val countryname: String,
    val drawable: Int
)

val flags = listOf(
        Countryflags("Turkmekistan", R.drawable.flag_of_turkmenistan),
        Countryflags("Brazil", R.drawable.flag_brazil),
        Countryflags("Canada", R.drawable.flag_canada),
        Countryflags("Mexico", R.drawable.flag_mexico),
        Countryflags("Germany", R.drawable.flag_of_germany_svg),
        Countryflags("Netherlands", R.drawable.flag_of_the_netherlands_svg),
        Countryflags("UAE", R.drawable.flag_united_arab_emirates),
        Countryflags("Switzerland", R.drawable.switzerland),
        Countryflags("UK", R.drawable.uk_flag)

    )