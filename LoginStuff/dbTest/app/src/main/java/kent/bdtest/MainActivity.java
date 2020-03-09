package kent.bdtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    dbHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new dbHelper(this);
    }

    public void onButtonClick(View view) {

        if(view.getId() == R.id.loginButton){

            EditText t = (EditText)findViewById(R.id.username);
            String str = t.getText().toString();
            EditText t2 = (EditText)findViewById(R.id.password);
            String str2 = t2.getText().toString();

            String password = myDb.SearchPass(str);
            if(str2.equals(password)) {
                Intent intent = new Intent(MainActivity.this, Display.class);
                intent.putExtra("Username", str);
                startActivity(intent);
            } else {
                //pop up message
                Toast validity = Toast.makeText(MainActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT);
                validity.show();
            }
        }

        if(view.getId() == R.id.signUpHereButton){
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }
    }
}
