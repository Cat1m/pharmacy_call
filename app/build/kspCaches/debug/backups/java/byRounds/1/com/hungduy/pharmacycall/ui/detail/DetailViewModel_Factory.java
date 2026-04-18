package com.hungduy.pharmacycall.ui.detail;

import androidx.lifecycle.SavedStateHandle;
import com.hungduy.pharmacycall.domain.repository.PharmacyRepository;
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
public final class DetailViewModel_Factory implements Factory<DetailViewModel> {
  private final Provider<PharmacyRepository> repoProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public DetailViewModel_Factory(Provider<PharmacyRepository> repoProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.repoProvider = repoProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public DetailViewModel get() {
    return newInstance(repoProvider.get(), savedStateHandleProvider.get());
  }

  public static DetailViewModel_Factory create(Provider<PharmacyRepository> repoProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new DetailViewModel_Factory(repoProvider, savedStateHandleProvider);
  }

  public static DetailViewModel newInstance(PharmacyRepository repo,
      SavedStateHandle savedStateHandle) {
    return new DetailViewModel(repo, savedStateHandle);
  }
}
