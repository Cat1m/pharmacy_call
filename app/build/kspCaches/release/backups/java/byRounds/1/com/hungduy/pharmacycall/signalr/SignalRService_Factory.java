package com.hungduy.pharmacycall.signalr;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SignalRService_Factory implements Factory<SignalRService> {
  @Override
  public SignalRService get() {
    return newInstance();
  }

  public static SignalRService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SignalRService newInstance() {
    return new SignalRService();
  }

  private static final class InstanceHolder {
    private static final SignalRService_Factory INSTANCE = new SignalRService_Factory();
  }
}
