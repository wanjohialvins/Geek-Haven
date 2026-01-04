package com.geekhaven.app.domain.usecase;

import com.geekhaven.app.data.local.source.LocalFileDataSource;
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
public final class ScanLocalBooksUseCase_Factory implements Factory<ScanLocalBooksUseCase> {
  private final Provider<LocalFileDataSource> localFileDataSourceProvider;

  private final Provider<BookRepository> bookRepositoryProvider;

  public ScanLocalBooksUseCase_Factory(Provider<LocalFileDataSource> localFileDataSourceProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    this.localFileDataSourceProvider = localFileDataSourceProvider;
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public ScanLocalBooksUseCase get() {
    return newInstance(localFileDataSourceProvider.get(), bookRepositoryProvider.get());
  }

  public static ScanLocalBooksUseCase_Factory create(
      Provider<LocalFileDataSource> localFileDataSourceProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    return new ScanLocalBooksUseCase_Factory(localFileDataSourceProvider, bookRepositoryProvider);
  }

  public static ScanLocalBooksUseCase newInstance(LocalFileDataSource localFileDataSource,
      BookRepository bookRepository) {
    return new ScanLocalBooksUseCase(localFileDataSource, bookRepository);
  }
}
