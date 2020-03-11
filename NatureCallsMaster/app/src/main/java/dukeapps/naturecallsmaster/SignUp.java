package dukeapps.naturecallsmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Activity {

    dbHelper helper = new dbHelper(this);

    EditText name, username, pass1, pass2;
    TextView textview2;
    Button signbtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name = findViewById(R.id.tfName);
        username = findViewById(R.id.tfUsername);
        pass1 = findViewById(R.id.tfPass1);
        pass2 = findViewById(R.id.tfPass2);


        textview2 = findViewById(R.id.textView2);
        signbtn = findViewById(R.id.signUpButton);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

            //Get all the values
            String signName = name.getText().toString();
            String userName = username.getText().toString();
            String password = pass1.getText().toString();
            String vPassword = pass2.getText().toString();


                UserHelperClass helperClass = new UserHelperClass(signName, userName, password, vPassword);
                reference.child(signName).setValue(helperClass);

            }
        });
    }
}

//    public void onSignUpClick(View view) {
//
//        if(view.getId() == R.id.signUpButton) {
//            EditText name = (EditText)findViewById(R.id.tfName);
//            EditText username = (EditText)findViewById(R.id.tfUsername);
//            EditText pass1 = (EditText)findViewById(R.id.tfPass1);
//            EditText pass2 = (EditText)findViewById(R.id.tfPass2);
//
//            String nameStr = name.getText().toString();
//            String usernameStr = username.getText().toString();
//            String pass1Str = pass1.getText().toString();
//            String pass2Str = pass2.getText().toString();
//
//            if(!pass1Str.equals(pass2Str)) {
//                //pop up message
//                Toast passwordMatch = Toast.makeText(SignUp.this, "Please make sure passwords match.", Toast.LENGTH_SHORT);
//                passwordMatch.show();
//            } else {
//                //insert info into database
//                ProfileInformation pi = new ProfileInformation();
//                pi.setName(nameStr);
//                pi.setUsername(usernameStr);
//                pi.setPassword(pass1Str);
//
//                helper.insertInfo(pi);
//
//                Intent intent = new Intent(SignUp.this, LoginPage.class);
//            }
//        }
//    }
//}
