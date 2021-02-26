package com.example.androiddevchallenge.model
import com.google.gson.annotations.SerializedName

data class Puppy(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("age") val age: Int,
    @field:SerializedName("breed") val breed: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("imageUrl") val imageUrl: String,
    @field:SerializedName("favourite") val favourite: Boolean,
)



