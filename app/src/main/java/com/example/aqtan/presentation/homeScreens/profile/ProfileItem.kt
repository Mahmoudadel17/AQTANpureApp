package com.example.aqtan.presentation.homeScreens.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditLocation
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.outlined.ContactPage
import androidx.compose.material.icons.outlined.ModeNight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ProfileItem(
    val imageVector: ImageVector,
    val color:Long
) {

    data object Country : ProfileItem(
        imageVector = Icons.Default.EditLocation,
        color = 0xFF03A9F4
    )
    data object Language : ProfileItem(
        imageVector = Icons.Default.Language,
        color = 0xFF942BB9
    )
    data object Mode : ProfileItem(
        imageVector = Icons.Outlined.ModeNight,
        color = 0xFFCADF4A

    )

    data object TermsAndConditions : ProfileItem(
        imageVector = Icons.Outlined.ContactPage,
        color = 0xFFCC2828

    )

    data object License : ProfileItem(
        imageVector = Icons.Default.Language,

        color = 0xFFC62887

    )

    data object Rate : ProfileItem(
        imageVector = Icons.Outlined.Star,

        color = 0xFF02457A

    )

    data object Share : ProfileItem(
        imageVector = Icons.Default.Language,
        color = 0xFF1B9484

    )

}