package rexzen.turtify;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import se.simbio.encryption.Encryption;

/**
 * Created by harishananth on 01/03/17.
 */

public class Recycleritem extends AppCompatActivity implements OnMapReadyCallback{
    WebView mywebview;
    private GoogleMap gmap;
    ZoomageView myzoomimg;
    MapView map;


    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private ProgressDialog progDailog;
    Activity activity;
    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond, Titletxt, neckwidthtxt, leadertxt, datetext, cavitywidthtxt, surftobottxt, surftoeggstxt, counttxt;
    private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;
    private DatabaseReference root;
    public String cal;
    public TextView date;
    public long diff, days;
    String getdate, gettitle;
    Switch reminder;
    String nestid,leader, count, catgeory, lat="13.0827", lon="80.2707", neckwidth, cavitywidth, surfacetobot, surfacetoeggs, link;
    String titc, datc,id;
    Long turtletime = 4752000000L;
    private static final String TAG = "MainActivity";
    private Spinner calendarIdSpinner;
    private Hashtable<String,String> calendarIdTable;
    Encryption encryption = Encryption.getDefault("SomeKey", "SomeSalt", new byte[16]);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleritem);



        map = (MapView) findViewById(R.id.mapv);
        reminder = (Switch) findViewById(R.id.alswitch);
        myzoomimg=(ZoomageView)findViewById(R.id.turtleimg);
        calendarIdSpinner = (Spinner) findViewById(R.id.myspinner);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reminder.isChecked() == true) {

                    if (CalendarHelper.haveCalendarReadWritePermissions(Recycleritem.this))
                    {
                        addNewEvent();
                    }
                    else
                    {
                        CalendarHelper.requestCalendarReadWritePermission(Recycleritem.this);
                    }
                }
            }
        });
        calendarIdSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        if (CalendarHelper.haveCalendarReadWritePermissions(this))
        {
            //Load calendars
            calendarIdTable = CalendarHelper.listCalendarId(this);
            updateCalendarIdSpinner();

        }


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id=bundle.getString("nestid");
            titc = bundle.getString("title");
            datc = bundle.getString("date");
        }
        root = FirebaseDatabase.getInstance().getReference().child(id);


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

       // Toast.makeText(this, "title is" + titc, Toast.LENGTH_SHORT).show();
        txtTimerDay = (TextView) findViewById(R.id.txtTimerDay);
        txtTimerHour = (TextView) findViewById(R.id.txtTimerHour);
        txtTimerMinute = (TextView) findViewById(R.id.txtTimerMinute);
        txtTimerSecond = (TextView) findViewById(R.id.txtTimerSecond);
        Titletxt = (TextView) findViewById(R.id.titletxt);
        counttxt = (TextView) findViewById(R.id.counttxt);
        leadertxt = (TextView) findViewById(R.id.leadertxt);
        datetext = (TextView) findViewById(R.id.datetxt);
        surftobottxt = (TextView) findViewById(R.id.surftobottomtxt);
        surftoeggstxt = (TextView) findViewById(R.id.surftoeggstxt);
        neckwidthtxt = (TextView) findViewById(R.id.neckwidthtxt);
        cavitywidthtxt = (TextView) findViewById(R.id.cavitywidthtxt);


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Recycleritem.this, MapsActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                startActivity(intent);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode==CalendarHelper.CALENDARHELPER_PERMISSION_REQUEST_CODE)
        {
            if (CalendarHelper.haveCalendarReadWritePermissions(this))
            {
                Toast.makeText(this, (String)"Have Calendar Read/Write Permission.",
                        Toast.LENGTH_LONG).show();

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {
            nestid = (String) ((DataSnapshot) i.next()).getValue();
            catgeory = (String) ((DataSnapshot) i.next()).getValue();
            cavitywidth = (String) ((DataSnapshot) i.next()).getValue();
            count = (String) ((DataSnapshot) i.next()).getValue();
            getdate = (String) ((DataSnapshot) i.next()).getValue();
            lat = (String) ((DataSnapshot) i.next()).getValue();
            leader = (String) ((DataSnapshot) i.next()).getValue();
            link = (String) ((DataSnapshot) i.next()).getValue();
            lon = (String) ((DataSnapshot) i.next()).getValue();
            neckwidth = (String) ((DataSnapshot) i.next()).getValue();
            surfacetobot = (String) ((DataSnapshot) i.next()).getValue();
            surfacetoeggs = (String) ((DataSnapshot) i.next()).getValue();
            gettitle = (String) ((DataSnapshot) i.next()).getValue();




        }
        String ddate=encryption.decryptOrNull(getdate);
        countDownStart(ddate);

        String dtitc = encryption.decryptOrNull(gettitle);
        String dcount=encryption.decryptOrNull(count);
        String dneckwidth=encryption.decryptOrNull(neckwidth);
        String dleader=encryption.decryptOrNull(leader);
        String dsurfacetobot=encryption.decryptOrNull(surfacetobot);
        String dsurfacetoeggs=encryption.decryptOrNull(surfacetoeggs);
        String dcavitywidth=encryption.decryptOrNull(cavitywidth);
        String dlat=encryption.decryptOrNull(lat);
        String dlon=encryption.decryptOrNull(lon);




        //Toast.makeText(this,lat+" "+lon,Toast.LENGTH_SHORT).show();
        Glide.with(this).load(link).into(myzoomimg);
        Titletxt.setText(dtitc);
        counttxt.setText(dcount);
        datetext.setText(ddate);
        neckwidthtxt.setText(dneckwidth+" centimetres");
        leadertxt.setText(dleader);
        surftobottxt.setText(dsurfacetobot+" centimetres");
        surftoeggstxt.setText(dsurfacetoeggs+" centimetres");
        cavitywidthtxt.setText(dcavitywidth+" centimetres");
        gmap.setMinZoomPreference(12);
        if(dlat!="null" && dlon!="null") {
            LatLng ny = new LatLng(Double.valueOf(dlat), Double.valueOf(dlon));
            //gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
            gmap.addMarker(new MarkerOptions()
                    .position(ny).title(dtitc)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .draggable(false).visible(true));
        }

    }

    public void countDownStart(final String datget) {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");


                    Date futureDate = dateFormat.parse(datget);
                    Date currentDate = new Date();
                    if (currentDate != null) {
                        diff = futureDate.getTime()
                                - currentDate.getTime() + turtletime;
                        Log.d("mylog",String.valueOf(diff));
                        Log.d("Date",String.valueOf(datget));
                        days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText(""
                                + String.format("%02d", minutes));
                        txtTimerSecond.setText(""
                                + String.format("%02d", seconds));
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }
    private void updateCalendarIdSpinner()
    {
        if (calendarIdTable==null)
        {
            return;
        }

        List<String> list = new ArrayList<String>();

        Enumeration e = calendarIdTable.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            list.add(key);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Recycleritem.this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calendarIdSpinner.setAdapter(dataAdapter);
    }


    private void addNewEvent()
    {
        if (calendarIdTable==null)
        {
            Toast.makeText(this, (String)"No calendars found. Please ensure at least one google account has been added.",
                    Toast.LENGTH_LONG).show();
            calendarIdTable = CalendarHelper.listCalendarId(this);


            return;
        }

int thour= Integer.parseInt(txtTimerHour.getText().toString());
        int tdays= Integer.parseInt(txtTimerDay.getText().toString());

        final long endtime = 55*24*60*60*1000;
        final long tenMinutes = 5000;

        long finaltime = (new Date()).getTime() + endtime;
        long timefromnow = (new Date()).getTime() + tenMinutes;


        String calendarString = calendarIdSpinner.getSelectedItem().toString();

        int calendar_id = Integer.parseInt(calendarIdTable.get(calendarString));

        CalendarHelper.MakeNewCalendarEntry(this, titc+" -turtify event", "Keep an eye on-"+titc+"nest", "Somewhere",timefromnow,timefromnow+endtime,false,true,calendar_id,3);

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

