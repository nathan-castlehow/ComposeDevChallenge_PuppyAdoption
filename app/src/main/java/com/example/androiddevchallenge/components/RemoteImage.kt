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
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

@Composable
fun RemoteImage(url: String, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Crop) {
    val image = remember { mutableStateOf<ImageBitmap?>(null) }
    val fallback = remember { mutableStateOf<Drawable?>(null) }

    DisposableEffect(url) {
        val picasso = Picasso.get()

        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                fallback.value = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                fallback.value = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image.value = bitmap?.asImageBitmap()
            }
        }

        picasso.load(url).into(target)

        onDispose {
            image.value = null
            fallback.value = null
            picasso.cancelRequest(target)
        }
    }

    if (image.value != null) {
        Image(
            bitmap = image.value!!,
            modifier = modifier,
            contentDescription = "image",
            contentScale = contentScale
        )
    }
}
