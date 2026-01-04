package com.geekhaven.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.geekhaven.app.data.local.entity.BookEntity;
import com.geekhaven.app.data.local.entity.Converters;
import com.geekhaven.app.data.local.entity.ReadingStatus;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookEntity> __insertionAdapterOfBookEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<BookEntity> __deletionAdapterOfBookEntity;

  private final EntityDeletionOrUpdateAdapter<BookEntity> __updateAdapterOfBookEntity;

  public BookDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookEntity = new EntityInsertionAdapter<BookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `books` (`id`,`googleBookId`,`title`,`authors`,`description`,`isbn`,`coverUrl`,`publisher`,`publishedDate`,`isSeries`,`seriesName`,`seriesIndex`,`notes`,`isFavorite`,`addedDate`,`readingStatus`,`progressPercentage`,`lastReadTime`,`currentPage`,`audioPosition`,`memoryAnchor`,`pdfUri`,`epubUri`,`audioUri`,`hasPdf`,`hasEpub`,`hasAudio`,`hasPhysical`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getGoogleBookId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getGoogleBookId());
        }
        statement.bindString(3, entity.getTitle());
        final String _tmp = __converters.fromStringList(entity.getAuthors());
        statement.bindString(4, _tmp);
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getIsbn() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIsbn());
        }
        if (entity.getCoverUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCoverUrl());
        }
        if (entity.getPublisher() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPublisher());
        }
        if (entity.getPublishedDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPublishedDate());
        }
        final int _tmp_1 = entity.isSeries() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getSeriesName() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getSeriesName());
        }
        if (entity.getSeriesIndex() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getSeriesIndex());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        final int _tmp_2 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(14, _tmp_2);
        statement.bindLong(15, entity.getAddedDate());
        final String _tmp_3 = __converters.fromReadingStatus(entity.getReadingStatus());
        statement.bindString(16, _tmp_3);
        statement.bindLong(17, entity.getProgressPercentage());
        statement.bindLong(18, entity.getLastReadTime());
        statement.bindLong(19, entity.getCurrentPage());
        statement.bindLong(20, entity.getAudioPosition());
        if (entity.getMemoryAnchor() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getMemoryAnchor());
        }
        if (entity.getPdfUri() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getPdfUri());
        }
        if (entity.getEpubUri() == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, entity.getEpubUri());
        }
        if (entity.getAudioUri() == null) {
          statement.bindNull(24);
        } else {
          statement.bindString(24, entity.getAudioUri());
        }
        final int _tmp_4 = entity.getHasPdf() ? 1 : 0;
        statement.bindLong(25, _tmp_4);
        final int _tmp_5 = entity.getHasEpub() ? 1 : 0;
        statement.bindLong(26, _tmp_5);
        final int _tmp_6 = entity.getHasAudio() ? 1 : 0;
        statement.bindLong(27, _tmp_6);
        final int _tmp_7 = entity.getHasPhysical() ? 1 : 0;
        statement.bindLong(28, _tmp_7);
      }
    };
    this.__deletionAdapterOfBookEntity = new EntityDeletionOrUpdateAdapter<BookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `books` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfBookEntity = new EntityDeletionOrUpdateAdapter<BookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `books` SET `id` = ?,`googleBookId` = ?,`title` = ?,`authors` = ?,`description` = ?,`isbn` = ?,`coverUrl` = ?,`publisher` = ?,`publishedDate` = ?,`isSeries` = ?,`seriesName` = ?,`seriesIndex` = ?,`notes` = ?,`isFavorite` = ?,`addedDate` = ?,`readingStatus` = ?,`progressPercentage` = ?,`lastReadTime` = ?,`currentPage` = ?,`audioPosition` = ?,`memoryAnchor` = ?,`pdfUri` = ?,`epubUri` = ?,`audioUri` = ?,`hasPdf` = ?,`hasEpub` = ?,`hasAudio` = ?,`hasPhysical` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getGoogleBookId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getGoogleBookId());
        }
        statement.bindString(3, entity.getTitle());
        final String _tmp = __converters.fromStringList(entity.getAuthors());
        statement.bindString(4, _tmp);
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getIsbn() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getIsbn());
        }
        if (entity.getCoverUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCoverUrl());
        }
        if (entity.getPublisher() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPublisher());
        }
        if (entity.getPublishedDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPublishedDate());
        }
        final int _tmp_1 = entity.isSeries() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getSeriesName() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getSeriesName());
        }
        if (entity.getSeriesIndex() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getSeriesIndex());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        final int _tmp_2 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(14, _tmp_2);
        statement.bindLong(15, entity.getAddedDate());
        final String _tmp_3 = __converters.fromReadingStatus(entity.getReadingStatus());
        statement.bindString(16, _tmp_3);
        statement.bindLong(17, entity.getProgressPercentage());
        statement.bindLong(18, entity.getLastReadTime());
        statement.bindLong(19, entity.getCurrentPage());
        statement.bindLong(20, entity.getAudioPosition());
        if (entity.getMemoryAnchor() == null) {
          statement.bindNull(21);
        } else {
          statement.bindString(21, entity.getMemoryAnchor());
        }
        if (entity.getPdfUri() == null) {
          statement.bindNull(22);
        } else {
          statement.bindString(22, entity.getPdfUri());
        }
        if (entity.getEpubUri() == null) {
          statement.bindNull(23);
        } else {
          statement.bindString(23, entity.getEpubUri());
        }
        if (entity.getAudioUri() == null) {
          statement.bindNull(24);
        } else {
          statement.bindString(24, entity.getAudioUri());
        }
        final int _tmp_4 = entity.getHasPdf() ? 1 : 0;
        statement.bindLong(25, _tmp_4);
        final int _tmp_5 = entity.getHasEpub() ? 1 : 0;
        statement.bindLong(26, _tmp_5);
        final int _tmp_6 = entity.getHasAudio() ? 1 : 0;
        statement.bindLong(27, _tmp_6);
        final int _tmp_7 = entity.getHasPhysical() ? 1 : 0;
        statement.bindLong(28, _tmp_7);
        statement.bindLong(29, entity.getId());
      }
    };
  }

  @Override
  public Object insertBook(final BookEntity book, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfBookEntity.insertAndReturnId(book);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBook(final BookEntity book, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBookEntity.handle(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBook(final BookEntity book, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBookEntity.handle(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookEntity>> getAllBooks() {
    final String _sql = "SELECT * FROM books ORDER BY lastReadTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"books"}, new Callable<List<BookEntity>>() {
      @Override
      @NonNull
      public List<BookEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfGoogleBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleBookId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthors = CursorUtil.getColumnIndexOrThrow(_cursor, "authors");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfPublishedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedDate");
          final int _cursorIndexOfIsSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "isSeries");
          final int _cursorIndexOfSeriesName = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesName");
          final int _cursorIndexOfSeriesIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesIndex");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final int _cursorIndexOfReadingStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "readingStatus");
          final int _cursorIndexOfProgressPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "progressPercentage");
          final int _cursorIndexOfLastReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReadTime");
          final int _cursorIndexOfCurrentPage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPage");
          final int _cursorIndexOfAudioPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "audioPosition");
          final int _cursorIndexOfMemoryAnchor = CursorUtil.getColumnIndexOrThrow(_cursor, "memoryAnchor");
          final int _cursorIndexOfPdfUri = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfUri");
          final int _cursorIndexOfEpubUri = CursorUtil.getColumnIndexOrThrow(_cursor, "epubUri");
          final int _cursorIndexOfAudioUri = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUri");
          final int _cursorIndexOfHasPdf = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPdf");
          final int _cursorIndexOfHasEpub = CursorUtil.getColumnIndexOrThrow(_cursor, "hasEpub");
          final int _cursorIndexOfHasAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "hasAudio");
          final int _cursorIndexOfHasPhysical = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPhysical");
          final List<BookEntity> _result = new ArrayList<BookEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpGoogleBookId;
            if (_cursor.isNull(_cursorIndexOfGoogleBookId)) {
              _tmpGoogleBookId = null;
            } else {
              _tmpGoogleBookId = _cursor.getString(_cursorIndexOfGoogleBookId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final List<String> _tmpAuthors;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfAuthors);
            _tmpAuthors = __converters.toStringList(_tmp);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIsbn;
            if (_cursor.isNull(_cursorIndexOfIsbn)) {
              _tmpIsbn = null;
            } else {
              _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpPublishedDate;
            if (_cursor.isNull(_cursorIndexOfPublishedDate)) {
              _tmpPublishedDate = null;
            } else {
              _tmpPublishedDate = _cursor.getString(_cursorIndexOfPublishedDate);
            }
            final boolean _tmpIsSeries;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSeries);
            _tmpIsSeries = _tmp_1 != 0;
            final String _tmpSeriesName;
            if (_cursor.isNull(_cursorIndexOfSeriesName)) {
              _tmpSeriesName = null;
            } else {
              _tmpSeriesName = _cursor.getString(_cursorIndexOfSeriesName);
            }
            final Double _tmpSeriesIndex;
            if (_cursor.isNull(_cursorIndexOfSeriesIndex)) {
              _tmpSeriesIndex = null;
            } else {
              _tmpSeriesIndex = _cursor.getDouble(_cursorIndexOfSeriesIndex);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            final long _tmpAddedDate;
            _tmpAddedDate = _cursor.getLong(_cursorIndexOfAddedDate);
            final ReadingStatus _tmpReadingStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfReadingStatus);
            _tmpReadingStatus = __converters.toReadingStatus(_tmp_3);
            final int _tmpProgressPercentage;
            _tmpProgressPercentage = _cursor.getInt(_cursorIndexOfProgressPercentage);
            final long _tmpLastReadTime;
            _tmpLastReadTime = _cursor.getLong(_cursorIndexOfLastReadTime);
            final int _tmpCurrentPage;
            _tmpCurrentPage = _cursor.getInt(_cursorIndexOfCurrentPage);
            final long _tmpAudioPosition;
            _tmpAudioPosition = _cursor.getLong(_cursorIndexOfAudioPosition);
            final String _tmpMemoryAnchor;
            if (_cursor.isNull(_cursorIndexOfMemoryAnchor)) {
              _tmpMemoryAnchor = null;
            } else {
              _tmpMemoryAnchor = _cursor.getString(_cursorIndexOfMemoryAnchor);
            }
            final String _tmpPdfUri;
            if (_cursor.isNull(_cursorIndexOfPdfUri)) {
              _tmpPdfUri = null;
            } else {
              _tmpPdfUri = _cursor.getString(_cursorIndexOfPdfUri);
            }
            final String _tmpEpubUri;
            if (_cursor.isNull(_cursorIndexOfEpubUri)) {
              _tmpEpubUri = null;
            } else {
              _tmpEpubUri = _cursor.getString(_cursorIndexOfEpubUri);
            }
            final String _tmpAudioUri;
            if (_cursor.isNull(_cursorIndexOfAudioUri)) {
              _tmpAudioUri = null;
            } else {
              _tmpAudioUri = _cursor.getString(_cursorIndexOfAudioUri);
            }
            final boolean _tmpHasPdf;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfHasPdf);
            _tmpHasPdf = _tmp_4 != 0;
            final boolean _tmpHasEpub;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfHasEpub);
            _tmpHasEpub = _tmp_5 != 0;
            final boolean _tmpHasAudio;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfHasAudio);
            _tmpHasAudio = _tmp_6 != 0;
            final boolean _tmpHasPhysical;
            final int _tmp_7;
            _tmp_7 = _cursor.getInt(_cursorIndexOfHasPhysical);
            _tmpHasPhysical = _tmp_7 != 0;
            _item = new BookEntity(_tmpId,_tmpGoogleBookId,_tmpTitle,_tmpAuthors,_tmpDescription,_tmpIsbn,_tmpCoverUrl,_tmpPublisher,_tmpPublishedDate,_tmpIsSeries,_tmpSeriesName,_tmpSeriesIndex,_tmpNotes,_tmpIsFavorite,_tmpAddedDate,_tmpReadingStatus,_tmpProgressPercentage,_tmpLastReadTime,_tmpCurrentPage,_tmpAudioPosition,_tmpMemoryAnchor,_tmpPdfUri,_tmpEpubUri,_tmpAudioUri,_tmpHasPdf,_tmpHasEpub,_tmpHasAudio,_tmpHasPhysical);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getBookById(final long id, final Continuation<? super BookEntity> $completion) {
    final String _sql = "SELECT * FROM books WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BookEntity>() {
      @Override
      @Nullable
      public BookEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfGoogleBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleBookId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthors = CursorUtil.getColumnIndexOrThrow(_cursor, "authors");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfPublishedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedDate");
          final int _cursorIndexOfIsSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "isSeries");
          final int _cursorIndexOfSeriesName = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesName");
          final int _cursorIndexOfSeriesIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesIndex");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final int _cursorIndexOfReadingStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "readingStatus");
          final int _cursorIndexOfProgressPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "progressPercentage");
          final int _cursorIndexOfLastReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReadTime");
          final int _cursorIndexOfCurrentPage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPage");
          final int _cursorIndexOfAudioPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "audioPosition");
          final int _cursorIndexOfMemoryAnchor = CursorUtil.getColumnIndexOrThrow(_cursor, "memoryAnchor");
          final int _cursorIndexOfPdfUri = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfUri");
          final int _cursorIndexOfEpubUri = CursorUtil.getColumnIndexOrThrow(_cursor, "epubUri");
          final int _cursorIndexOfAudioUri = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUri");
          final int _cursorIndexOfHasPdf = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPdf");
          final int _cursorIndexOfHasEpub = CursorUtil.getColumnIndexOrThrow(_cursor, "hasEpub");
          final int _cursorIndexOfHasAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "hasAudio");
          final int _cursorIndexOfHasPhysical = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPhysical");
          final BookEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpGoogleBookId;
            if (_cursor.isNull(_cursorIndexOfGoogleBookId)) {
              _tmpGoogleBookId = null;
            } else {
              _tmpGoogleBookId = _cursor.getString(_cursorIndexOfGoogleBookId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final List<String> _tmpAuthors;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfAuthors);
            _tmpAuthors = __converters.toStringList(_tmp);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIsbn;
            if (_cursor.isNull(_cursorIndexOfIsbn)) {
              _tmpIsbn = null;
            } else {
              _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpPublishedDate;
            if (_cursor.isNull(_cursorIndexOfPublishedDate)) {
              _tmpPublishedDate = null;
            } else {
              _tmpPublishedDate = _cursor.getString(_cursorIndexOfPublishedDate);
            }
            final boolean _tmpIsSeries;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSeries);
            _tmpIsSeries = _tmp_1 != 0;
            final String _tmpSeriesName;
            if (_cursor.isNull(_cursorIndexOfSeriesName)) {
              _tmpSeriesName = null;
            } else {
              _tmpSeriesName = _cursor.getString(_cursorIndexOfSeriesName);
            }
            final Double _tmpSeriesIndex;
            if (_cursor.isNull(_cursorIndexOfSeriesIndex)) {
              _tmpSeriesIndex = null;
            } else {
              _tmpSeriesIndex = _cursor.getDouble(_cursorIndexOfSeriesIndex);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            final long _tmpAddedDate;
            _tmpAddedDate = _cursor.getLong(_cursorIndexOfAddedDate);
            final ReadingStatus _tmpReadingStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfReadingStatus);
            _tmpReadingStatus = __converters.toReadingStatus(_tmp_3);
            final int _tmpProgressPercentage;
            _tmpProgressPercentage = _cursor.getInt(_cursorIndexOfProgressPercentage);
            final long _tmpLastReadTime;
            _tmpLastReadTime = _cursor.getLong(_cursorIndexOfLastReadTime);
            final int _tmpCurrentPage;
            _tmpCurrentPage = _cursor.getInt(_cursorIndexOfCurrentPage);
            final long _tmpAudioPosition;
            _tmpAudioPosition = _cursor.getLong(_cursorIndexOfAudioPosition);
            final String _tmpMemoryAnchor;
            if (_cursor.isNull(_cursorIndexOfMemoryAnchor)) {
              _tmpMemoryAnchor = null;
            } else {
              _tmpMemoryAnchor = _cursor.getString(_cursorIndexOfMemoryAnchor);
            }
            final String _tmpPdfUri;
            if (_cursor.isNull(_cursorIndexOfPdfUri)) {
              _tmpPdfUri = null;
            } else {
              _tmpPdfUri = _cursor.getString(_cursorIndexOfPdfUri);
            }
            final String _tmpEpubUri;
            if (_cursor.isNull(_cursorIndexOfEpubUri)) {
              _tmpEpubUri = null;
            } else {
              _tmpEpubUri = _cursor.getString(_cursorIndexOfEpubUri);
            }
            final String _tmpAudioUri;
            if (_cursor.isNull(_cursorIndexOfAudioUri)) {
              _tmpAudioUri = null;
            } else {
              _tmpAudioUri = _cursor.getString(_cursorIndexOfAudioUri);
            }
            final boolean _tmpHasPdf;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfHasPdf);
            _tmpHasPdf = _tmp_4 != 0;
            final boolean _tmpHasEpub;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfHasEpub);
            _tmpHasEpub = _tmp_5 != 0;
            final boolean _tmpHasAudio;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfHasAudio);
            _tmpHasAudio = _tmp_6 != 0;
            final boolean _tmpHasPhysical;
            final int _tmp_7;
            _tmp_7 = _cursor.getInt(_cursorIndexOfHasPhysical);
            _tmpHasPhysical = _tmp_7 != 0;
            _result = new BookEntity(_tmpId,_tmpGoogleBookId,_tmpTitle,_tmpAuthors,_tmpDescription,_tmpIsbn,_tmpCoverUrl,_tmpPublisher,_tmpPublishedDate,_tmpIsSeries,_tmpSeriesName,_tmpSeriesIndex,_tmpNotes,_tmpIsFavorite,_tmpAddedDate,_tmpReadingStatus,_tmpProgressPercentage,_tmpLastReadTime,_tmpCurrentPage,_tmpAudioPosition,_tmpMemoryAnchor,_tmpPdfUri,_tmpEpubUri,_tmpAudioUri,_tmpHasPdf,_tmpHasEpub,_tmpHasAudio,_tmpHasPhysical);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getBookByGoogleId(final String googleId,
      final Continuation<? super BookEntity> $completion) {
    final String _sql = "SELECT * FROM books WHERE googleBookId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, googleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BookEntity>() {
      @Override
      @Nullable
      public BookEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfGoogleBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleBookId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthors = CursorUtil.getColumnIndexOrThrow(_cursor, "authors");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfPublishedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedDate");
          final int _cursorIndexOfIsSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "isSeries");
          final int _cursorIndexOfSeriesName = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesName");
          final int _cursorIndexOfSeriesIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesIndex");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final int _cursorIndexOfReadingStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "readingStatus");
          final int _cursorIndexOfProgressPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "progressPercentage");
          final int _cursorIndexOfLastReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReadTime");
          final int _cursorIndexOfCurrentPage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPage");
          final int _cursorIndexOfAudioPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "audioPosition");
          final int _cursorIndexOfMemoryAnchor = CursorUtil.getColumnIndexOrThrow(_cursor, "memoryAnchor");
          final int _cursorIndexOfPdfUri = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfUri");
          final int _cursorIndexOfEpubUri = CursorUtil.getColumnIndexOrThrow(_cursor, "epubUri");
          final int _cursorIndexOfAudioUri = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUri");
          final int _cursorIndexOfHasPdf = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPdf");
          final int _cursorIndexOfHasEpub = CursorUtil.getColumnIndexOrThrow(_cursor, "hasEpub");
          final int _cursorIndexOfHasAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "hasAudio");
          final int _cursorIndexOfHasPhysical = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPhysical");
          final BookEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpGoogleBookId;
            if (_cursor.isNull(_cursorIndexOfGoogleBookId)) {
              _tmpGoogleBookId = null;
            } else {
              _tmpGoogleBookId = _cursor.getString(_cursorIndexOfGoogleBookId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final List<String> _tmpAuthors;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfAuthors);
            _tmpAuthors = __converters.toStringList(_tmp);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIsbn;
            if (_cursor.isNull(_cursorIndexOfIsbn)) {
              _tmpIsbn = null;
            } else {
              _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpPublishedDate;
            if (_cursor.isNull(_cursorIndexOfPublishedDate)) {
              _tmpPublishedDate = null;
            } else {
              _tmpPublishedDate = _cursor.getString(_cursorIndexOfPublishedDate);
            }
            final boolean _tmpIsSeries;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSeries);
            _tmpIsSeries = _tmp_1 != 0;
            final String _tmpSeriesName;
            if (_cursor.isNull(_cursorIndexOfSeriesName)) {
              _tmpSeriesName = null;
            } else {
              _tmpSeriesName = _cursor.getString(_cursorIndexOfSeriesName);
            }
            final Double _tmpSeriesIndex;
            if (_cursor.isNull(_cursorIndexOfSeriesIndex)) {
              _tmpSeriesIndex = null;
            } else {
              _tmpSeriesIndex = _cursor.getDouble(_cursorIndexOfSeriesIndex);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            final long _tmpAddedDate;
            _tmpAddedDate = _cursor.getLong(_cursorIndexOfAddedDate);
            final ReadingStatus _tmpReadingStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfReadingStatus);
            _tmpReadingStatus = __converters.toReadingStatus(_tmp_3);
            final int _tmpProgressPercentage;
            _tmpProgressPercentage = _cursor.getInt(_cursorIndexOfProgressPercentage);
            final long _tmpLastReadTime;
            _tmpLastReadTime = _cursor.getLong(_cursorIndexOfLastReadTime);
            final int _tmpCurrentPage;
            _tmpCurrentPage = _cursor.getInt(_cursorIndexOfCurrentPage);
            final long _tmpAudioPosition;
            _tmpAudioPosition = _cursor.getLong(_cursorIndexOfAudioPosition);
            final String _tmpMemoryAnchor;
            if (_cursor.isNull(_cursorIndexOfMemoryAnchor)) {
              _tmpMemoryAnchor = null;
            } else {
              _tmpMemoryAnchor = _cursor.getString(_cursorIndexOfMemoryAnchor);
            }
            final String _tmpPdfUri;
            if (_cursor.isNull(_cursorIndexOfPdfUri)) {
              _tmpPdfUri = null;
            } else {
              _tmpPdfUri = _cursor.getString(_cursorIndexOfPdfUri);
            }
            final String _tmpEpubUri;
            if (_cursor.isNull(_cursorIndexOfEpubUri)) {
              _tmpEpubUri = null;
            } else {
              _tmpEpubUri = _cursor.getString(_cursorIndexOfEpubUri);
            }
            final String _tmpAudioUri;
            if (_cursor.isNull(_cursorIndexOfAudioUri)) {
              _tmpAudioUri = null;
            } else {
              _tmpAudioUri = _cursor.getString(_cursorIndexOfAudioUri);
            }
            final boolean _tmpHasPdf;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfHasPdf);
            _tmpHasPdf = _tmp_4 != 0;
            final boolean _tmpHasEpub;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfHasEpub);
            _tmpHasEpub = _tmp_5 != 0;
            final boolean _tmpHasAudio;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfHasAudio);
            _tmpHasAudio = _tmp_6 != 0;
            final boolean _tmpHasPhysical;
            final int _tmp_7;
            _tmp_7 = _cursor.getInt(_cursorIndexOfHasPhysical);
            _tmpHasPhysical = _tmp_7 != 0;
            _result = new BookEntity(_tmpId,_tmpGoogleBookId,_tmpTitle,_tmpAuthors,_tmpDescription,_tmpIsbn,_tmpCoverUrl,_tmpPublisher,_tmpPublishedDate,_tmpIsSeries,_tmpSeriesName,_tmpSeriesIndex,_tmpNotes,_tmpIsFavorite,_tmpAddedDate,_tmpReadingStatus,_tmpProgressPercentage,_tmpLastReadTime,_tmpCurrentPage,_tmpAudioPosition,_tmpMemoryAnchor,_tmpPdfUri,_tmpEpubUri,_tmpAudioUri,_tmpHasPdf,_tmpHasEpub,_tmpHasAudio,_tmpHasPhysical);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookEntity>> searchBooks(final String query) {
    final String _sql = "SELECT * FROM books WHERE title LIKE '%' || ? || '%' OR authors LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"books"}, new Callable<List<BookEntity>>() {
      @Override
      @NonNull
      public List<BookEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfGoogleBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "googleBookId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthors = CursorUtil.getColumnIndexOrThrow(_cursor, "authors");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIsbn = CursorUtil.getColumnIndexOrThrow(_cursor, "isbn");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfPublishedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedDate");
          final int _cursorIndexOfIsSeries = CursorUtil.getColumnIndexOrThrow(_cursor, "isSeries");
          final int _cursorIndexOfSeriesName = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesName");
          final int _cursorIndexOfSeriesIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "seriesIndex");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfAddedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "addedDate");
          final int _cursorIndexOfReadingStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "readingStatus");
          final int _cursorIndexOfProgressPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "progressPercentage");
          final int _cursorIndexOfLastReadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReadTime");
          final int _cursorIndexOfCurrentPage = CursorUtil.getColumnIndexOrThrow(_cursor, "currentPage");
          final int _cursorIndexOfAudioPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "audioPosition");
          final int _cursorIndexOfMemoryAnchor = CursorUtil.getColumnIndexOrThrow(_cursor, "memoryAnchor");
          final int _cursorIndexOfPdfUri = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfUri");
          final int _cursorIndexOfEpubUri = CursorUtil.getColumnIndexOrThrow(_cursor, "epubUri");
          final int _cursorIndexOfAudioUri = CursorUtil.getColumnIndexOrThrow(_cursor, "audioUri");
          final int _cursorIndexOfHasPdf = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPdf");
          final int _cursorIndexOfHasEpub = CursorUtil.getColumnIndexOrThrow(_cursor, "hasEpub");
          final int _cursorIndexOfHasAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "hasAudio");
          final int _cursorIndexOfHasPhysical = CursorUtil.getColumnIndexOrThrow(_cursor, "hasPhysical");
          final List<BookEntity> _result = new ArrayList<BookEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpGoogleBookId;
            if (_cursor.isNull(_cursorIndexOfGoogleBookId)) {
              _tmpGoogleBookId = null;
            } else {
              _tmpGoogleBookId = _cursor.getString(_cursorIndexOfGoogleBookId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final List<String> _tmpAuthors;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfAuthors);
            _tmpAuthors = __converters.toStringList(_tmp);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpIsbn;
            if (_cursor.isNull(_cursorIndexOfIsbn)) {
              _tmpIsbn = null;
            } else {
              _tmpIsbn = _cursor.getString(_cursorIndexOfIsbn);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpPublishedDate;
            if (_cursor.isNull(_cursorIndexOfPublishedDate)) {
              _tmpPublishedDate = null;
            } else {
              _tmpPublishedDate = _cursor.getString(_cursorIndexOfPublishedDate);
            }
            final boolean _tmpIsSeries;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsSeries);
            _tmpIsSeries = _tmp_1 != 0;
            final String _tmpSeriesName;
            if (_cursor.isNull(_cursorIndexOfSeriesName)) {
              _tmpSeriesName = null;
            } else {
              _tmpSeriesName = _cursor.getString(_cursorIndexOfSeriesName);
            }
            final Double _tmpSeriesIndex;
            if (_cursor.isNull(_cursorIndexOfSeriesIndex)) {
              _tmpSeriesIndex = null;
            } else {
              _tmpSeriesIndex = _cursor.getDouble(_cursorIndexOfSeriesIndex);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_2 != 0;
            final long _tmpAddedDate;
            _tmpAddedDate = _cursor.getLong(_cursorIndexOfAddedDate);
            final ReadingStatus _tmpReadingStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfReadingStatus);
            _tmpReadingStatus = __converters.toReadingStatus(_tmp_3);
            final int _tmpProgressPercentage;
            _tmpProgressPercentage = _cursor.getInt(_cursorIndexOfProgressPercentage);
            final long _tmpLastReadTime;
            _tmpLastReadTime = _cursor.getLong(_cursorIndexOfLastReadTime);
            final int _tmpCurrentPage;
            _tmpCurrentPage = _cursor.getInt(_cursorIndexOfCurrentPage);
            final long _tmpAudioPosition;
            _tmpAudioPosition = _cursor.getLong(_cursorIndexOfAudioPosition);
            final String _tmpMemoryAnchor;
            if (_cursor.isNull(_cursorIndexOfMemoryAnchor)) {
              _tmpMemoryAnchor = null;
            } else {
              _tmpMemoryAnchor = _cursor.getString(_cursorIndexOfMemoryAnchor);
            }
            final String _tmpPdfUri;
            if (_cursor.isNull(_cursorIndexOfPdfUri)) {
              _tmpPdfUri = null;
            } else {
              _tmpPdfUri = _cursor.getString(_cursorIndexOfPdfUri);
            }
            final String _tmpEpubUri;
            if (_cursor.isNull(_cursorIndexOfEpubUri)) {
              _tmpEpubUri = null;
            } else {
              _tmpEpubUri = _cursor.getString(_cursorIndexOfEpubUri);
            }
            final String _tmpAudioUri;
            if (_cursor.isNull(_cursorIndexOfAudioUri)) {
              _tmpAudioUri = null;
            } else {
              _tmpAudioUri = _cursor.getString(_cursorIndexOfAudioUri);
            }
            final boolean _tmpHasPdf;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfHasPdf);
            _tmpHasPdf = _tmp_4 != 0;
            final boolean _tmpHasEpub;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfHasEpub);
            _tmpHasEpub = _tmp_5 != 0;
            final boolean _tmpHasAudio;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfHasAudio);
            _tmpHasAudio = _tmp_6 != 0;
            final boolean _tmpHasPhysical;
            final int _tmp_7;
            _tmp_7 = _cursor.getInt(_cursorIndexOfHasPhysical);
            _tmpHasPhysical = _tmp_7 != 0;
            _item = new BookEntity(_tmpId,_tmpGoogleBookId,_tmpTitle,_tmpAuthors,_tmpDescription,_tmpIsbn,_tmpCoverUrl,_tmpPublisher,_tmpPublishedDate,_tmpIsSeries,_tmpSeriesName,_tmpSeriesIndex,_tmpNotes,_tmpIsFavorite,_tmpAddedDate,_tmpReadingStatus,_tmpProgressPercentage,_tmpLastReadTime,_tmpCurrentPage,_tmpAudioPosition,_tmpMemoryAnchor,_tmpPdfUri,_tmpEpubUri,_tmpAudioUri,_tmpHasPdf,_tmpHasEpub,_tmpHasAudio,_tmpHasPhysical);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
