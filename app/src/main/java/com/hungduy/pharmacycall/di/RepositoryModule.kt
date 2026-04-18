package com.hungduy.pharmacycall.di

import com.hungduy.pharmacycall.data.repository.PharmacyRepositoryImpl
import com.hungduy.pharmacycall.domain.repository.PharmacyRepository
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
    abstract fun bindPharmacyRepository(impl: PharmacyRepositoryImpl): PharmacyRepository
}
