package com.example.androidtest.di

import android.content.Context
import com.example.androidtest.repo.Repo
import com.example.androidtest.repo.RepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideRepo(context: Context): Repo {
        return RepoImpl()
    }
}