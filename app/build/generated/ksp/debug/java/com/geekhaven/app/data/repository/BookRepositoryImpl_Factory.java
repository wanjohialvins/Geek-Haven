package com.geekhaven.app.data.repository;

import com.geekhaven.app.data.local.dao.BookDao;
import com.geekhaven.app.data.remote.api.GoogleBooksApi;
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
public final class BookRepositoryImpl_Factory implements Factory<BookRepositoryImpl> {
  private final Provider<BookDao> bookDaoProvider;

  private final Provider<GoogleBooksApi> apiProvider;

  public BookRepositoryImpl_Factory(Provider<BookDao> bookDaoProvider,
      Provider<GoogleBooksApi> apiProvider) {
    this.bookDaoProvider = bookDaoProvider;
    this.apiProvider = apiProvider;
  }

  @Override
  public BookRepositoryImpl get() {
    return newInstance(bookDaoProvider.get(), apiProvider.get());
  }

  public static BookRepositoryImpl_Factory create(Provider<BookDao> bookDaoProvider,
      Provider<GoogleBooksApi> apiProvider) {
    return new BookRepositoryImpl_Factory(bookDaoProvider, apiProvider);
  }

  public static BookRepositoryImpl newInstance(BookDao bookDao, GoogleBooksApi api) {
    return new BookRepositoryImpl(bookDao, api);
  }
}
