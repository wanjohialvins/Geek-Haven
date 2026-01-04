package com.geekhaven.app.ui.library;

import com.geekhaven.app.domain.repository.BookRepository;
import com.geekhaven.app.domain.usecase.ScanLocalBooksUseCase;
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
public final class LibraryViewModel_Factory implements Factory<LibraryViewModel> {
  private final Provider<BookRepository> bookRepositoryProvider;

  private final Provider<ScanLocalBooksUseCase> scanLocalBooksUseCaseProvider;

  public LibraryViewModel_Factory(Provider<BookRepository> bookRepositoryProvider,
      Provider<ScanLocalBooksUseCase> scanLocalBooksUseCaseProvider) {
    this.bookRepositoryProvider = bookRepositoryProvider;
    this.scanLocalBooksUseCaseProvider = scanLocalBooksUseCaseProvider;
  }

  @Override
  public LibraryViewModel get() {
    return newInstance(bookRepositoryProvider.get(), scanLocalBooksUseCaseProvider.get());
  }

  public static LibraryViewModel_Factory create(Provider<BookRepository> bookRepositoryProvider,
      Provider<ScanLocalBooksUseCase> scanLocalBooksUseCaseProvider) {
    return new LibraryViewModel_Factory(bookRepositoryProvider, scanLocalBooksUseCaseProvider);
  }

  public static LibraryViewModel newInstance(BookRepository bookRepository,
      ScanLocalBooksUseCase scanLocalBooksUseCase) {
    return new LibraryViewModel(bookRepository, scanLocalBooksUseCase);
  }
}
