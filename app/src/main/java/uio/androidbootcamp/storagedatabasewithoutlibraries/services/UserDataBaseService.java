package uio.androidbootcamp.storagedatabasewithoutlibraries.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import uio.androidbootcamp.storagedatabasewithoutlibraries.models.User;


/**
 * Created by Jorge on 09/07/2017.
 */

public class UserDataBaseService {

        // Database fields
        private SQLiteDatabase database;
        private CreateDataBaseService createDataBaseService;
        private String[] allColumns = { CreateDataBaseService.COLUMN_ID,
                CreateDataBaseService.COLUMN_NAME };

        public UserDataBaseService(Context context) {
            createDataBaseService = new CreateDataBaseService(context);
        }

        public void open() throws SQLException {
            database = createDataBaseService.getWritableDatabase();
        }

        public void close() {
            createDataBaseService.close();
        }

        public User createUser(String name) {
            ContentValues values = new ContentValues();
            values.put(CreateDataBaseService.COLUMN_NAME, name);
            long insertId = database.insert(CreateDataBaseService.TABLE_USER_NAME, null,
                    values);
            Cursor cursor = database.query(CreateDataBaseService.TABLE_USER_NAME,
                    allColumns, CreateDataBaseService.COLUMN_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            User newUser = cursorToUser(cursor);
            cursor.close();
            return newUser;
        }

        public void deleteUser(User user) {
            long id = user.getIdUser();
            database.delete(CreateDataBaseService.TABLE_USER_NAME, CreateDataBaseService.COLUMN_ID
                    + " = " + id, null);
        }

        public List<User> getAllUsers() {
            List<User> users = new ArrayList<User>();

            Cursor cursor = database.query(CreateDataBaseService.TABLE_USER_NAME,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = cursorToUser(cursor);
                users.add(user);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return users;
        }

        private User cursorToUser(Cursor cursor) {
            User user = new User();
            user.setIdUser(cursor.getLong(0));
            user.setName(cursor.getString(1));
            return user;
        }
    }
