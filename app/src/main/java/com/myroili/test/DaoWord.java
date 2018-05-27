package com.myroili.test;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by abouelmahassineabdelghani on 5/19/18.
 */

@Dao
public interface DaoWord {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word... word);

   @Delete
   void delete(Word... words);

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
