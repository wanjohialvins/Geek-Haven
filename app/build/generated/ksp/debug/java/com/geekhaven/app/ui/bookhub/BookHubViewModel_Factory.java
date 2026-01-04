package com.geekhaven.app.ui.bookhub;

import androidx.lifecycle.SavedStateHandle;
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
public final class BookHubViewModel_Factory implements Factory<BookHubViewModel> {
  private final Provider<BookRepository> bookRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public BookHubViewModel_Factory(Provider<BookRepository> bookRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.bookRepositoryProvider = bookRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public BookHubViewModel get() {
    return newInstance(bookRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static BookHubViewModel_Factory create(Provider<BookRepository> bookRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new BookHubViewModel_Factory(bookRepositoryProvider, savedStateHandleProvider);
  }

  public static BookHubViewModel newInstance(BookRepository bookRepository,
      SavedStateHandle savedStateHandle) {
    return new BookHubViewModel(bookRepository, savedStateHandle);
  }
}
