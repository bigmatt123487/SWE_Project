package android.sweproject.fyndhr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ReviewUser extends AppCompatActivity {

    DatabaseHelper myDb;
    Button submit;
    RatingBar comfortable, fun, reliable;

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_user);
        
        myDb = new DatabaseHelper(this);
        fun = findViewById(R.id.ratingBar6);
        reliable = findViewById(R.id.ratingBar3);
        comfortable = findViewById(R.id.ratingBar4);
        submit = findViewById(R.id.button2);
        user = findViewById(R.id.textView2);

       // rate = findViewById(R.id.textView3);
  
       // rate = findViewById(R.id.textView3);

    }

    /**
     * Submits the review to the database.
     * Uses Intents to get the username from ViewUser and send it back when done reviewing (So it can show
     * on the top textView again) and to send a message that will go to a Toast in ViewUser.
     * @param view
     */
    public void Submit(View view){

        Intent mIntent = getIntent();
        mIntent.getExtras();

        String User = mIntent.getStringExtra("Username");
        

       // Line for inserting review into database

       // Line for inserting review into database

        myDb.insertRating(fun.getRating(),comfortable.getRating(),reliable.getRating(),User);


        finish();
        /*
        Intent sub = new Intent(this, ViewUser.class);
        String thanks = "Thank you for the Review";
        sub.putExtra("Review_Complete", thanks);
        sub.putExtra("Username", User);

        startActivity(sub);
        */

    }

}
