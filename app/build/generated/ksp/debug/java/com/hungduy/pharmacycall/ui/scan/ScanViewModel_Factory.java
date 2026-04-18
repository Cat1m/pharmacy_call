package com.hungduy.pharmacycall.ui.scan;

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
public final class ScanViewModel_Factory implements Factory<ScanViewModel> {
  private final Provider<TokenStorage> tokenStorageProvider;

  public ScanViewModel_Factory(Provider<TokenStorage> tokenStorageProvider) {
    this.tokenStorageProvider = tokenStorageProvider;
  }

  @Override
  public ScanViewModel get() {
    return newInstance(tokenStorageProvider.get());
  }

  public static ScanViewModel_Factory create(Provider<TokenStorage> tokenStorageProvider) {
    return new ScanViewModel_Factory(tokenStorageProvider);
  }

  public static ScanViewModel newInstance(TokenStorage tokenStorage) {
    return new ScanViewModel(tokenStorage);
  }
}
