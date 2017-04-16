package ph.databaseinsertview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class foundData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_data);

        TextView name = (TextView) findViewById(R.id.usernameView);
        TextView email = (TextView) findViewById(R.id.emailView);
        TextView password = (TextView) findViewById(R.id.passwordView);

        String nameRec = getIntent().getExtras().getString("name");
        String emailRec = getIntent().getExtras().getString("email");
        String passwordRec = getIntent().getExtras().getString("password").toString();

        name.setText(nameRec);
        email.setText(emailRec);
        password.setText(passwordRec);



    }
}
