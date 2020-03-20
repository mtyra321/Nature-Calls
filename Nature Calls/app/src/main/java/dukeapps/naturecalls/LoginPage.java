package dukeapps.naturecalls;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginPage extends AppCompatActivity {

    EditText uEmail, uPassword;
    Button loginBtn, signUpBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uEmail = findViewById(R.id.tfEmail);
        uPassword = findViewById(R.id.tfPass);

        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        //map stuff
        mLocationPermissionsGranted = getLocationPermission();
        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // return;
            }
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = uEmail.getText().toString();
                String password = uPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    //pop up message
                    Toast emailSet = Toast.makeText(LoginPage.this, "Please enter an email.", Toast.LENGTH_SHORT);
                    emailSet.show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
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
                        if (task.isSuccessful()) {
                            Toast successfulLogin = Toast.makeText(LoginPage.this, "Login Successful.", Toast.LENGTH_SHORT);
                            successfulLogin.show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            progressBar.setVisibility(View.INVISIBLE);
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast errorLogin = Toast.makeText(LoginPage.this, "Failed Login: " + e.getMessage(), Toast.LENGTH_SHORT);
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

        //map stuff


    }
//map stuff


    public FusedLocationProviderClient fusedLocationClient;
    public static Location currentLocation;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;

    private void getDeviceLocation() {
        Log.d("", "getDeviceLocation: getting the devices current location");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = fusedLocationClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d("", "onComplete: found location!");
                            currentLocation = (Location) task.getResult();
                            Log.i("current location", "currentLocation: " + currentLocation.getLatitude() + " " + currentLocation.getLongitude());
//                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(43.814759, -111.784555)));
//                            googleMap.moveCamera(CameraUpdateFactory.zoomTo(18));
                            //     moveToCurrentLocation();
                            //        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
                        } else {
                            Log.d("", "onComplete: current location is null");
                            // Toast.makeText(this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("", "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }


    private boolean getLocationPermission() {
        Log.d("get", "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                return true;
                //    initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        return false;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("", "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d("", "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d("", "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    //                initMap();
                }
            }
        }
    }


}
