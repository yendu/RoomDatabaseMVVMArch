package com.yendu.roomdatabasemvvmarch.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yendu.roomdatabasemvvmarch.Models.Word;
import com.yendu.roomdatabasemvvmarch.Repositories.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<List<Word>> mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository=new WordRepository(application);
        mAllWords=mWordRepository.getmAllWords();

    }
    public LiveData<List<Word>>getAllWords(){return mAllWords;}

    public void insert(Word word){
        mWordRepository.insert(word);
    }
    public void delete(Word word){
        mWordRepository.delete(word);
    }
    public void deleteAll(){
        mWordRepository.deleteAll();
    }
}
