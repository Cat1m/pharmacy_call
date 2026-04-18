package com.hungduy.pharmacycall.ui.login;

import com.hungduy.pharmacycall.signalr.SignalRService;
import com.hungduy.pharmacycall.util.DeviceInfo;
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<SignalRService> signalRProvider;

  private final Provider<TokenStorage> tokenStorageProvider;

  private final Provider<DeviceInfo> deviceInfoProvider;

  public LoginViewModel_Factory(Provider<SignalRService> signalRProvider,
      Provider<TokenStorage> tokenStorageProvider, Provider<DeviceInfo> deviceInfoProvider) {
    this.signalRProvider = signalRProvider;
    this.tokenStorageProvider = tokenStorageProvider;
    this.deviceInfoProvider = deviceInfoProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(signalRProvider.get(), tokenStorageProvider.get(), deviceInfoProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<SignalRService> signalRProvider,
      Provider<TokenStorage> tokenStorageProvider, Provider<DeviceInfo> deviceInfoProvider) {
    return new LoginViewModel_Factory(signalRProvider, tokenStorageProvider, deviceInfoProvider);
  }

  public static LoginViewModel newInstance(SignalRService signalR, TokenStorage tokenStorage,
      DeviceInfo deviceInfo) {
    return new LoginViewModel(signalR, tokenStorage, deviceInfo);
  }
}
