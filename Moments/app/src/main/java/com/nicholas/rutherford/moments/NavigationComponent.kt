package com.nicholas.rutherford.moments

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.nicholas.rutherford.moments.navigation.Navigator
import com.nicholas.rutherford.moments.navigation.asLifecycleAwareState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationComponent(
    activity: MainActivity,
    navHostController: NavHostController,
    navigator: Navigator,
    viewModels: ViewModels,
) {
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current


    val navigatorState by navigator.navActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )
    val popRouteState by navigator.popRouteActions.asLifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        initialState = null
    )

    val homeViewModel = viewModels.homeViewModel

}