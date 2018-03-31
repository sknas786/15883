package shiksha.teambitcoders.com.shiksha;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    private int PROXIMITY_RADIUS = 10000;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    DatabaseReference ref,ref1,ref2,ref3,ref4,ref5,ref6,ref7,ref8,ref9,ref10;
    public ArrayList<String> stringArrayListMarkerName = new ArrayList<>();
    public ArrayList<String> stringArrayListMarkerLang = new ArrayList<>();
    public ArrayList<String> stringArrayListMarkerLat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        ref1 = FirebaseDatabase.getInstance().getReference();
//        ref1.child("mapsdetails").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()){
//                            //Get map of users in datasnapshot
//                            collectPhoneNumbers1((Map<String, Object>) dataSnapshot.getValue());
//                        }
//                    }
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
//
//
//        ref2 = FirebaseDatabase.getInstance().getReference();
//        ref2.child("mapsdetails").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//                        if (dataSnapshot.exists()) {
//                            collectPhoneNumbers2((Map<String, Object>) dataSnapshot.getValue());
//                        }
//                    }
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
//
//        ref3 = FirebaseDatabase.getInstance().getReference();
//        ref3.child("mapsdetails").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Get map of users in datasnapshot
//                        if (dataSnapshot.exists()) {
//                            collectPhoneNumbers3((Map<String, Object>) dataSnapshot.getValue());
//                        }
//                    }
//
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_map));


        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

//        Button btnRestaurant = (Button) findViewById(R.id.btnRestaurant);
//        btnRestaurant.setOnClickListener(new View.OnClickListener() {
//            String Restaurant = "restaurant";
//            @Override
//            public void onClick(View v) {
//                Log.d("onClick", "Button is Clicked");
//                mMap.clear();
//                String url = getUrl(latitude, longitude, Restaurant);
//                Object[] DataTransfer = new Object[2];
//                DataTransfer[0] = mMap;
//                DataTransfer[1] = url;
//                Log.d("onClick", url);
//                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//                getNearbyPlacesData.execute(DataTransfer);
//                Toast.makeText(MapsActivity.this,"Nearby Restaurants", Toast.LENGTH_LONG).show();
//            }
//        });

//        Button btnHospital = (Button) findViewById(R.id.btnHospital);
//        btnHospital.setOnClickListener(new View.OnClickListener() {
//            String Hospital = "hospital";
//            @Override
//            public void onClick(View v) {
//                Log.d("onClick", "Button is Clicked");
//                mMap.clear();
//                String url = getUrl(latitude, longitude, Hospital);
//                Object[] DataTransfer = new Object[2];
//                DataTransfer[0] = mMap;
//                DataTransfer[1] = url;
//                Log.d("onClick", url);
//                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//                getNearbyPlacesData.execute(DataTransfer);
//                Toast.makeText(MapsActivity.this,"Finding Nearby Hospitals...", Toast.LENGTH_LONG).show();
//            }
//        });

//        Button btnSchool = (Button) findViewById(R.id.btnSchool);
//        btnSchool.setOnClickListener(new View.OnClickListener() {
//            String School = "school";
//            @Override
//            public void onClick(View v) {
//                Log.d("onClick", "Button is Clicked");
//                mMap.clear();
//                if (mCurrLocationMarker != null) {
//                    mCurrLocationMarker.remove();
//                }
//                String url = getUrl(latitude, longitude, School);
//                Object[] DataTransfer = new Object[2];
//                DataTransfer[0] = mMap;
//                DataTransfer[1] = url;
//                Log.d("onClick", url);
//                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//                getNearbyPlacesData.execute(DataTransfer);
//                Toast.makeText(MapsActivity.this,"Nearby Schools", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            //getting the latitude value
            double latitudeValue=mLastLocation.getLatitude();
            Log.d("value",""+latitudeValue);
            //getting the longitude value
            double longitudeValue=mLastLocation.getLongitude();
            double ulat=latitudeValue-4.0013569;
            double ulng=longitudeValue+4.0015142;



            // Other supported types include: MAP_TYPE_NORMAL,
            // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
//           
            mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.style_map));

            mMap.setMyLocationEnabled(true);


