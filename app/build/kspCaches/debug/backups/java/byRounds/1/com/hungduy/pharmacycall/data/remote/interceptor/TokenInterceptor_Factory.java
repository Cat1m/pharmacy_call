package com.hungduy.pharmacycall.data.remote.interceptor;

import com.hungduy.pharmacycall.util.TokenStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class TokenInterceptor_Factory implements Factory<TokenInterceptor> {
  private final Provider<TokenStorage> tokenStorageProvider;

  public TokenInterceptor_Factory(Provider<TokenStorage> tokenStorageProvider) {
    this.tokenStorageProvider = tokenStorageProvider;
  }

  @Override
  public TokenInterceptor get() {
    return newInstance(tokenStorageProvider.get());
  }

  public static TokenInterceptor_Factory create(Provider<TokenStorage> tokenStorageProvider) {
    return new TokenInterceptor_Factory(tokenStorageProvider);
  }

  public static TokenInterceptor newInstance(TokenStorage tokenStorage) {
    return new TokenInterceptor(tokenStorage);
  }
}
