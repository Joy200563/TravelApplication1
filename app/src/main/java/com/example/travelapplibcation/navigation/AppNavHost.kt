package com.example.travelapplibcation.navigation

//import com.example.morningmvvm.ui.theme.screens.home.LoginScreen
//import com.example.morningmvvm.ui.theme.screens.products.AddProductsScreen
//import com.example.morningmvvm.ui.theme.screens.products.UpdateProductsScreen
//import com.example.morningmvvm.ui.theme.screens.products.ViewProductsScreen
import HomeScreen
//import ViewPlaceScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelapplibcation.ui.theme.screens.about.AboutScreen
import com.example.travelapplibcation.ui.theme.screens.login.LoginScreen
import com.example.travelapplibcation.ui.theme.screens.places.AddPlaceScreen
import com.example.travelapplibcation.ui.theme.screens.places.ViewPlaces
import com.example.travelapplibcation.ui.theme.screens.places.ViewUploadsScreen
import com.example.travelapplibcation.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(modifier: Modifier=Modifier, navController: NavHostController= rememberNavController(),startDestination: String= ROUTE_REGISTER) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = ROUTE_LOGIN
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
//        composable(ROUTE_PLACE_VIEW) {
//            ViewPlaceScreen(navController)
//        }
        composable(ROUTE_ADD_PLACE){
            AddPlaceScreen(navController)
        }
        composable(ROUTE_VIEW_PLACES){
            ViewPlaces(navController)
        }
        composable(ROUTE_VIEW_UPLOADS){
            ViewUploadsScreen(navController)
        }




    }
}

