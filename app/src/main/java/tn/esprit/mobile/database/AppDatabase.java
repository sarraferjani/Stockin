package tn.esprit.mobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.mobile.dao.BlogDao;
import tn.esprit.mobile.model.Blog;


@Database(entities = {Blog.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract BlogDao blogDao();
    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "stocin_db")

                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}
