package com.example.aqtan.presentation.homeScreens.profile

data class ProfileState(
    val isDark : Boolean = true,
    val isLanguageBottomSheetShow:Boolean = false,
    val isCountryBottomSheetShow:Boolean = false,
    val isArabic:Boolean = false,
    val isRtlDirection:Boolean = false,
    val countryCode:Int = -1

)