package com.myroili.test;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by abouelmahassineabdelghani on 5/19/18.
 */


public class WordViewModel extends AndroidViewModel {
    private Repository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllWords = mRepository.getmAllWords();
    }
    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }
    public void insert(Word word){
        mRepository.insert(word);
    }
    public void delete(Word word){
        mRepository.delete(word);
    }
}
