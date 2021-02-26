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
package com.example.androiddevchallenge.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.model.Puppy

@Composable
fun Home(
    navController: NavController,
    puppyData: MutableState<List<Puppy>>
) {
    Column {

        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxWidth()
                .padding(1.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Adopt these cuties!",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    modifier = Modifier.fillMaxWidth()

                )
                Text(
                    "All these good doggos need a home. Can you help?",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        LazyColumn(modifier = Modifier.background(color = Color.LightGray)) {
            items(puppyData.value.size) {
                val currentPuppy = puppyData.value[it]
                PuppyListCard(
                    id = it.toString(),
                    name = currentPuppy.name,
                    description = currentPuppy.description,
                    breed = currentPuppy.breed,
                    image = currentPuppy.imageUrl,
                    onPress = { id -> navController.navigate("puppyDetail/$id") }
                )
            }
        }
    }
}
