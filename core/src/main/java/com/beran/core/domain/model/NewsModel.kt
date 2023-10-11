package com.beran.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val publishedAt: String,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: SourceModel? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
) : Parcelable

@Parcelize
data class SourceModel(
    val name: String? = null,
    val id: String? = null
) : Parcelable
