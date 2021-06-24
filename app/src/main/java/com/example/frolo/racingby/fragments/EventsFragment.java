package com.example.frolo.racingby.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frolo.racingby.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventsFragment extends Fragment implements OnMapReadyCallback {

    private MapView mMapView;
    private GoogleMap googleMap;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.getActivity().setTitle("Мероприятия");
        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        LatLng Pinsk = new LatLng(52.1313878, 26.1099338);
        this.googleMap.addMarker(new MarkerOptions()
                .position(Pinsk)
                .title("Пинск, стадион ДОСААФ"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(Pinsk));

        LatLng Logoisk = new LatLng(54.181657, 27.8097912);
        this.googleMap.addMarker(new MarkerOptions()
                .position(Logoisk)
                .title("Логойск, ГСОК «Логойск»"));

        LatLng Pinsk2 = new LatLng(52.131416, 26.109744);
        this.googleMap.addMarker(new MarkerOptions()
                .position(Pinsk2)
                .title("Пинск, стадион ДОСААФ"));

        LatLng Minsk = new LatLng(53.868327, 27.530472);
        this.googleMap.addMarker(new MarkerOptions()
                .position(Minsk)
                .title("Минск, аэропорт Минск-1"));

        LatLng Raubichi = new LatLng(54.868327, 27.736615);
        this.googleMap.addMarker(new MarkerOptions()
                .position(Raubichi)
                .title("Раубичи, РЦОП Раубичи"));

        LatLng Minsk2 = new LatLng(53.868327, 27.530472);
        this.googleMap.addMarker(new MarkerOptions()
                .position(Minsk2)
                .title("Минск, аэропорт Минск-1"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(Minsk2));
    }
}