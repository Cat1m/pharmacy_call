package com.hungduy.pharmacycall.di;

import com.hungduy.pharmacycall.data.remote.interceptor.TokenInterceptor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvideOkHttpFactory implements Factory<OkHttpClient> {
  private final Provider<TokenInterceptor> tokenProvider;

  private final Provider<HttpLoggingInterceptor> logProvider;

  public NetworkModule_ProvideOkHttpFactory(Provider<TokenInterceptor> tokenProvider,
      Provider<HttpLoggingInterceptor> logProvider) {
    this.tokenProvider = tokenProvider;
    this.logProvider = logProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttp(tokenProvider.get(), logProvider.get());
  }

  public static NetworkModule_ProvideOkHttpFactory create(Provider<TokenInterceptor> tokenProvider,
      Provider<HttpLoggingInterceptor> logProvider) {
    return new NetworkModule_ProvideOkHttpFactory(tokenProvider, logProvider);
  }

  public static OkHttpClient provideOkHttp(TokenInterceptor token, HttpLoggingInterceptor log) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttp(token, log));
  }
}
