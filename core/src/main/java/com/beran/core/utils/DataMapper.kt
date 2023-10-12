package com.beran.core.utils

import com.beran.core.data.remote.response.ArticlesItem
import com.beran.core.domain.model.NewsModel
import com.beran.core.domain.model.SourceModel

object DataMapper {
    fun articleItemToNewsModel(input: ArticlesItem): NewsModel {
        val source = input.source?.let { SourceModel(name = it.name, id = it.id.toString()) }
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