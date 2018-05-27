package com.myroili.test;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by abouelmahassineabdelghani on 5/19/18.
 */

@Database(entities = {Word.class},version = 1)
public  abstract class WordDataBase extends RoomDatabase {
    public abstract DaoWord daoWord();
    private static WordDataBase INSTANCE;
    static WordDataBase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (WordDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WordDataBase.class,"word_database")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