//            for(int i=0;i<stringArrayListMarkerName.size();i++)
//            {
//                double doubleLat = Double.parseDouble(stringArrayListMarkerLat.get(i));
//                double doubleLang = Double.parseDouble(stringArrayListMarkerLang.get(i));
////                stringArrayListMarkerLat.get(i);
//                MarkerOptions marker= new MarkerOptions()
//                        .title(stringArrayListMarkerName.get(i))
//                        .position(new LatLng(doubleLat,doubleLang))
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
//                mMap.addMarker(marker);
//            }

            //Setting up the marker
            MarkerOptions marker= new MarkerOptions()
                    .title(
                            "K L university")
                    .position(new LatLng(16.441919,80.622526))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker);

            MarkerOptions marker1= new MarkerOptions()
                    .title(
                            "Rajiv gandhi university")
                    .position(new LatLng(12.926639,77.592320))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker1);

            MarkerOptions marker2= new MarkerOptions()
                    .title(
                            "Gauhati University")
                    .position(new LatLng(26.153559,91.663031))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker2);

            MarkerOptions marker3= new MarkerOptions()
                    .title(
                            "Magadh University")
                    .position(new LatLng(24.686747,84.965134))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker3);

            MarkerOptions marker4= new MarkerOptions()
                    .title(
                            "National Institute of Technology")
                    .position(new LatLng(43.087552,-77.668277))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker4);

            MarkerOptions marker5= new MarkerOptions()
                    .title(
                            " Goa University")
                    .position(new LatLng(15.458414,73.834555))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker5);

            MarkerOptions marker6= new MarkerOptions()
                    .title(
                            "Maharaja Sayajirao University of Baroda")
                    .position(new LatLng(22.322474,73.180923))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker6);

            MarkerOptions marker7= new MarkerOptions()
                    .title(
                            "Maharishi Dayanand University")
                    .position(new LatLng(28.876827,76.621108))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker7);

            MarkerOptions marker8= new MarkerOptions()
                    .title(
                            "Himachal Pradesh University")
                    .position(new LatLng(31.111037,77.139365))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker8);

            MarkerOptions marker9= new MarkerOptions()
                    .title(
                            "University of Jammu")
                    .position(new LatLng(32.719370,74.868063))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker9);

            MarkerOptions marker10= new MarkerOptions()
                    .title(
                            "Ranchi University")
                    .position(new LatLng(23.371500,85.323923))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker10);

            MarkerOptions marker11= new MarkerOptions()
                    .title(
                            "Manipal University")
                    .position(new LatLng(13.353167,74.784748))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker11);

            MarkerOptions marker12= new MarkerOptions()
                    .title(
                            "MG University")
                    .position(new LatLng(9.657967,76.532764))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker12);
            MarkerOptions marker13= new MarkerOptions()
                    .title(
                            "Devi Ahilya Vishwavidyalaya")
                    .position(new LatLng(22.716359,75.871913))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker13);

            MarkerOptions marker14= new MarkerOptions()
                    .title(
                            "University of Mumbai")
                    .position(new LatLng(19.071122,72.855996))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker14);

            MarkerOptions marker15= new MarkerOptions()
                    .title(
                            "Manipur University")
                    .position(new LatLng(24.752288,93.928042))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker15);

            MarkerOptions marker16= new MarkerOptions()
                    .title(
                            "CMJ University")
                    .position(new LatLng(25.570804,91.895576))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker16);

            MarkerOptions marker17= new MarkerOptions()
                    .title(
                            "North Eastern Hill University")
                    .position(new LatLng(25.613142,91.898147))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker17);

            MarkerOptions marker18= new MarkerOptions()
                    .title(
                            "The Global Open University Nagaland")
                    .position(new LatLng(25.845051,93.777066))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker18);

            MarkerOptions marker19= new MarkerOptions()
                    .title(
                            "KIIT University")
                    .position(new LatLng(20.354386,85.814657))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.gmarker));
            mMap.addMarker(marker19);





            mMap.setTrafficEnabled(true);
        }
    }



//    private String getUrl(double latitude, double longitude, String nearbyPlace) {
//
//        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//        googlePlacesUrl.append("location=" + latitude + "," + longitude);
//        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
//        googlePlacesUrl.append("&type=" + nearbyPlace);
//        googlePlacesUrl.append("&sensor=true");
//        googlePlacesUrl.append("&key=" + "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0");
//        Log.d("getUrl", googlePlacesUrl.toString());
//        return (googlePlacesUrl.toString());
//    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.current_position_marker));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(4));
        Toast.makeText(MapsActivity.this,"Your Current Location", Toast.LENGTH_LONG).show();

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }




    public void collectPhoneNumbers1(Map<String,Object> Faculty) {

    //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()) {

        //Get user map
        Map singleUser = (Map) entry.getValue();
        //Get phone field and append to list
        stringArrayListMarkerName.add((String) singleUser.get("collegename"));

    }


}


    public void collectPhoneNumbers2(Map<String,Object> Faculty) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            stringArrayListMarkerLat.add((String) singleUser.get("latitude"));

        }


    }



    public void collectPhoneNumbers3(Map<String,Object> Faculty) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : Faculty.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            stringArrayListMarkerLang.add((String) singleUser.get("longitude"));

        }


    }
}
