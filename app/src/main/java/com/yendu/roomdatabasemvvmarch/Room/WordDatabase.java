package com.yendu.roomdatabasemvvmarch.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            WordDatabase.class,"word_database").addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{

                WordDao wordDao=INSTANCE.wordDao();
                Word word=new Word("hello");
                wordDao.insert(word);
                wordDao.insert(new Word("world"));
                wordDao.insert(new Word("Hello"));
            });

        }
    };

}
