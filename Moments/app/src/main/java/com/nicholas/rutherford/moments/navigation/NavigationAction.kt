package com.nicholas.rutherford.moments.navigation

import androidx.navigation.NavOptions

interface NavigationAction {
    val destination: String
    val navOptions: NavOptions
        get() = NavOptions.Builder().build()
}