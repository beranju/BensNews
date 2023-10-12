package com.beran.core.utils

import com.beran.core.data.remote.response.ArticlesItem
import com.beran.core.domain.model.NewsModel

object DataMapper {
    fun articleItemToNewsModel(input: ArticlesItem): NewsModel {
        val source = input.source?.name ?: "Anonymous"
        return NewsModel(
            publishedAt = input.publishedAt,
            author = input.author,
            urlToImage = input.urlToImage,
            description = input.description,
            source = source,
            title = input.title,
            url = input.url.orEmpty(),
            content = input.content
        )
    }
}