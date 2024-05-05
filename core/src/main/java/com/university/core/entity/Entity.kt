package com.university.core.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class University(
    val alphaTwoCode: String,
    val name: String,
    val country: String,
    val domains: List<String>,
    val webPages: List<String>,
    val stateProvince: String?
) : Parcelable