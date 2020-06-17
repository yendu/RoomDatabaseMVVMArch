package com.yendu.roomdatabasemvvmarch.Room.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yendu.roomdatabasemvvmarch.Models.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);
    @Delete
    void delete(Word delete);
    @Query("DELETE from word_table")
    void deleteAll();
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>>getWords();
}
