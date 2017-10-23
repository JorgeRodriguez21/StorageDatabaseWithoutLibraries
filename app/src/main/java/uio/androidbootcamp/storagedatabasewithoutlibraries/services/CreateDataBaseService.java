package uio.androidbootcamp.storagedatabasewithoutlibraries.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jorge on 09/07/2017.
 */

public class CreateDataBaseService extends SQLiteOpenHelper {

        public static final String TABLE_USER_NAME = "user";
        public static final String COLUMN_ID = "idUser";
        public static final String COLUMN_NAME = "name";

        private static final String DATABASE_NAME = "users.db";
        //Siempre que se haga un cambio se debe actualizar este número.
        private static final int DATABASE_VERSION = 1;

        // Database creation sql statement
        private static final String DATABASE_CREATE = "create table "
                + TABLE_USER_NAME + "( " + COLUMN_ID
                + " integer primary key autoincrement, " + COLUMN_NAME
                + " text not null);";

        public CreateDataBaseService(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            System.out.println("Se actualiza de versión de la base, de la versión "+oldVersion+" a la versión "+newVersion);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_NAME);
            onCreate(db);
        }
    }
