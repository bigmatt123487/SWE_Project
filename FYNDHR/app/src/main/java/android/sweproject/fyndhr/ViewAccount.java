package android.sweproject.fyndhr;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewAccount extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewaccount_screen);

        TextView Name = findViewById(R.id.user_name);
        TextView birth = findViewById(R.id.user_age);
        TextView Gender = findViewById(R.id.user_relationship);
        TextView us = findViewById(R.id.Username);
        RatingBar bFun = findViewById(R.id.rbFun);
        RatingBar bReliable = findViewById(R.id.rbReliable);
        RatingBar bSafe = findViewById(R.id.rbSafe);

        db = new DatabaseHelper(this);

        Intent mIntent = getIntent();
        mIntent.getExtras();
        String User = mIntent.getStringExtra("Username");
        Cursor res = db.getAccount(User);

        res.moveToFirst();
        String age = res.getString(5);
        String gender = res.getString(4);
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
        Gender.setText(gender);
        us.setText(User);
        }
    }

