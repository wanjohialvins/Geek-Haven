package com.geekhaven.app.data.local.source;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LocalFileDataSource_Factory implements Factory<LocalFileDataSource> {
  private final Provider<Context> contextProvider;

  public LocalFileDataSource_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocalFileDataSource get() {
    return newInstance(contextProvider.get());
  }

  public static LocalFileDataSource_Factory create(Provider<Context> contextProvider) {
    return new LocalFileDataSource_Factory(contextProvider);
  }

  public static LocalFileDataSource newInstance(Context context) {
    return new LocalFileDataSource(context);
  }
}
