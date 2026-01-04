package com.geekhaven.app.data.repository;

import com.geekhaven.app.data.remote.api.GeminiApi;
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
public final class IntelligenceRepositoryImpl_Factory implements Factory<IntelligenceRepositoryImpl> {
  private final Provider<GeminiApi> apiProvider;

  public IntelligenceRepositoryImpl_Factory(Provider<GeminiApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public IntelligenceRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static IntelligenceRepositoryImpl_Factory create(Provider<GeminiApi> apiProvider) {
    return new IntelligenceRepositoryImpl_Factory(apiProvider);
  }

  public static IntelligenceRepositoryImpl newInstance(GeminiApi api) {
    return new IntelligenceRepositoryImpl(api);
  }
}
