package com.hongul.filq.ui.navigation

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.hongul.filq.R
import com.hongul.filq.ui.CardShareViewModelProvider
import com.hongul.filq.ui.customize.BusinessCardGenerateScreen
//import com.hongul.filq.ui.customize.page.FaceBookURLPage
//import com.hongul.filq.ui.customize.page.InstaGramURLPage
import com.hongul.filq.ui.customize.page.PlusSnsPage
import com.hongul.filq.ui.customize.page.SocialInfoPage
import com.hongul.filq.ui.customize.page.URLPage
//import com.hongul.filq.ui.customize.page.XURLPage
//import com.hongul.filq.ui.customize.page.YoutubeURLPage
import com.hongul.filq.ui.home.HomeScreen
import com.hongul.filq.ui.home.StickerChangeRoute
import com.hongul.filq.ui.home.StickerChangeScreen
import com.hongul.filq.ui.share.CardShareRoute
import com.hongul.filq.ui.share.CardShareScreen
import com.hongul.filq.ui.share.CardShareViewModel

sealed class NavItem(val route: String, val title: String, @DrawableRes val icon: Int) {
    data object Home : NavItem("home", "내 명함", R.drawable.ic_nav_home)
    data object Contact : NavItem("contact", "연락처", R.drawable.ic_nav_contact)
    data object WiP : NavItem("wip", "작업 중", R.drawable.ic_nav_wip)
    data object More : NavItem("more", "더보기", R.drawable.ic_nav_more)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        var showNavigationBar by remember { mutableStateOf(false) }

        NavHost(
            navController = navController,
            startDestination = NavItem.Home.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(NavItem.Home.route) {
                showNavigationBar = true
                HomeScreen(navigator = navController)
            }
            composable(NavItem.Contact.route) {
                showNavigationBar = true
                PlaceHolder(it.destination.route!!)
            }
            composable(NavItem.WiP.route) {
                showNavigationBar = true
                PlaceHolder(it.destination.route!!)
            }
            composable(NavItem.More.route) {
                showNavigationBar = true
                PlaceHolder(it.destination.route!!)
            }
            composable<StickerChangeRoute> {
                showNavigationBar = false
                val route = it.toRoute<StickerChangeRoute>()
                StickerChangeScreen(route.cardId, navController)
            }
            composable<CardShareRoute> {
                showNavigationBar = false
                val route = it.toRoute<CardShareRoute>()
                val viewModel: CardShareViewModel = viewModel(
                    factory = CardShareViewModelProvider.factory(route.cardId),
                )
                CardShareScreen(viewModel)
            }
            composable("generate") {
                showNavigationBar = false
                BusinessCardGenerateScreen(navController)
            }
            composable("social_info") {
                showNavigationBar = false
                SocialInfoPage(onNext = {
                    navController.navigate("plus_sns") // PlusSns로 이동
                })
            }
            composable("plus_sns") {
                showNavigationBar = false
                PlusSnsPage(
                    onBack = { navController.popBackStack() },
                    navController = navController
                )
            }
//
            composable("url_page/{title}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: "SNS"
                URLPage(
                    title = title,
                    onBack = { navController.popBackStack() },
                    onRegisterClick = { url ->
                        Log.d("URLPage", "$title URL 등록됨: $url")
                        navController.popBackStack()
                    }
                )
            }
        }

        if (showNavigationBar) {
            BottomNavigation(navController = navController)
        }
    }
}


@Composable
private fun PlaceHolder(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Text(text = title)
        }
    )
}

