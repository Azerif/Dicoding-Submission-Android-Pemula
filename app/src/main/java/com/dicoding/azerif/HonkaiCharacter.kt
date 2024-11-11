package com.dicoding.azerif
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HonkaiCharacter (
    val name: String,
    val path: String,
    val type: String,
    val desc: String,

    val charImg: Int,
    val charImgBG: Int,
    val rarityImg: Int,
    val pathImg: Int,
    val typeImg: Int

) : Parcelable