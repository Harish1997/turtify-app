package rexzen.turtify;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jsibbold.zoomage.ZoomageView;

import java.util.HashMap;
import java.util.Iterator;

import se.simbio.encryption.Encryption;

/**
 * Created by harishananth on 10/06/17.
 */

public class turtleitem extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap gmap;
    ZoomageView myzoomimg;
    MapView map;
    Encryption encryption = Encryption.getDefault("SomeKey", "SomeSalt", new byte[16]);


    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    private ProgressDialog progDailog;
    Activity activity;
    private Handler handler;
    private Runnable runnable;
    private DatabaseReference root;
    public TextView date;
    String getdate, gettitle;
    String aturid,desc,leader, count, catgeory, lat="13.0827", lon="80.2707", neckwidth, cavitywidth, surfacetobot, surfacetoeggs, link;
    String titc, datc,id;
    Button mapb;
    Long turtletime = 4752000000L;
    TextView titletxt,datetxt,description,categorytxt,leadertxt;
    private static final String TAG = "MainActivity";
    ImageView img;


    HashMap<String,String> months=new HashMap<>();
    HashMap<String,String> days=new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turtleitem);
        months.put("1","January");months.put("2","February");months.put("3","March"); months.put("4","April"); months.put("5","May"); months.put("6","June"); months.put("7","July"); months.put("8","August"); months.put("9","September");months.put("10","October"); months.put("11","November"); months.put("12","December");
        days.put("1","st"); days.put("2","nd"); days.put("3","rd"); days.put("4","th"); days.put("5","th"); days.put("6","th"); days.put("7","th"); days.put("8","th"); days.put("9","th"); days.put("10","th"); days.put("11","th"); days.put("12","th"); days.put("13","th"); days.put("14","th"); days.put("15","th"); days.put("16","th"); days.put("17","th"); days.put("18","th"); days.put("19","th"); days.put("20","th"); days.put("21","st"); days.put("22","nd"); days.put("23","rd"); days.put("24","th"); days.put("25","th"); days.put("26","th"); days.put("27","th"); days.put("28","th"); days.put("29","th"); days.put("30","th"); days.put("31","st");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id=bundle.getString("turid");
            titc = bundle.getString("title");
            datc = bundle.getString("date");
        }
        root = FirebaseDatabase.getInstance().getReference().child(id);
        map = (MapView) findViewById(R.id.mapView);

        titletxt=(TextView)findViewById(R.id.titletur);
        datetxt=(TextView)findViewById(R.id.datetext);
        description=(TextView)findViewById(R.id.desctxt);
        categorytxt=(TextView)findViewById(R.id.categorytt);
        leadertxt=(TextView)findViewById(R.id.leadertxt);
        myzoomimg=(ZoomageView)findViewById(R.id.turtleimg);
        img=(ImageView)findViewById(R.id.imageView5);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = gettitle+"\n\n"+"By: "+leader+"\n\n"+link+"\n\n"+desc;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(sharingIntent);
            }
        });

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        map.onCreate(mapViewBundle);
        map.getMapAsync(this);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        map.onSaveInstanceState(mapViewBundle);
    }

    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            aturid = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            catgeory = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            getdate = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            desc=encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            lat = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            leader = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            link = (String) ((DataSnapshot) i.next()).getValue();
            lon = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());
            gettitle = encryption.decryptOrNull((String) ((DataSnapshot) i.next()).getValue());


        }
        String arr[]=getdate.split("-");

        Glide.with(this).load(link).into(myzoomimg);

        titletxt.setText(gettitle);
        datetxt.setText("As dated on: "+arr[2]+days.get(arr[2])+" "+months.get(arr[1])+", "+arr[0]);
        description.setText(desc);
        categorytxt.setText(catgeory);
        leadertxt.setText(leader);
        gmap.setMinZoomPreference(14);
        if(lat!="null" && lon!="null") {
            LatLng ny = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
            Toast.makeText(this,lat,Toast.LENGTH_SHORT).show();
            gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
            gmap.addMarker(new MarkerOptions()
                    .position(ny).title(gettitle)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .draggable(false).visible(true));
        }


    }
    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        map.onStop();
    }
    @Override
    protected void onPause() {
        map.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        map.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        if(lat!=null && lon!=null) {
            LatLng ny = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
            gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
        }
    }
}
