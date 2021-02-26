/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("BlockingMethodInNonBlockingContext")

package com.example.androiddevchallenge.components
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Puppy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val PUPPY_DETAIL_ROUTE = "puppyDetail"
    const val PUPPY_DETAIL_ID_KEY = "puppyId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.HOME_ROUTE) {
    val navController = rememberNavController()
    val availablePuppies = rememberSaveable { mutableStateOf<List<Puppy>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(true) {
        context.assets.open("puppy_data.json").use { inputStream ->
            val type = object : TypeToken<List<Puppy>>() {}.type
            val reader = JsonReader(inputStream.reader())
            val data: List<Puppy> = Gson().fromJson(reader, type)
            availablePuppies.value = data
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            Home(navController = navController, puppyData = availablePuppies)
        }
        composable(
            "${MainDestinations.PUPPY_DETAIL_ROUTE}/{${MainDestinations.PUPPY_DETAIL_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.PUPPY_DETAIL_ID_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val puppyId = arguments.getInt(MainDestinations.PUPPY_DETAIL_ID_KEY)
            PuppyDetail(
                currentPuppy = availablePuppies.value[puppyId]
            )
        }
    }
}
