package com.myroili.test;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by abouelmahassineabdelghani on 5/19/18.
 */

public class Repository {
    private DaoWord mWordDao;
    private LiveData<List<Word>> mAllWords;

    Repository(Application application) {
        WordDataBase db= WordDataBase.getInstance(application);
        mWordDao = db.daoWord();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }
    public void insert (Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }
    public void delete(Word word){
        new DeleteAsyncTask(mWordDao).execute(word);
    }



    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private DaoWord mAsyncTaskDao;

        InsertAsyncTask(DaoWord dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private DaoWord mAsyncTaskDao;

        DeleteAsyncTask(DaoWord dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
