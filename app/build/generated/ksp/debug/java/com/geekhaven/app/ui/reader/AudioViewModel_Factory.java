package com.geekhaven.app.ui.reader;

import android.content.Context;
import com.geekhaven.app.domain.repository.BookRepository;
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
public final class AudioViewModel_Factory implements Factory<AudioViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<BookRepository> bookRepositoryProvider;

  public AudioViewModel_Factory(Provider<Context> contextProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public AudioViewModel get() {
    return newInstance(contextProvider.get(), bookRepositoryProvider.get());
  }

  public static AudioViewModel_Factory create(Provider<Context> contextProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    return new AudioViewModel_Factory(contextProvider, bookRepositoryProvider);
  }

  public static AudioViewModel newInstance(Context context, BookRepository bookRepository) {
    return new AudioViewModel(context, bookRepository);
  }
}
