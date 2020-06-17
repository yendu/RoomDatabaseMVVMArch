package com.yendu.roomdatabasemvvmarch.Repositories;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.yendu.roomdatabasemvvmarch.Models.Word;
import com.yendu.roomdatabasemvvmarch.Room.Daos.WordDao;
import com.yendu.roomdatabasemvvmarch.Room.WordDatabase;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        WordDatabase db=WordDatabase.getDatabase(application);
        mWordDao=db.wordDao();
        mAllWords=mWordDao.getWords();
    }

    public LiveData<List<Word>>getmAllWords(){
        return  mAllWords;
    }

    public void insert(Word word){
        WordDatabase.databaseWriteExecutor.execute(()->{
                mWordDao.insert(word);}
                );
    }
    public void delete(Word word){
        WordDatabase.databaseWriteExecutor.execute(()->{
            mWordDao.delete(word);
        });
    }
    public void deleteAll(){
        WordDatabase.databaseWriteExecutor.execute(()->{
            mWordDao.deleteAll();
        });
    }
}
