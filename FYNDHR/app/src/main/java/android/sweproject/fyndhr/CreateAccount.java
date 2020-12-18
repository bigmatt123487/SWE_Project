package android.sweproject.fyndhr;

import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText inUser, inPass, inFirst, inLast, inSex, inBirth;
    Button createAcc;
    Button viewData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_screen);

        myDb = new DatabaseHelper(this);

        inUser = (EditText) findViewById(R.id.editUsername);
        inPass = (EditText) findViewById(R.id.editPassword);
        inFirst = (EditText) findViewById(R.id.editFirst);
        inLast = (EditText) findViewById(R.id.editLast);
        inSex = (EditText) findViewById(R.id.editSex);
        inBirth = (EditText) findViewById(R.id.editBirthdate);
        createAcc = (Button) findViewById(R.id.button5);

        addData();
    }


    /**
     * When you select create, it will check if the boolean is true, if it is then it will
     * use an intent with a key that will send you to the main screen (while disabling the Create Account button).
     * else it will display a Toast.
     * @param view
     */


    public void Create(View view){
        boolean validateOk = validate(new EditText[] {inUser, inPass, inFirst, inLast, inSex, inBirth});


        if (validateOk) {
            Intent intent = new Intent(this, Homepage.class);
            intent.putExtra("Disable", 1);
            startActivity(intent);
        }
        else
            Toast.makeText(this,"Must not leave a field blank!", Toast.LENGTH_LONG).show();
    }

    /**
     * Validating that all the text fields have information in them
     * @param fields
     * @return boolean
     */
    private boolean validate(EditText[] fields){
        for(int i = 0; i < fields.length; i++){
            EditText currentField = fields[i];
            if(currentField.getText().toString().length() <= 0){
                return false;
            }
        }
        return true;
    }

    public void addData(){
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(inUser.getText().toString(), inPass.getText().toString(), inFirst.getText().toString(), inLast.getText().toString(), inSex.getText().toString(), inBirth.getText().toString());
                if(isInserted == true)
                    Toast.makeText(CreateAccount.this,"Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(CreateAccount.this,"Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }
}

