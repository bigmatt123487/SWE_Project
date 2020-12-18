package android.sweproject.fyndhr;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ViewUser extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewuser_screen);
        myDb = new DatabaseHelper(this);
/**
 * Getting username and review(toast) strings.
 */
        Intent mIntent = getIntent();
        mIntent.getExtras();
        String User = mIntent.getStringExtra("Username");
        String thanks = mIntent.getStringExtra("Review_Complete");

        Cursor res = myDb.getAccount(User);
        TextView us = findViewById(R.id.Username);
        TextView birth = findViewById(R.id.user_age);
        TextView Name = findViewById(R.id.user_name);

        RatingBar bFun = findViewById(R.id.ratingBar);
        RatingBar bReliable = findViewById(R.id.ratingBar2);
        RatingBar bSafe = findViewById(R.id.ratingBar5);

        res.moveToFirst();
        String age = res.getString(5);
        String realname = res.getString(2);
        realname += " " + res.getString(3);

        Float fun = res.getFloat(7);
        Float safe = res.getFloat(8);
        Float reliable = res.getFloat(9);

        bFun.setRating(fun);
        bReliable.setRating(safe);
        bSafe.setRating(reliable);


        Name.setText(realname);
        birth.setText(age);
        us.setText(User);

    }

    /**
     * Once pressed, it will use Intents to get the username and send it to the ReviewUser class and start
     * new Activity.
     * @param view
     */
    public void ReviewUser (View view) {

        Intent user = getIntent();
        user.getExtras();
        String User = user.getStringExtra("Username");


        Intent intent = new Intent(this, ReviewUser.class);
        intent.putExtra("Username", User);
        startActivity(intent);
    }



}
