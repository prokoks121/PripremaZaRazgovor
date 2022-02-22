package com.example.pripremazarazgovor.modul

import com.example.pripremazarazgovor.retrofit.Connect
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModul {
    @Singleton
    @Provides
    fun provideRetrofit(): Connect {
        return Connect()
    }
}