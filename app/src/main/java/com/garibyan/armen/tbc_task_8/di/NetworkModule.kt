package com.garibyan.armen.tbc_task_8.di

import android.content.Context
import com.garibyan.armen.tbc_task_8.common.extensions.isNetworkConnection
import com.garibyan.armen.tbc_task_8.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(
        @Named("OnlineInterceptor") onlineInterceptor: Interceptor,
        @Named("OfflineInterceptor") offlineInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(offlineInterceptor)
            addNetworkInterceptor(onlineInterceptor)
            cache(cache)
        }.build()
    }

    @Provides
    @Singleton
    fun providesCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, (10 * 1024 * 1024).toLong())
    }

    @Singleton
    @Provides
    @Named("OnlineInterceptor")
    fun provideOnlineInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    @Provides
    @Singleton
    @Named("OfflineInterceptor")
    fun provideOffLineInterceptor(@ApplicationContext context: Context): Interceptor =
        Interceptor { chain ->
            var request: Request = chain.request()
            if (!context.isNetworkConnection()) {
                val maxStale = 60 * 60 * 24 * 30
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(
                        KotlinJsonAdapterFactory()
                    ).build()
                )
            )
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}