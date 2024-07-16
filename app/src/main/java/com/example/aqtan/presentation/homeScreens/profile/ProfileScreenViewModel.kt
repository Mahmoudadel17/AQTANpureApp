package com.example.aqtan.presentation.homeScreens.profile

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aqtan.data.Constants
import com.example.aqtan.domain.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import java.util.Locale


@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val pref: SharedPreferences
): ViewModel() {

    private var _state by mutableStateOf(
        ProfileState()
    )

    val state: State<ProfileState>
        get() = derivedStateOf { _state }



    init {
        val codeIsArabic =  pref.getSharedPreferences(Constants.LANGUAGE,"") == Constants.LANGUAGE_AR_CODE
        _state = _state.copy(
            isDark = pref.getBooleanSharedPreferences(Constants.MODE, true),
            isArabic  =  codeIsArabic ,
            isRtlDirection = codeIsArabic,
        )
    }

    fun isFirstTimeOpenApp():Boolean{
        // check if this first time to open application or not
        // note: make sure you call this function before "onCreateChangeLanguage"
        // because onCreateChangeLanguage make first time false
        return pref.getBooleanSharedPreferences(Constants.FIRST_TIME,true)
    }

    fun onCreateChangeLanguage(
        resources: Resources,
        systemLanguage:String
    ){
        // make languageCode equal to language that saved in
        var languageCode = pref.getSharedPreferences(Constants.LANGUAGE,"")

        // check if this first time to open application or not
        // if its first time, make languageCode equal to systemLanguage
        if (pref.getBooleanSharedPreferences(Constants.FIRST_TIME,true)){
            // make languageCode is systemLanguage because it first time to open app.
            languageCode = systemLanguage
            pref.setSharedPreferences(Constants.LANGUAGE,systemLanguage)
            _state = _state.copy(
                isArabic  =  systemLanguage == Constants.LANGUAGE_AR_CODE ,
                isRtlDirection = systemLanguage == Constants.LANGUAGE_AR_CODE
            )
            // make firstTime false in Shared Preferences
            pref.setBooleanSharedPreferences(Constants.FIRST_TIME,false)

        }
        // set application language for the right language
        setLocale(languageCode,resources)
    }

    fun onChangeMode() {
        pref.setBooleanSharedPreferences(Constants.MODE, _state.isDark.not())
        _state = _state.copy(
            isDark = _state.isDark.not()
        )

    }



    fun onShowBottomSheet(){
        _state = _state.copy(
            isBottomSheetShow = true
        )
    }
    fun onDismissRequest(){
        _state = _state.copy(
            isBottomSheetShow = false
        )
    }

    fun onSelectLanguage(languageCode:String, resources: Resources){
        setLocale(languageCode,resources)
        pref.setSharedPreferences(Constants.LANGUAGE,languageCode)
        _state = _state.copy(
            isArabic = languageCode == Constants.LANGUAGE_AR_CODE,
            isRtlDirection = languageCode == Constants.LANGUAGE_AR_CODE
        )
    }

    private fun setLocale(languageCode: String, resources: Resources){
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }



    
}