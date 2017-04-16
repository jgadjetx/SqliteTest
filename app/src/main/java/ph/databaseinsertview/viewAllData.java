package ph.databaseinsertview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class viewAllData extends AppCompatActivity
{
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_data);

        populateListView();
        listView = (ListView) findViewById(R.id.theList);
    }

    public void populateListView()
    {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.getAllRows(db);

        String [] fields = new String[]{"_id","name","email","password"};
        int [] viewId = new int[]{R.id.theid,R.id.thename,R.id.theemail,R.id.thepassword};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.list_layout,cursor,fields,viewId,0);
        listView = (ListView) findViewById(R.id.theList);

        listView.setAdapter(adapter);
    }
}
