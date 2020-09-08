package com.example.yanolkka.src.activities.maps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanolkka.R;
import com.example.yanolkka.src.activities.maps.interfaces.MapsActivityView;
import com.example.yanolkka.src.common.base.BaseActivity;
import com.example.yanolkka.src.common.objects.MarkerData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.yanolkka.src.ApplicationClass.BASE_LATITUDE;
import static com.example.yanolkka.src.ApplicationClass.BASE_LONGITUDE;
import static com.example.yanolkka.src.ApplicationClass.DATE_FORMAT;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, MapsActivityView {

    private GoogleMap mMap;
    private MarkerOptions myLocation;

    private double latitude, longitude;
    private int numAdult, numKid;
    private String strCheckIn, strCheckOut;

    private View markerView;
    private TextView tvPrice;

    private ArrayList<MarkerData> markerDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", BASE_LATITUDE);
        longitude = intent.getDoubleExtra("longitude", BASE_LONGITUDE);
        numAdult = intent.getIntExtra("numAdult", 2);
        numKid = intent.getIntExtra("numKid", 0);
        Calendar today = Calendar.getInstance();
        int checkInMonth = intent.getIntExtra("checkInMonth", today.get(Calendar.MONTH));
        int checkInDate = intent.getIntExtra("checkInDate", today.get(Calendar.DATE));
        today.add(Calendar.DATE, 1);
        int checkOutMonth = intent.getIntExtra("checkOutMonth", today.get(Calendar.MONTH));
        int checkOutDate = intent.getIntExtra("checkOutDate", today.get(Calendar.DATE));

        Calendar checkIn = Calendar.getInstance();
        checkIn.set(Calendar.MONTH, checkInMonth);
        checkIn.set(Calendar.DATE, checkInDate);
        strCheckIn = DATE_FORMAT.format(checkIn.getTime());

        Calendar checkOut = Calendar.getInstance();
        checkOut.set(Calendar.MONTH, checkOutMonth);
        checkOut.set(Calendar.DATE, checkOutDate);
        strCheckOut = DATE_FORMAT.format(checkOut.getTime());

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15f));

        LatLng currentLatLng = new LatLng(latitude, longitude);
        Bitmap locationBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.location_indicator)).getBitmap();
        myLocation = new MarkerOptions();
        myLocation.icon(BitmapDescriptorFactory.fromBitmap(locationBitmap));
        myLocation.position(currentLatLng);
        mMap.addMarker(myLocation);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f));

        setCustomMarkerView();

        MapsService mapsService = new MapsService(this);
        mapsService.getMapInfo(latitude, longitude, 5, strCheckIn, strCheckOut, numAdult, numKid);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.networkError) : message);
    }

    @Override
    public void getMarkerData(ArrayList<MarkerData> markers) {
        markerDataList.addAll(markers);

        for (MarkerData markerData : markerDataList){
            addMarker(markerData);
        }
    }

    private void setCustomMarkerView(){
        markerView = LayoutInflater.from(this).inflate(R.layout.map_marker, null);
        tvPrice = markerView.findViewById(R.id.tv_tag_marker);
    }

    private Marker addMarker(MarkerData markerData){
        LatLng position = markerData.getLatLng();
        int idx = markerData.getAccomIdx();
        int price = markerData.getPrice();

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String strPrice = decimalFormat.format(price)+"원";
        tvPrice.setText(strPrice);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, markerView)));

        return mMap.addMarker(markerOptions);
    }

    private Bitmap createDrawableFromView(Context context, View view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0,0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight()
                , Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}