package com.hungduy.pharmacycall.di;

import com.hungduy.pharmacycall.data.remote.api.PharmacyApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvidePharmacyApiFactory implements Factory<PharmacyApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvidePharmacyApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PharmacyApi get() {
    return providePharmacyApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvidePharmacyApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePharmacyApiFactory(retrofitProvider);
  }

  public static PharmacyApi providePharmacyApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePharmacyApi(retrofit));
  }
}
