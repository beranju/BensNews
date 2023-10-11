package com.beran.core.data.utils

import com.beran.core.data.remote.response.ArticlesItem
import com.beran.core.domain.model.NewsModel

object DataDummy {
    fun generateListOfArticleItems(): List<ArticlesItem>{
        val newsList = mutableListOf<ArticlesItem>()
        for (i in 0..10){
            val news = ArticlesItem(i.toString())
            newsList.add(news)
        }
        return newsList
    }
}