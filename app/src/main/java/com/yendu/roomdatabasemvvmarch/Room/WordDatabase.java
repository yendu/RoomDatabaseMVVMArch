package com.yendu.roomdatabasemvvmarch.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yendu.roomdatabasemvvmarch.Models.Word;
import com.yendu.roomdatabasemvvmarch.Room.Daos.WordDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Word.class},version = 1)
public abstract class WordDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WordDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (WordDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class,"word_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
