package com.example.androiddevchallenge.components

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.PuppyApp
import com.example.androiddevchallenge.components.ui.theme.AndroidDevChallengeTheme
import com.example.androiddevchallenge.model.Puppy

@Composable
fun PuppyDetail(currentPuppy: Puppy) {
   Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().padding(5.dp), verticalArrangement = Arrangement.SpaceBetween) {
       Column() {
           RemoteImage(currentPuppy.imageUrl, modifier = Modifier.clip(CircleShape))
           Text(currentPuppy.name, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
           Text(currentPuppy.description)
       }
       Box( modifier = Modifier.fillMaxWidth().background(color=MaterialTheme.colors.primarySurface).clickable {  }.padding(10.dp)) {
           Text("Take me home!", color= Color.White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
       }
   }
}

