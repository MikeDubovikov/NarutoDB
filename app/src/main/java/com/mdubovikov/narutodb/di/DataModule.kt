package com.mdubovikov.narutodb.di

import android.content.Context
import com.mdubovikov.narutodb.data.local.db.BookmarksDao
import com.mdubovikov.narutodb.data.local.db.BookmarksDatabase
import com.mdubovikov.narutodb.data.network.api.ApiFactory
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.data.repository.BookmarksRepositoryImpl
import com.mdubovikov.narutodb.data.repository.CategoriesRepositoryImpl
import com.mdubovikov.narutodb.data.repository.CategoryRepositoryImpl
import com.mdubovikov.narutodb.data.repository.SearchRepositoryImpl
import com.mdubovikov.narutodb.domain.repository.BookmarksRepository
import com.mdubovikov.narutodb.domain.repository.CategoriesRepository
import com.mdubovikov.narutodb.domain.repository.CategoryRepository
import com.mdubovikov.narutodb.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindBookmarksRepository(impl: BookmarksRepositoryImpl): BookmarksRepository

    @[ApplicationScope Binds]
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @[ApplicationScope Binds]
    fun bindCharacterRepository(impl: CategoriesRepositoryImpl): CategoriesRepository

    @[ApplicationScope Binds]
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    companion object {

        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideBookmarksDatabase(context: Context): BookmarksDatabase {
            return BookmarksDatabase.getInstance(context)
        }

        @[ApplicationScope Provides]
        fun provideBookmarksDao(database: BookmarksDatabase): BookmarksDao {
            return database.bookmarksDao()
        }
    }
}