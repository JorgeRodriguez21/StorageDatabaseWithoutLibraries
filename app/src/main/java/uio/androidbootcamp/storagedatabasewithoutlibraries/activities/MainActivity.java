package uio.androidbootcamp.storagedatabasewithoutlibraries.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

import uio.androidbootcamp.storagedatabasewithoutlibraries.R;
import uio.androidbootcamp.storagedatabasewithoutlibraries.models.User;
import uio.androidbootcamp.storagedatabasewithoutlibraries.services.UserDataBaseService;

public class MainActivity extends ListActivity {
    //http://www.vogella.com/tutorials/AndroidSQLite/article.html

    private UserDataBaseService userDataBaseService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDataBaseService = new UserDataBaseService(this);
        userDataBaseService.open();

        List<User> values = userDataBaseService.getAllUsers();

        ArrayAdapter<User> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
        User user = null;
        switch (view.getId()) {
            case R.id.button_add:
                String[] users = new String[] { "Patricia", "Cristina", "Daniel" };
                int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                user = userDataBaseService.createUser(users[nextInt]);
                adapter.add(user);
                break;
            case R.id.button_delete:
                if (getListAdapter().getCount() > 0) {
                    user = (User) getListAdapter().getItem(0);
                    userDataBaseService.deleteUser(user);
                    adapter.remove(user);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        userDataBaseService.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        userDataBaseService.close();
        super.onPause();
    }
}