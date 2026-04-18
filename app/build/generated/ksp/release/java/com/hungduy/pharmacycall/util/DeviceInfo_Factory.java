package com.hungduy.pharmacycall.util;

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
public final class DeviceInfo_Factory implements Factory<DeviceInfo> {
  @Override
  public DeviceInfo get() {
    return newInstance();
  }

  public static DeviceInfo_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DeviceInfo newInstance() {
    return new DeviceInfo();
  }

  private static final class InstanceHolder {
    private static final DeviceInfo_Factory INSTANCE = new DeviceInfo_Factory();
  }
}
