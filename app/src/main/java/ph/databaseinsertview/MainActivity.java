package ph.databaseinsertview;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnInsert = (Button) findViewById(R.id.button);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText username = (EditText) findViewById(R.id.username);
        final Button buttonView = (Button) findViewById(R.id.view);
        final Button btnViewAllData = (Button) findViewById(R.id.viewAll);



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if ((email.getText().length() > 1 ) && (password.getText().length() > 1 ) && (username.getText().length() > 1 ))
                {
                    insertData();

                    username.setText(null);
                    email.setText(null);
                    password.setText(null);
                    
                }

                if ((email.getText().length() < 1 ) && (password.getText().length() < 1 ) && (username.getText().length() < 1 ))
                {
                    showToast("Cannot Append Empty Data To Database");
                }



            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,viewData.class);
                startActivity(intent);
            }
        });


        btnViewAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,viewAllData.class);
                startActivity(intent);
            }
        });
    }



    public void insertData()
    {

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);

        String btnUsername = String.valueOf(username.getText());
        String btnEmail = String.valueOf(email.getText());
        String btnPassword = String.valueOf(password.getText().toString());

        try
        {
            SQLiteOpenHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            boolean found = emailExists(db,btnEmail);

            if(!found)
            {
                ContentValues values = new ContentValues();

                values.put("name",btnUsername);
                values.put("email",btnEmail);
                values.put("password",btnPassword.toString());

                db.insert("USERS",null,values);

                Toast goodTest = Toast.makeText(this,"Data insert successful",Toast.LENGTH_SHORT);
                goodTest.show();
            }
            else
            {
                showToast("Sorry e-mail Already Exists");
            }


            db.close();
        }
        catch ( SQLiteException e)
        {
            Toast toast = Toast.makeText(this,"Database Error!!",Toast.LENGTH_SHORT);
            toast.show();

        }

    }


    public boolean emailExists(SQLiteDatabase db,String email)
    {
        boolean found = false;

        Cursor cursor = db.query("USERS",new String[]{"email"},"email = ?", new String[]{email},null,null,null);

        if (cursor.moveToFirst() == true)
        {
            found = true;
        }

        return  found;

    }
    public void showToast(String text)
    {
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }





}
