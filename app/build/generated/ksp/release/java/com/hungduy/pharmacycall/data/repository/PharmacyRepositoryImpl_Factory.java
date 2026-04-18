package com.hungduy.pharmacycall.data.repository;

import com.hungduy.pharmacycall.data.remote.api.PharmacyApi;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class PharmacyRepositoryImpl_Factory implements Factory<PharmacyRepositoryImpl> {
  private final Provider<PharmacyApi> apiProvider;

  private final Provider<Moshi> moshiProvider;

  public PharmacyRepositoryImpl_Factory(Provider<PharmacyApi> apiProvider,
      Provider<Moshi> moshiProvider) {
    this.apiProvider = apiProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public PharmacyRepositoryImpl get() {
    return newInstance(apiProvider.get(), moshiProvider.get());
  }

  public static PharmacyRepositoryImpl_Factory create(Provider<PharmacyApi> apiProvider,
      Provider<Moshi> moshiProvider) {
    return new PharmacyRepositoryImpl_Factory(apiProvider, moshiProvider);
  }

  public static PharmacyRepositoryImpl newInstance(PharmacyApi api, Moshi moshi) {
    return new PharmacyRepositoryImpl(api, moshi);
  }
}
