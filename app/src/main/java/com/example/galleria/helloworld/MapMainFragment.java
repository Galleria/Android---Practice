package com.example.galleria.helloworld;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.galleria.helloworld.adapter.MapCardAdapter;
import com.example.galleria.helloworld.model.MapCard;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapMainFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private OnFragmentInteractionListener mListener;
    private FragmentTransaction ft;

    private com.google.android.gms.maps.MapFragment map_fragment;
    private GoogleMap gMap;

    private FloatingActionButton locationActionButton;
    private FloatingActionButton plusActionButton;

    private RecyclerView mapCardRecyclerView;

    public MapMainFragment() {
        // Required empty public constructor
    }

    public static MapMainFragment newInstance() {
        MapMainFragment fragment = new MapMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //com.google.android.gms.maps.MapFragment.newInstance();
        View rootView = inflater.inflate(R.layout.map_main, container, false);

        map_fragment = com.google.android.gms.maps.MapFragment.newInstance();
        map_fragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                gMap = googleMap;
            }
        });

        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.google_map, map_fragment);
        ft.commit();

        locationActionButton = (FloatingActionButton) rootView.findViewById(R.id.locationActionButton);
        locationActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyLocation();
            }
        });

        mapCardRecyclerView = (RecyclerView) rootView.findViewById(R.id.card_list);

        List<MapCard> mapCardList = new ArrayList<>();
        mapCardList.add(new MapCard());
        mapCardList.add(new MapCard());
        mapCardList.add(new MapCard());
        mapCardList.add(new MapCard());
        mapCardList.add(new MapCard());
        mapCardList.add(new MapCard());

        MapCardAdapter mapCardAdapter = new MapCardAdapter(mapCardList);

        mapCardRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mapCardRecyclerView.setAdapter(mapCardAdapter);

        mapCardAdapter.notifyDataSetChanged();


        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        System.out.println("onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    MarkerOptions m1;

    @Override
    public void onLocationChanged(Location location) {

        System.out.println( "location.getLatitude() "+location.getLatitude() );
        System.out.println( "location.getLongitude() "+location.getLongitude() );

        //System.out.println( location );
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //LatLng latLng = new LatLng( 123.5, 50 );

        m1 = new MarkerOptions()
                .position(latLng)
                .title("Hello world");

        gMap.clear();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        gMap.animateCamera(cameraUpdate);

        gMap.addMarker(m1);


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        public void onBlankFragment2Interaction(String string);
    }

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private synchronized void getMyLocation() {
        if (
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                        ==
                PackageManager.PERMISSION_GRANTED
                        &&
                ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        ==
                PackageManager.PERMISSION_GRANTED) {

            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(UPDATE_INTERVAL)
                    .setFastestInterval(FASTEST_INTERVAL);

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient , mLocationRequest , this);

        }else{

            Context context = getActivity();
            CharSequence text = "PERMISSION_DENIED";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            ActivityCompat.requestPermissions(getActivity(),new String[] {
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    REQUEST_CODE_ASK_PERMISSIONS);
        }



    }

}
