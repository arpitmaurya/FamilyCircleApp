package com.india.AccidentNotifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;
    TextView latTextView, lonTextView,direction;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference cXref ;
DatabaseReference cYref , isEmergency ,directionFb,phone;
Button reload , emergency;
SharedPreferences sharedPreferences ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("com.india.AccidentNotifier", MODE_PRIVATE);

        String circleName = sharedPreferences.getString("CIRCLE_NAME","");
        String userID= sharedPreferences.getString("Email","").replace(".com","");
        Toast.makeText(MainActivity.this,"CircleName is :"+circleName,Toast.LENGTH_LONG);
        latTextView = findViewById(R.id.latTextView);
        lonTextView = findViewById(R.id.lonTextView);
        direction = findViewById(R.id.bearing);
        emergency = (Button) findViewById(R.id.emergency);
        reload = (Button) findViewById(R.id.reload);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        firebaseDatabase = FirebaseDatabase.getInstance();

        cXref = firebaseDatabase.getReference(circleName+"/"+userID+"/cX");
        cYref = firebaseDatabase.getReference(circleName+"/"+userID+"/cY");
        directionFb= firebaseDatabase.getReference(circleName+"/"+userID+"/direction");
        isEmergency = firebaseDatabase.getReference(circleName+"/"+userID+"/isEmergency");
        phone = firebaseDatabase.getReference(circleName+"/"+userID+"/phone");
        isEmergency.setValue(false);
        phone.setValue("9451243978");
        getLastLocation();

      emergency.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              isEmergency.setValue(true);
              Toast.makeText(MainActivity.this, "Help is coming don't panic, Notification has been sent to your family and friends in the current circle!", Toast.LENGTH_SHORT).show();
          }
      });

reload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
   onResume();
   Toast.makeText(MainActivity.this, "Your current location is updated!", Toast.LENGTH_SHORT).show();

    }
});


    }




    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    latTextView.setText(location.getLatitude()+" Latidute");
                                    cXref.setValue(location.getLatitude()+"");
                                    lonTextView.setText(location.getLongitude()+" Longitude");
                                   cYref.setValue(location.getLongitude()+"");
                                    direction.setText(location.getBearing()+" Degree");
                                    directionFb.setValue(location.getBearing()+"");


                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latTextView.setText(mLastLocation.getLatitude()+"");
            lonTextView.setText(mLastLocation.getLongitude()+"");






        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEmergency.setValue(true);
                Toast.makeText(MainActivity.this, "HELP is coming don't panic , Notification has been sent to your family and friends in the current circle!", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
