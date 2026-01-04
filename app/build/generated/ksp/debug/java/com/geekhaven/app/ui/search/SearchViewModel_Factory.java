package com.geekhaven.app.ui.search;

import com.geekhaven.app.domain.repository.BookRepository;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<BookRepository> bookRepositoryProvider;

  public SearchViewModel_Factory(Provider<BookRepository> bookRepositoryProvider) {
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(bookRepositoryProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<BookRepository> bookRepositoryProvider) {
    return new SearchViewModel_Factory(bookRepositoryProvider);
  }

  public static SearchViewModel newInstance(BookRepository bookRepository) {
    return new SearchViewModel(bookRepository);
  }
}
