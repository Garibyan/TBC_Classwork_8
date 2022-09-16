package com.garibyan.armen.tbc_task_8.di

import com.garibyan.armen.tbc_task_8.data.remote.repository.ApiRepository
import com.garibyan.armen.tbc_task_8.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(apiRepository: ApiRepository): MainRepository
}