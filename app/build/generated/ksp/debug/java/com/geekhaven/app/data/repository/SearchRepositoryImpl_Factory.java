package com.geekhaven.app.data.repository;

import com.geekhaven.app.data.remote.api.WebSearchApi;
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
public final class SearchRepositoryImpl_Factory implements Factory<SearchRepositoryImpl> {
  private final Provider<WebSearchApi> apiProvider;

  public SearchRepositoryImpl_Factory(Provider<WebSearchApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public SearchRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static SearchRepositoryImpl_Factory create(Provider<WebSearchApi> apiProvider) {
    return new SearchRepositoryImpl_Factory(apiProvider);
  }

  public static SearchRepositoryImpl newInstance(WebSearchApi api) {
    return new SearchRepositoryImpl(api);
  }
}
