package com.example.pripremazarazgovor.modul

import com.example.pripremazarazgovor.retrofit.Connect
import com.example.pripremazarazgovor.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositsoryModul {
    @Singleton
    @Provides
    fun provideRepository(
        retrofit: Connect
    ): Repository {
        return Repository(retrofit)
    }
}