package dukeapps.naturecalls.ui.map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dukeapps.naturecalls.BathroomActivity;
import dukeapps.naturecalls.LoginPage;
import dukeapps.naturecalls.MainActivity;
import dukeapps.naturecalls.R;


import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.GeoApiContext;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private MapViewModel mapViewModel;
    private MapView mMapView;
    public GoogleMap googleMap;
    private Location currentLocation;
    private GeoApiContext mGeoApiContext = null;
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final String API_KEY = "AIzaSyAt1HpipYIEY1SoEwiYMlF7TwSNtzDQthY";
    private static final String TAG = "MapFragment";
    private String transitMode = "driving";
    private Polyline mPolyline;
    ArrayList<LatLng> mMarkerPoints;
    private ClusterManager<ClusterMarker> mClusterManager;
    private MyClusterManagerRenderer mClusterManagerRenderer;
    private ArrayList<ClusterMarker> mClusterMarkers = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView textView = root.findViewById(R.id.text_map);
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        mMapView = (MapView) root.findViewById(R.id.map);

        initGoogleMap(savedInstanceState);
        currentLocation = LoginPage.currentLocation;
        //   this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));

        mMarkerPoints = new ArrayList<>();
        return root;
    }

    public void onMapReady(final GoogleMap googleMap) {
        setMap(googleMap);
        this.googleMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())));
        this.googleMap.setOnInfoWindowClickListener(this);
        googleMap.setMyLocationEnabled(true);
        addMapMarkers();
    }

    private void initGoogleMap(Bundle savedInstanceState) {
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
        if (mGeoApiContext == null) {
            mGeoApiContext = new GeoApiContext.Builder()
                    .apiKey(API_KEY)
                    .build();
        }

    }


    private void addMapMarkers() {

        if (googleMap != null) {

            if (mClusterManager == null) {
                mClusterManager = new ClusterManager<ClusterMarker>(getActivity().getApplicationContext(), googleMap);
            }
            if (mClusterManagerRenderer == null) {
                mClusterManagerRenderer = new MyClusterManagerRenderer(
                        getActivity(),
                        googleMap,
                        mClusterManager
                );
                mClusterManager.setRenderer(mClusterManagerRenderer);
            }

            //    for(UserLocation userLocation: mUserLocations){

            Log.d(TAG, "addMapMarkers: location: ");
            try {
                String snippet = "testing";


                int avatar = R.drawable.toilet; // set the default avatar
                try {
                    //   avatar = Integer.parseInt(userLocation.getUser().getAvatar());
                } catch (NumberFormatException e) {
                    Log.d(TAG, "addMapMarkers: no avatar for , setting default.");
                }
                ClusterMarker newClusterMarker = new ClusterMarker(
                        new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                        "test",
                        snippet,
                        avatar
                );
                mClusterManager.addItem(newClusterMarker);
                mClusterMarkers.add(newClusterMarker);
                ClusterMarker newClusterMarker1 = new ClusterMarker(
                        new LatLng(43.81803644277783, -111.78968902677298),
                        "Destination",
                        "The testing end1",
                        avatar
                );
                mClusterManager.addItem(newClusterMarker1);
                mClusterMarkers.add(newClusterMarker1);
                ClusterMarker newClusterMarker2 = new ClusterMarker(
                        new LatLng(43.81582601618893, -111.78317125886679),
                        "2",
                        "The testing end2",
                        avatar
                );
                mClusterManager.addItem(newClusterMarker2);
                mClusterMarkers.add(newClusterMarker2);
                ClusterMarker newClusterMarker3 = new ClusterMarker(
                        new LatLng(43.81827110075013, -111.7827320471406),
                        "3",
                        "The testing end3",
                        avatar
                );
                mClusterManager.addItem(newClusterMarker3);
                mClusterMarkers.add(newClusterMarker3);
            } catch (NullPointerException e) {
                Log.e(TAG, "addMapMarkers: NullPointerException: " + e.getMessage());
            }

            // }
            mClusterManager.cluster();

            //    setCameraView();
        }
    }

    public void onInfoWindowClick(Marker marker) {
        if (marker.getSnippet().equals("This is you")) {
            marker.hideInfoWindow();
        } else {

            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("set route to " + marker.getTitle() + "?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            LatLng destination = marker.getPosition();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("set route to " + marker.getTitle() + "?")
                                    .setCancelable(true)
                                    .setPositiveButton("Driving", new DialogInterface.OnClickListener() {
                                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                            setTransitModeToDriving();
                                            Log.i("testing", "testtesttest");
                                            drawRoute(destination);
                                            Log.i("testing", "after draw route");
                                            dialog.dismiss();

                                        }
                                    })

                                    .setNegativeButton("Walking", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                            setTransitModeToWalking();
                                            drawRoute(destination);
                                        }
                                    });
                            final AlertDialog alert = builder.create();
                            alert.show();
                            dialog.dismiss();
                        }
                    })
                    .setNeutralButton("Bathroom Info", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                               Intent intent = new Intent(getActivity(), BathroomActivity.class);

                             startActivity(intent);
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
        }
        Log.i("OnInfo", "The info window was clicked");
    }

    public void setMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void setTransitModeToDriving() {
        Log.i("Transitmodedriving", "transit mode is now driving");
        transitMode = "driving";
    }

    public void setTransitModeToWalking() {
        Log.i("Transitmodewalking", "transit mode is now walking");
        transitMode = "walking";
    }

    public String getTransitMode() {
        return transitMode;
    }

    public void setTransitMode(String transitMode) {
        this.transitMode = transitMode;
    }

    private void drawRoute(LatLng destination) {

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), destination);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }


    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Key
        String key = "key=" + API_KEY;

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest +"&mode=" +transitMode+ "&" + key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception on download", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


    /**
     * A class to download data from Google Directions URL
     */
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DownloadTask", "DownloadTask : " + data);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    /**
     * A class to parse the Google Directions in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("testingRoutes", routes.toString());
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(8);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                if (mPolyline != null) {
                    mPolyline.remove();
                }
                mPolyline = googleMap.addPolyline(lineOptions);

            } else {
                //   Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
            }
        }
    }


}
