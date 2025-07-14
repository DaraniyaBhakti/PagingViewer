package com.example.pagingviewer.di

import com.example.pagingviewer.data.remote.ApiService
import com.example.pagingviewer.data.repository.AlbumRepository
import com.example.pagingviewer.data.impl.AlbumsRepositoryImpl
import com.example.pagingviewer.data.repository.CommentRepository
import com.example.pagingviewer.data.impl.CommentRepositoryImpl
import com.example.pagingviewer.data.repository.PostRepository
import com.example.pagingviewer.data.impl.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://jsonplaceholder.typicode.com"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // You can use BASIC/HEADERS too
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String,okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService): PostRepository =
        PostRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideCommentRepository(apiService: ApiService): CommentRepository =
        CommentRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideAlbumRepository(apiService: ApiService): AlbumRepository =
        AlbumsRepositoryImpl(apiService)
}