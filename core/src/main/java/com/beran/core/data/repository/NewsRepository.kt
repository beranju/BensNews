package com.beran.core.data.repository

import com.beran.core.data.remote.retrofit.ApiService
import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import com.beran.core.domain.repository.INewsRepository
import com.beran.core.utils.Constants.COUNTRY_US
import com.beran.core.utils.DataMapper.articleItemToNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

class NewsRepository(
    private val apiService: ApiService
) : INewsRepository {
    override fun getAllNews(
        page: Int?,
        pageSize: Int?,
        country: String?,
        category: String?
    ): Flow<Resource<List<NewsModel>>> =
        flow {
            emit(Resource.Loading)
            try {
                val response =
                    apiService.fetchAllNews(category = category, country = country ?: COUNTRY_US)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.articles?.isNotEmpty() == true) {
                        val dataItem = body.articles.let { items ->
                            items.map { articleItemToNewsModel(it!!) }
                        }
                        emit(Resource.Success(dataItem))
                    } else {
                        emit(Resource.Success(emptyList()))
                    }
                } else {
                    val error = when (response.code()) {
                        500 -> "Server error, coba lagi nanti"
                        400 -> "System error, coba lagi"
                        else -> "Terjadi masalah, coba lagi"
                    }
                    emit(Resource.Error(error))
                }
            } catch (e: IOException) {
                emit(Resource.Error("Periksa Koneksi Internet Anda"))
            } catch (e: SocketTimeoutException) {
                emit(Resource.Error("Waktu koneksi habis, ulangi"))
            } catch (e: HttpException) {
                val error = when (e.code()) {
                    500 -> "Server error, coba lagi nanti"
                    400 -> "System error, coba lagi"
                    else -> "Terjadi masalah, coba lagi"
                }
                emit(Resource.Error(error))
            }
        }
            .flowOn(Dispatchers.IO)

    override fun getNewsByQuery(
        page: Int?,
        pageSize: Int?,
        query: String,
        searchIn: String?
    ): Flow<Resource<List<NewsModel>>> =
        flow<Resource<List<NewsModel>>> {
            emit(Resource.Loading)
            try {
                val response = apiService.findNewsByQuery(query = query)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.articles?.isNotEmpty() == true) {
                        val dataItem = body.articles.let { items ->
                            items.map { articleItemToNewsModel(it!!) }
                        }
                        emit(Resource.Success(dataItem))
                    } else {
                        emit(Resource.Success(emptyList()))
                    }
                } else {
                    val error = when (response.code()) {
                        500 -> "Server error, coba lagi nanti"
                        400 -> "System error, coba lagi"
                        else -> "Terjadi masalah, coba lagi"
                    }
                    emit(Resource.Error(error))
                }
            } catch (e: IOException) {
                emit(Resource.Error("Periksa Koneksi Internet Anda"))
            } catch (e: SocketTimeoutException) {
                emit(Resource.Error("Waktu koneksi habis, ulangi"))
            } catch (e: HttpException) {
                val error = when (e.code()) {
                    500 -> "Server error, coba lagi nanti"
                    400 -> "System error, coba lagi"
                    else -> "Terjadi masalah, coba lagi"
                }
                emit(Resource.Error(error))
            }
        }
            .flowOn(Dispatchers.IO)

}