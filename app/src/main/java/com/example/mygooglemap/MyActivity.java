package com.example.mygooglemap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
/*SHA    95:CC:D7:93:F1:30:1C:EE:32:00:0A:6E:9E:73:EC:A7:85:67:55:58  */
/* HashKey        lczXk/EwHO4yAApunnPsp4VnVVg=        */
/* AIzaSyATgYjpXX9aOLLvEah3z-mfcOeh9ZWoPuI  */

public class MyActivity extends Activity {

    public static final String RESPONSE_BROADCAST = "com.example.mygooglemap.response_broadcast";
    public static final String RESPONSE_VALUE   = "com.example.mygooglemap.response_value";
    private Marker startMarker , endMarker ;
    private GoogleMap mMap;
    private ResponseBroadcast broadcast;
    Polyline mPolyline;
    LatLng myPossition = new LatLng(48.611860d, 22.293198d);
    GoogleMapJsonParser parser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        getActionBar().hide();
        broadcast = new ResponseBroadcast();


        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);

        MyMapListeners listeners = new MyMapListeners();
        mMap.setOnMapLongClickListener(listeners);
        mMap.setOnMarkerDragListener(listeners);
        showMe();
    }

    private void showMe() {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(myPossition);
        markerOptions.draggable(false);

        markerOptions.title("Готель Закарпаття");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(240.0f));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myPossition, 17);
        startMarker = mMap.addMarker(markerOptions);
        startMarker.showInfoWindow();

        mMap.animateCamera(cameraUpdate);

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(RESPONSE_BROADCAST);
        registerReceiver(broadcast, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast);
    }

    public void drawMarker(GoogleMapJsonParser parser) {
        if (endMarker != null){
            endMarker.remove();
            endMarker = null;
        }
        LatLng endPoint = parser.getEndPoint();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(endPoint);
        markerOptions.draggable(true);
        markerOptions.title(parser.getDistance());

        markerOptions.snippet(parser.getEndAddress());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(139.0f));
        endMarker= mMap.addMarker(markerOptions);
        endMarker.showInfoWindow();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(endPoint, 17);
        mMap.animateCamera(cameraUpdate);
    }
    private void drawLine(Steps... steps ) {

        if (mPolyline != null) {
                mPolyline.remove();
                mPolyline = null;
        }
            PolylineOptions polylineOptions = new PolylineOptions().width(5).color(Color.parseColor("#00f060")).geodesic(true);
           LatLng startPosition = null;
           LatLng endPosition = null;
            for (int i = 0 ; i < steps.length; i++) {
                startPosition = new LatLng(steps[i].getStartLocationLat(), steps[i].getStartLocationLng());
                polylineOptions.add(startPosition);
                endPosition = new LatLng(steps[i].getEndLocationLat(), steps[i].getEndLocationLng());
                polylineOptions.add(endPosition);
            }

            mPolyline = mMap.addPolyline(polylineOptions);

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(endPosition, 17);
            mMap.animateCamera(cameraUpdate);
    }
    private String createRequest(LatLng origin, LatLng destination ){

        String s ="http://maps.googleapis.com/maps/api/directions/json?origin=" +
                origin.latitude +
                "," +
                origin.longitude +
                "&destination=" +
                destination.latitude +
                "," +
                destination.longitude +
                "&sensor=false";
        return s;
    }
    private class MyMapListeners implements GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {
        @Override
        public void onMapLongClick(LatLng destination) {


              LoadCoordinates loadCoordinates = new LoadCoordinates(MyActivity.this);
              String request = createRequest(myPossition, destination);
              loadCoordinates.execute(request);

        }

        @Override
        public void onMarkerDragStart(Marker marker) {

        }

        @Override
        public void onMarkerDrag(Marker marker) {

        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            LatLng destination= new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            LoadCoordinates loadCoordinates = new LoadCoordinates(MyActivity.this);
            String request = createRequest(myPossition, destination);
            loadCoordinates.execute(request);
        }
    }
    private class ResponseBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                String response =   bundle.getString(MyActivity.RESPONSE_VALUE);
                parser = new GoogleMapJsonParser(response);
                drawLine(parser.getLegsArray());
                drawMarker(parser);

        }
    }
}
