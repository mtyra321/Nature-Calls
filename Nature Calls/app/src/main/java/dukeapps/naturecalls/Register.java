package dukeapps.naturecalls;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Register extends AppCompatActivity {

    EditText        email, username, pass1, pass2;
    TextView        textview2;
    Button          signbtn;
    FirebaseAuth    fAuth;
    ProgressBar     progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        email       = findViewById(R.id.tfEmail);
        username    = findViewById(R.id.tfUsername);
        pass1       = findViewById(R.id.tfPass1);
        pass2       = findViewById(R.id.tfPass2);

        textview2   = findViewById(R.id.textView2);
        signbtn     = findViewById(R.id.signbtn);

        fAuth       = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get all the values
                String eMail        = email.getText().toString();
                String userName     = username.getText().toString();
                String password     = pass1.getText().toString();
                String vPassword    = pass2.getText().toString();

                if(TextUtils.isEmpty(eMail)) {
                    //pop up message
                    Toast emailSet = Toast.makeText(Register.this, "Please enter an email.", Toast.LENGTH_SHORT);
                    emailSet.show();
                    return;
                }

                if(TextUtils.isEmpty(userName)) {
                    //pop up message
                    Toast usernameSet = Toast.makeText(Register.this, "Please enter username.", Toast.LENGTH_SHORT);
                    usernameSet.show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    //pop up message
                    Toast passwordSet = Toast.makeText(Register.this, "Please enter a password.", Toast.LENGTH_SHORT);
                    passwordSet.show();
                    return;
                }

                if(TextUtils.isEmpty(vPassword)) {
                    //pop up message
                    Toast vPasswordSet = Toast.makeText(Register.this, "Please verify your password.", Toast.LENGTH_SHORT);
                    vPasswordSet.show();
                    return;
                }

                if(!password.equals(vPassword)) {
                    //pop up message
                    Toast passwordMatch = Toast.makeText(Register.this, "Please make sure passwords match.", Toast.LENGTH_SHORT);
                    passwordMatch.show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Register user in Firebase.
                fAuth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast successfulRegister = Toast.makeText(Register.this, "Account Created Successfully.", Toast.LENGTH_SHORT);
                            successfulRegister.show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast errorRegister = Toast.makeText(Register.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT);
                            errorRegister.show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }
}