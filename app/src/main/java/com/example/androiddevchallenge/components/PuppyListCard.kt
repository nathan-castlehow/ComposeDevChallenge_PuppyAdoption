package com.example.androiddevchallenge.components

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.components.RemoteImage

@Composable
fun PuppyListCard(id:String,name: String, description:String, breed:String, image:String,  onPress :(String) -> Unit) {
    Row(modifier = Modifier
        .padding(10.dp)
        .clickable { onPress(id)}
        .fillMaxWidth()
        .shadow(5.dp)
        .background(color = Color.White)
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Column() {
            Row() {
                Column() {
                    Text(name, fontWeight = FontWeight.Bold)
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Breed: ")
                        }
                        append("$breed")
                    })
                }
                Column(modifier = Modifier.height(50.dp).width(50.dp)) {
                    RemoteImage(url = image, modifier = Modifier.fillMaxWidth().fillMaxHeight())
                }
            }
            Text("What to know?", fontWeight = FontWeight.Bold)
            Text(description.split(".")[0] + ".")
        }
    }
}


