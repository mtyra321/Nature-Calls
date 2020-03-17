package dukeapps.naturecallsmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    dbHelper myDb;

    Button callSignUp, login_btn;
    TextView username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new dbHelper(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login_btn = findViewById(R.id.loginButton);
        callSignUp = findViewById(R.id.signUpHereButton);
    }

    private Boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
           // username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            //password.setErrorEnabled(false);
            return true;
        }
    }
    

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredUsername = username.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    username.setError(null);

                    String passwordFromBD = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromBD.equals(userEnteredPassword)){
                        username.setError(null);
                        String signNameFromBD = dataSnapshot.child(userEnteredUsername).child("signName").getValue(String.class);
                        String userNameFromBD = dataSnapshot.child(userEnteredUsername).child("userName").getValue(String.class);
                        //String vPasswordFromBD = dataSnapshot.child(userEnteredUsername).child("vPassword").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        intent.putExtra("signName", signNameFromBD);
                        intent.putExtra("username", userNameFromBD);
                        intent.putExtra("password", passwordFromBD);
                        startActivity(intent);
                    } else {
                       // progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                   // progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                    }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


//    public void onButtonClick(View view) {
//
//        if(view.getId() == R.id.loginButton){
//
//            EditText t = (EditText)findViewById(R.id.username);
//            String str = t.getText().toString();
//            EditText t2 = (EditText)findViewById(R.id.password);
//            String str2 = t2.getText().toString();
//
//            String password = myDb.SearchPass(str);
//            if(str2.equals(password)) {
//                Intent intent = new Intent(LoginPage.this, MainActivity.class);
//                intent.putExtra("Username", str);
//                startActivity(intent);
//            } else {
//                //pop up message
//                Toast validity = Toast.makeText(LoginPage.this, "Invalid Credentials.", Toast.LENGTH_SHORT);
//                validity.show();
//            }
//        }
//
//        if(view.getId() == R.id.signUpHereButton){
//            Intent intent = new Intent(LoginPage.this, SignUp.class);
//            startActivity(intent);
//        }
//    }
}
