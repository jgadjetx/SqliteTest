package ph.databaseinsertview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class viewData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        final EditText name = (EditText) findViewById(R.id.SearchUsername);
        final EditText email = (EditText) findViewById(R.id.SearchEmail);
        final Button btnSearch = (Button) findViewById(R.id.SearchButton);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(name.getText().length() < 1)
                {
                    String emailData = email.getText().toString();
                    searchEmail(emailData);
                }

                else if(email.getText().length() < 1)
                {
                    String nameData = name.getText().toString();
                    searchName(nameData);
                }

                else if (name.getText().length() < 1 && email.getText().length() < 1)
                {
                    Toast toast =  Toast.makeText(getApplicationContext(),"Cannot Search for empty info you idiot",Toast.LENGTH_SHORT);
                    toast.show();
                }

                else
                {
                    String nameData = name.getText().toString();
                    String emailData = email.getText().toString();

                    searchBoth(nameData,emailData);
                }

            }
        });



    }


    public void searchBoth(String name, String email)
    {

        try
        {
            SQLiteOpenHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query("USERS",new String[]{"name","email","password"},"name = ? AND email = ?", new String[]{name,email},null,null,null);

            if(cursor.moveToFirst())
            {
                String nameFound = cursor.getString(0);
                String emailFound = cursor.getString(1);
                String passwordFound = cursor.getString(2).toString();


                Intent intent = new Intent(viewData.this,foundData.class);
                intent.putExtra("name",nameFound);
                intent.putExtra("email",emailFound);
                intent.putExtra("password",passwordFound);

                startActivity(intent);

            }

            if (cursor.moveToFirst() == false)
            {
                Toast falseToast = Toast.makeText(this,"Data Not Found On Database",Toast.LENGTH_SHORT);
                falseToast.show();
            }

            db.close();

        }
        catch (SQLiteException e)
        {
            Toast toast = Toast.makeText(this,"Database Error",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void searchName(String name)
    {
        try
        {
            SQLiteOpenHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query("USERS",new String[]{"name","email","password"},"name = ?", new String[]{name},null,null,null,null);

            if (cursor.moveToFirst())
            {

                String nameFound = cursor.getString(0);
                String emailFound = cursor.getString(1);
                String passwordFound = cursor.getString(2).toString();

                Intent intent = new Intent(viewData.this,foundData.class);
                intent.putExtra("name",nameFound);
                intent.putExtra("password",passwordFound);
                intent.putExtra("email",emailFound);

                startActivity(intent);

            }

            if (cursor.moveToFirst() == false)
            {
                Toast falseToast = Toast.makeText(this,"Data Not Found On Database",Toast.LENGTH_SHORT);
                falseToast.show();
            }

            db.close();

        }
        catch (SQLiteException e)
        {
            Toast toast = Toast.makeText(this,"Database Error",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void searchEmail(String email)
    {
        try {

            SQLiteOpenHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query("USERS", new String[]{"name", "email", "password"}, "email = ?", new String[]{email}, null, null, null);

            if (cursor.moveToFirst()) {
                String nameFound = cursor.getString(0);
                String emailFound = cursor.getString(1);
                String passwordFound = cursor.getString(2).toString();

                Intent intent = new Intent(viewData.this, foundData.class);

                intent.putExtra("name", nameFound);
                intent.putExtra("email", emailFound);
                intent.putExtra("password", passwordFound);

                startActivity(intent);
            }

            if (cursor.moveToFirst() == false) {
                Toast falseToast = Toast.makeText(this, "Data Not Found On Database", Toast.LENGTH_SHORT);
                falseToast.show();
            }

            db.close();
        }
        catch (SQLiteException e)
        {
            Toast toast = Toast.makeText(this,"Database Error",Toast.LENGTH_SHORT);
            toast.show();

        }
    }


}
