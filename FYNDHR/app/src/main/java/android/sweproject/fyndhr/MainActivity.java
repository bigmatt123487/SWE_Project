package android.sweproject.fyndhr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button login, signup;
    TextView user, pass;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Intent mIntent = getIntent();
        mIntent.getExtras();
        //String User = mIntent.getStringExtra("Username");
        db = new DatabaseHelper(this);
        login = findViewById(R.id.lglogin);
        signup = findViewById(R.id.lgsignup);
        user = findViewById(R.id.lguser);
        pass = findViewById(R.id.lgpass);


    }
    /* Checks to see if account exists with given information. Takes you to home screen if successful,
     * stays on page if not.
     *
     */
    public void checkLogin(View view){


            boolean valid = db.LoginCheck(user.getText().toString(), pass.getText().toString());

            // If account exists, take user to home page
            if (valid == true) {

                Intent cte = new Intent(this, Homepage.class);
                cte.putExtra("User", user.getText().toString());
                startActivity(cte);

            }
            // If account doesn't exist, let them know and do nothing
            else {
                Toast.makeText(this, "No account found!", Toast.LENGTH_LONG).show();
            }


    }

    /* Takes you to the create account page
     *
     */
    public void register(View view){


        Intent reg = new Intent(this,CreateAccount.class);
        startActivity(reg);
                // After the sign up button is clicked, direct towards create_screen/CreateAccount page




    }
}
