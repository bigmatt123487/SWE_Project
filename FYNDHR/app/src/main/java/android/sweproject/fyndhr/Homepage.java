package android.sweproject.fyndhr;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;


public class Homepage extends AppCompatActivity {
    DatabaseHelper myDb;
    private Button mCreateAccount;
    private SearchView searchView;
    private String userin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        myDb = new DatabaseHelper(this);
        mCreateAccount = findViewById(R.id.CreateAButton);
        searchView = findViewById(R.id.search_view);
        Intent intent = getIntent();
        intent.getExtras();
        userin = intent.getStringExtra("User");

        /**
         * If the account was successfully created, you are logged in and the Create Account button
         * has been disabled.
         */
        if (intent.hasExtra("Disable")) {
            mCreateAccount.setEnabled(false);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SQLiteDatabase db = myDb.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from " + myDb.getTableName() + " where " + myDb.getCOL1() + "= '" + query + "';", null);
                if (cursor.getCount() == 0) {
                    return false;
                } else {
                    startViewUserActivity(query);
                    return true;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void startViewUserActivity(String query) {
        Intent intent = new Intent(this, ViewUser.class);
        intent.putExtra("Username", query);
        startActivity(intent);
    }
    /**
     * Shows your current account. This methods parameters (View view) will have to be changed to actually show the
     * proper name on the ViewAccount screen.
     * @param view
     */
    public void viewAccount(View view){


        Intent rev = new Intent(this, ViewAccount.class);

        rev.putExtra("Username", userin);


        startActivity(rev);
    }

    /**
     * When you click on an account in the top 4 list it will take you to the ViewUser screen.
     * It uses Intents to transfer the name of the account clicked.
     * @param view
     */
    public void ClickAccount(View view) {
        TextView a = (TextView) view;
        String user = a.getText().toString();
        Intent rev = new Intent(this, ViewUser.class);
        rev.putExtra("Username",user);
        startActivity(rev);
    }

    /**
     * If you want to create a new Account, it will send you to to CreateAccount class.
     * @param view
     */
    public void CreateAccount(View view){

        Intent cre = new Intent(this, CreateAccount.class);
        startActivity(cre);
    }
}
