package com.geekhaven.app.ui.profile;

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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<BookRepository> bookRepositoryProvider;

  public ProfileViewModel_Factory(Provider<BookRepository> bookRepositoryProvider) {
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(bookRepositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<BookRepository> bookRepositoryProvider) {
    return new ProfileViewModel_Factory(bookRepositoryProvider);
  }

  public static ProfileViewModel newInstance(BookRepository bookRepository) {
    return new ProfileViewModel(bookRepository);
  }
}
