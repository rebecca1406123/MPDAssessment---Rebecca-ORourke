package org.me.gcu.mpd;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.util.Log;

public class MapsActivity<GoogleMap> extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener
{

    private GoogleMap mMap;

    private ViewSwitcher avw;
    private Button s1Button;
    private Button s2Button;
    private RadioGroup mapTypeGroup;
    private RadioButton normalViewButton = (RadioButton)findViewById(R.id.normalViewRadio);
    private RadioButton terrainViewButton;
    private RadioButton hybridViewButton;
    private RadioButton satelliteViewButton;
    private CheckBox panZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapTypeGroup = (RadioGroup)findViewById(R.id.mapTypeGroup);
        terrainViewButton = (RadioButton)findViewById(R.id.terrainViewRadio);
        hybridViewButton = (RadioButton)findViewById(R.id.hybridViewRadio);
        satelliteViewButton = (RadioButton)findViewById(R.id.satelliteViewRadio);
        panZoom = (CheckBox)findViewById(R.id.panZoom);

        Log.e(getPackageName(), "just before avw");
        avw = (ViewSwitcher) findViewById(R.id.vwSwitch);
        if (avw == null)
        {
            Toast.makeText(getApplicationContext(), "Null ViewSwicther", Toast.LENGTH_LONG);
            Log.e(getPackageName(), "null pointer");
        }
        s1Button = (Button) findViewById(R.id.screen1Button);
        s2Button = (Button) findViewById(R.id.screen2Button);
        s1Button.setOnClickListener(this);
        s2Button.setOnClickListener(this);
        normalViewButton.setOnClickListener(this);
        terrainViewButton.setOnClickListener(this);
        hybridViewButton.setOnClickListener(this);
        satelliteViewButton.setOnClickListener(this);
        normalViewButton.toggle();
        panZoom.setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    public Context getApplicationContext() {
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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng glasgow = new LatLng(55.86,-4.25);
        LatLng paisley = new LatLng(55.8473, 4.4401);

        mMap.addMarker(new MarkerOptions().position(glasgow).title("Marker in Glasgow"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(glasgow));

        mMap.addMarker(new MarkerOptions().position(paisley).title("marker in Paisley"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(paisley));
    }

    @Override
    public void onClick(View arg0)
    {
        if (arg0 == s1Button)
        {
            avw.showNext();
        }
        else
        if (arg0 == s2Button)
        {
            avw.showPrevious();
        }
        else
        if (arg0 == normalViewButton)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        else
        if (arg0 == terrainViewButton)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else
        if (arg0 == hybridViewButton)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else
        if (arg0 == satelliteViewButton)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

        if (panZoom.isChecked())
        {
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }
        else
        {
            mMap.getUiSettings().setZoomControlsEnabled(false);
        }
    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {

    }
} // End of Main Activity
