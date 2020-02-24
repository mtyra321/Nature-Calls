package kent.bdtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends Activity {

    dbHelper helper = new dbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View view) {

        if(view.getId() == R.id.signUpButton) {
            EditText name = (EditText)findViewById(R.id.tfName);
            EditText username = (EditText)findViewById(R.id.tfUsername);
            EditText pass1 = (EditText)findViewById(R.id.tfPass1);
            EditText pass2 = (EditText)findViewById(R.id.tfPass2);

            String nameStr = name.getText().toString();
            String usernameStr = username.getText().toString();
            String pass1Str = pass1.getText().toString();
            String pass2Str = pass2.getText().toString();

            if(!pass1Str.equals(pass2Str)) {
                //pop up message
                Toast passwordMatch = Toast.makeText(SignUp.this, "Please make sure passwords match.", Toast.LENGTH_SHORT);
                passwordMatch.show();
            } else {
                //insert info into database
                ProfileInformation pi = new ProfileInformation();
                pi.setName(nameStr);
                pi.setUsername(usernameStr);
                pi.setPassword(pass1Str);

                helper.insertInfo(pi);
            }
        }
    }
}
