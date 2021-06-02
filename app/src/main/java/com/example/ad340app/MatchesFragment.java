package com.example.ad340app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad340app.ViewModel.MatchesViewModel;
import com.example.ad340app.ViewModel.SettingViewModel;

import java.util.ArrayList;


public class MatchesFragment extends Fragment {

    public ArrayList matchList = new ArrayList();
    private MatchesViewModel matchesViewModel = new MatchesViewModel();
    private RecyclerView recyclerView;
    SettingViewModel settingViewModel;
    ProductCardRecyclerViewAdapter adapter;

    LocationManager locationManager;
    Location userLocation;
    Float MaximunR = 16093.44f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            matchList = getArguments().getParcelableArrayList(Constants.KEY_MATCH);
        }
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        userLocation = new Location(LocationManager.GPS_PROVIDER);

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        adapter = new ProductCardRecyclerViewAdapter(getContext(), matchList);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));
        updateGps(view);
        getMatches();
        return view;
    }

    @Override
    public void onPause() {
        matchesViewModel.clear();
        super.onPause();
    }

    private boolean checkLocation() {
        if (!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this.getContext());
        dialog.setTitle(R.string.enable_location)
                .setMessage(getString(R.string.location_message))
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.location_cancel, (paramDialogInterface, paramInt) -> {
                });
        dialog.show();
    }

    public void updateGps(View view) {
        if (!checkLocation()) {
            return;
        }
        if (
                ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListener);
            Toast.makeText(this.getContext(), R.string.provider_started_running, Toast.LENGTH_LONG).show();
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            userLocation.setLatitude(location.getLatitude());
            userLocation.setLongitude(location.getLongitude());
            getMatches();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    public void getMatches() {
        matchesViewModel.getMatche((ArrayList<MatchView> matches) -> {
            ArrayList<MatchView> OutOfArea = new ArrayList<>();
            float[] distance = new float[1];
            for (MatchView match : matches) {
                Location.distanceBetween(Double.parseDouble(match.lat), Double.parseDouble(match.longitude), userLocation.getLatitude(), userLocation.getLongitude(), distance);
                if (distance[0] > MaximunR) {
                    OutOfArea.add(match);
                }
            }
            matches.removeAll(OutOfArea);
            adapter.setMatchesList(matches);
            adapter.notifyDataSetChanged();
        });
    }
}