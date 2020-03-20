package dukeapps.naturecalls;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginPage extends AppCompatActivity {

    EditText        uEmail, uPassword;
    Button          loginBtn, signUpBtn;
    ProgressBar     progressBar;
    FirebaseAuth    fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uEmail          = findViewById(R.id.tfEmail);
        uPassword       = findViewById(R.id.tfPass);

        loginBtn        = findViewById(R.id.loginBtn);
        signUpBtn       = findViewById(R.id.signUpBtn);

        fAuth           = FirebaseAuth.getInstance();
        progressBar     = findViewById(R.id.progressBar2);

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email        = uEmail.getText().toString();
                String password     = uPassword.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    //pop up message
                    Toast emailSet = Toast.makeText(LoginPage.this, "Please enter an email.", Toast.LENGTH_SHORT);
                    emailSet.show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    //pop up message
                    Toast passwordSet = Toast.makeText(LoginPage.this, "Please enter a password.", Toast.LENGTH_SHORT);
                    passwordSet.show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Authenticate valid user.
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast successfulLogin = Toast.makeText(LoginPage.this, "Login Successful.", Toast.LENGTH_SHORT);
                            successfulLogin.show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast errorLogin = Toast.makeText(LoginPage.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT);
                            errorLogin.show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }


}
