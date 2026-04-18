package com.hungduy.pharmacycall.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * StorageModule — giữ chỗ cho các binding liên quan đến storage.
 * TokenStorage và DeviceInfo đã dùng @Inject constructor nên Hilt tự provide,
 * không cần khai báo thêm trong module này.
 */
@Module
@InstallIn(SingletonComponent::class)
object StorageModule
