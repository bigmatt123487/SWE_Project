package android.sweproject.fyndhr;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.view.View;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "account_table";
    private static final String COL1 = "username";
    private static final String COL2 = "pass";
    private static final String COL3 = "first_name";
    private static final String COL4 = "last_name";
    private static final String COL5 = "sex";
    private static final String COL6 = "birth_date";
    private static final String COL7 = "nreviews";
    private static final String COL8 = "fun";
    private static final String COL9 = "safe";
    private static final String COL10 = "reliable";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
        //onUpgrade(db, 1, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (username varchar(20) PRIMARY KEY, pass varchar(15), first_name varchar(12), last_name varchar(15), sex varchar(1), birth_date date, nreviews int DEFAULT 0, fun double DEFAULT 0, safe double DEFAULT 0, reliable double DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /* Takes in a username and password and checks to see if an account exists.
     * Returns true if correct account information , false if wrong information
     * */
    public boolean LoginCheck(String usrin, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL1 + "= '" + usrin + "' AND " + COL2 + "= '" + password + "';", null);

        // No account found
        if (res.getCount() == 0) {
            return false;
        }
        // Account found
        else {
            return true;
        }
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getCOL1() {
        return COL1;
    }

    public static String getCOL2() {
        return COL2;
    }

    public static String getCOL3() {
        return COL3;
    }

    public static String getCOL4() {
        return COL4;
    }

    public static String getCOL5() {
        return COL5;
    }

    public static String getCOL6() {
        return COL6;
    }

    public static String getCOL7() {
        return COL7;
    }

    public static String getCOL8() {
        return COL8;
    }

    public static String getCOL9() {
        return COL9;
    }

    public static String getCOL10() {
        return COL10;
    }

    /* Inserts account information into the table.
     *
     * */
    public boolean insertData(String username, String password, String first, String last, String sex, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3, first);
        contentValues.put(COL4, last);
        contentValues.put(COL5, sex);
        contentValues.put(COL6, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    /* Returns all table information in the form of a Cursor
     *
     * */
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


    /* Returns the password for a specified account, null if error
     *
     * */
    public Cursor getAccount(String usrin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL1 + "= '" + usrin + "';", null);
        return res;
    }

    /* Gets the age of a specified account
     * Returns -1 if error
     */
    public int getAge(String usrin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL1 + "= '" + usrin + "';", null);

        if (res.getCount() == 0) {
            //error message
            return -1;
        }
        else {
            res.moveToFirst();
            String buffer = res.getString(5);
            String bday = buffer;
            Date date = new Date(); // your date

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Central"));
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            //return ""+ year + " " + month + " " + day;
            String delims = "-";
            StringTokenizer tk = new StringTokenizer(bday, delims);
            int month2 = Integer.parseInt(tk.nextToken().toString());
            int day2 = Integer.parseInt(tk.nextToken().toString());
            int year2 = Integer.parseInt(tk.nextToken().toString());
            int totalYear = year - year2;

            if(month2 >= month)
            {
                if(day2 >= day)
                {
                    return totalYear+1;
                }
            }
            return totalYear;
        }
    }

    /* Returns the number of reviews for a specified account
     * Returns -1 if error
     * */
    public int getReviews(String userin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE username= '" + userin + "'", null);
        if (res.getCount() == 0) {
            //error message
            return -1;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getString(6));

            }
            return Integer.parseInt(buffer.toString());

        }
    }

    /* Returns rating value for given account
     * @param idexin: 7 for fun
     *                8 for safe
     *                9 for reliable
     * Returns -1 if error
     **/
    public double getRating(String usrin, int indexin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE username= '" + usrin + "';", null);
        if (res.getCount() == 0) {
            //error message
            return -1;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append(res.getDouble(indexin));

            }
            return Double.parseDouble(buffer.toString());

        }
    }

    /* Adds 1 to the reviews for an account
     *
     * */
    public void addReview(String usr) {
        int reviews = getReviews(usr);
        int newReviews = reviews + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL7, newReviews);
        db.update(TABLE_NAME, contentValues, "username = ?", new String[]{usr});
        db.close();
    }

    /* Inserts fun/safe/reliable into table with new averages.
     *
     * */
    public void insertRating(double fun, double safe, double reliable, String usr) {

        int reviews = getReviews(usr);

        // No reviews / new account ->
        // Add review and rating
        if (reviews == 0) {
            addReview(usr);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL8, fun);
            contentValues.put(COL9, safe);
            contentValues.put(COL10, reliable);
            db.update(TABLE_NAME, contentValues, "username = ?", new String[]{usr});
            db.close();
        }
        // One or more reviews ->
        // Compute average FUN and store it in the table
        else {
            addReview(usr);
            reviews = getReviews(usr);
            double averageFun = getRating(usr, 7);
            double totalFun = averageFun * (reviews - 1);
            double newAverageFun = (totalFun + fun) / (reviews);

            // Compute average SAFE and store it in the table
            double averageSafe = getRating(usr, 8);
            double totalSafe = averageSafe * (reviews - 1);
            double newAverageSafe = (totalSafe + safe) / (reviews);

            // Compute average RELIABLE and store it in the table
            double averageReliable = getRating(usr, 9);
            double totalReliable = averageReliable * (reviews - 1);
            double newAverageReliable = (totalReliable + reliable) / (reviews);

            // Write to DB
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL8, newAverageFun);
            contentValues.put(COL9, newAverageSafe);
            contentValues.put(COL10, newAverageReliable);
            db.update(TABLE_NAME, contentValues, "username = ?", new String[]{usr});
            db.close();

        }
    }
}

