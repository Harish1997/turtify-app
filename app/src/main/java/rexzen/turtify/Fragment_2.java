package rexzen.turtify;

import android.*;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pl.droidsonroids.gif.GifTextView;
import se.simbio.encryption.Encryption;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by harishananth on 02/02/17.
 */
public class Fragment_2 extends Fragment {

    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;

    Button upload, done;
    private LocationListener locationListener;
    public TextView chtext, titletext;
    private LocationManager locationManager;
    private static final String TAG = "Storage#MainActivity";
    RadioButton nest, live, dead;
    public EditText nesttitle, leader, count, neckwidth, surftoeggs, surftobottom, cavwidth;
    private static final int RC_TAKE_PICTURE = 101;
    public static int counter = 0;
    private DatabaseReference root3, root4;
    Encryption encryption = Encryption.getDefault("SomeKey", "SomeSalt", new byte[16]);


    String stcategory, sttitle, stleader, stcount, stneckwidth, stsurftobot, stsurftoeggs, stcavwidth;
    private static final String KEY_FILE_URI = "key_file_uri";
    private static final String KEY_DOWNLOAD_URL = "key_download_url";

    private BroadcastReceiver mBroadcastReceiver;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    String picdownurl;
    int x = 1;
    Calendar calendar;
    String year, month, day;
    String date;
    public String lat, lon;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    private String user_name, room_name;
    public TextView egg1, egg2, egg3, egg5, egg4, egg6, nestg, nestcav;

    private String temp_key, temp_key1;
    private Uri mDownloadUrl = null;
    private Uri mFileUri = null;
    private DatabaseReference root2 = FirebaseDatabase.getInstance().getReference().getRoot();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_b, container, false);
        mAuth = FirebaseAuth.getInstance();

        root3 = FirebaseDatabase.getInstance().getReference().child("TD");


        root3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot, view);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot, view);
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


        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);


        upload = (Button) view.findViewById(R.id.uploadb);
        done = (Button) view.findViewById(R.id.submitb);


        nestg = (TextView) view.findViewById(R.id.textView4);
        titletext = (TextView) view.findViewById(R.id.chtextview);
        chtext = (TextView) view.findViewById(R.id.textdesc);
        nest = (RadioButton) view.findViewById(R.id.tnestradio);
        live = (RadioButton) view.findViewById(R.id.turtleliveradio);
        dead = (RadioButton) view.findViewById(R.id.turtledeadradio);
        nesttitle = (EditText) view.findViewById(R.id.titleget);
        leader = (EditText) view.findViewById(R.id.leaderget);
        count = (EditText) view.findViewById(R.id.noofeggs);
        neckwidth = (EditText) view.findViewById(R.id.neckwidth);
        surftobottom = (EditText) view.findViewById(R.id.surftobot);
        surftoeggs = (EditText) view.findViewById(R.id.surfacetoeggs);
        cavwidth = (EditText) view.findViewById(R.id.cavitywidth);
        egg1 = (TextView) view.findViewById(R.id.textView2);
        egg2 = (TextView) view.findViewById(R.id.textView10);
        egg3 = (TextView) view.findViewById(R.id.textView13);
        egg4 = (TextView) view.findViewById(R.id.textView15);
        egg5 = (TextView) view.findViewById(R.id.textView16);
        egg6 = (TextView) view.findViewById(R.id.textView9);


        titletext.setVisibility(View.INVISIBLE);
        chtext.setVisibility(View.INVISIBLE);
        nesttitle.setVisibility(View.INVISIBLE);
        leader.setVisibility(View.INVISIBLE);
        neckwidth.setVisibility(View.INVISIBLE);
        count.setVisibility(View.INVISIBLE);
        surftoeggs.setVisibility(View.INVISIBLE);
        surftobottom.setVisibility(View.INVISIBLE);
        cavwidth.setVisibility(View.INVISIBLE);
        egg1.setVisibility(View.INVISIBLE);
        egg2.setVisibility(View.INVISIBLE);
        egg3.setVisibility(View.INVISIBLE);
        egg4.setVisibility(View.INVISIBLE);
        egg5.setVisibility(View.INVISIBLE);
        egg6.setVisibility(View.INVISIBLE);
        upload.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);


        date = Year + "-" + Month + "-" + day;
        // Toast.makeText(getActivity(),date,Toast.LENGTH_SHORT).show();
        //if (login.st == 0)
            //Toast.makeText(getActivity(), "Please Login", Toast.LENGTH_LONG).show();

        nest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titletext.setVisibility(View.VISIBLE);  //fortitle
                chtext.setVisibility(View.VISIBLE);  //fortitle
                nesttitle.setVisibility(View.VISIBLE); //edittext
                leader.setVisibility(View.VISIBLE);  //will change to desc
                upload.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);
                titletext.setText("Enter a suitable title for the nest:");
                chtext.setText("Enter team leader name");
                count.setVisibility(View.VISIBLE);
                neckwidth.setVisibility(View.VISIBLE);
                surftoeggs.setVisibility(View.VISIBLE);
                surftobottom.setVisibility(View.VISIBLE);
                cavwidth.setVisibility(View.VISIBLE);
                egg1.setVisibility(View.VISIBLE);
                egg2.setVisibility(View.VISIBLE);
                egg3.setVisibility(View.VISIBLE);
                egg4.setVisibility(View.VISIBLE);
                egg5.setVisibility(View.VISIBLE);
                egg6.setVisibility(View.VISIBLE);

                nestg.setVisibility(View.VISIBLE);

                stcategory = "nest";
                getlocation();
                nest.setChecked(true);
                dead.setChecked(false);
                live.setChecked(false);
            }
        });

        live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titletext.setVisibility(View.VISIBLE);  //fortitle
                chtext.setVisibility(View.VISIBLE);  //fortitle
                nesttitle.setVisibility(View.VISIBLE); //edittext
                leader.setVisibility(View.VISIBLE);  //will change to desc
                upload.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);

                titletext.setText("Enter a suitable title for the report:");
                chtext.setText("Enter a description");
                //leader.getLayoutParams().height=300;
                leader.setScroller(new Scroller(getContext()));
                leader.setVerticalScrollBarEnabled(true);
                leader.setMinLines(2);
                leader.setMaxLines(10);

                count.setVisibility(View.INVISIBLE);
                neckwidth.setVisibility(View.INVISIBLE);
                surftoeggs.setVisibility(View.INVISIBLE);
                surftobottom.setVisibility(View.INVISIBLE);
                cavwidth.setVisibility(View.INVISIBLE);
                egg1.setVisibility(View.INVISIBLE);
                egg2.setVisibility(View.INVISIBLE);
                egg3.setVisibility(View.INVISIBLE);
                egg4.setVisibility(View.INVISIBLE);
                egg5.setVisibility(View.INVISIBLE);
                egg6.setVisibility(View.INVISIBLE);


                getlocation();
                stcategory = "live turtle";
                live.setChecked(true);
                nest.setChecked(false);
                dead.setChecked(false);
            }
        });
        dead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titletext.setVisibility(View.VISIBLE);  //fortitle
                chtext.setVisibility(View.VISIBLE);  //fortitle
                nesttitle.setVisibility(View.VISIBLE); //edittext
                leader.setVisibility(View.VISIBLE);  //will change to desc
                upload.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);

                titletext.setText("Enter a suitable title for the report:");
                chtext.setText("Enter a description");

                count.setVisibility(View.INVISIBLE);
                neckwidth.setVisibility(View.INVISIBLE);
                surftoeggs.setVisibility(View.INVISIBLE);
                surftobottom.setVisibility(View.INVISIBLE);
                cavwidth.setVisibility(View.INVISIBLE);
                egg1.setVisibility(View.INVISIBLE);
                egg2.setVisibility(View.INVISIBLE);
                egg3.setVisibility(View.INVISIBLE);
                egg4.setVisibility(View.INVISIBLE);
                egg5.setVisibility(View.INVISIBLE);
                egg6.setVisibility(View.INVISIBLE);


                getlocation();
                stcategory = "dead turtle";
                dead.setChecked(true);
                nest.setChecked(false);
                live.setChecked(false);
            }
        });

        if (checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,getContext())) {
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            Location lastKnownLocation = null;
            List<String> providers = null;
            if (locationManager != null) providers = locationManager.getAllProviders();
            if (providers != null) {
                for (int i = 0; i < providers.size(); i++) {
                    if (locationManager != null) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                1);


                        lastKnownLocation = locationManager.getLastKnownLocation(providers.get(i));
                    }
                    if(lastKnownLocation != null)
                    {
                        LatLng  position = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                      //  Toast.makeText(getActivity(),position.toString(),Toast.LENGTH_LONG).show();
                        lat= String.valueOf(position.latitude);
                        lon= String.valueOf(position.longitude);
                        break;
                    }
                }
            }
        }
        else
        {
            requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION,PERMISSION_REQUEST_CODE_LOCATION,getContext());
        }


        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                {
                    lat=String.valueOf(location.getLatitude());
                    lon=String.valueOf(location.getLongitude());
                    x++;
  //                  Toast.makeText(getActivity(), "lat is" + location.getLatitude() + "lon is" + location.getLongitude(), Toast.LENGTH_LONG).show();

                }
            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                getlocation();

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getlocation();
                Intent intent = new Intent(getActivity(), firebaseupload.class);
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login.st==1) {
                    getlocation();
                    Long tsLong = System.currentTimeMillis()/1000;
                    String ts = tsLong.toString();

                    switch (stcategory) {
                        case "nest": {
                                stleader = leader.getText().toString();
                                stcount = count.getText().toString();
                                stneckwidth = neckwidth.getText().toString();
                                stsurftobot = surftobottom.getText().toString();
                                stsurftoeggs = surftoeggs.getText().toString();
                                stcavwidth = cavwidth.getText().toString();


                                picdownurl = firebaseupload.downurl;

                                Map<String, Object> map = new HashMap<String, Object>();
                                map.put(ts, "");
                                root.updateChildren(map);
                                root = FirebaseDatabase.getInstance().getReference().child(ts);
                                Map<String, Object> map1 = new HashMap<String, Object>();
                                temp_key = root.push().getKey();
                                root.updateChildren(map1);


                                DatabaseReference message_root = root.child(temp_key);
                                Map<String, Object> map2 = new HashMap<String, Object>();
                                map2.put("anestid",encryption.encryptOrNull(ts));
                                map2.put("title",encryption.encryptOrNull(nesttitle.getText().toString()));
                                map2.put("leader",encryption.encryptOrNull(stleader));
                                map2.put("category",encryption.encryptOrNull(stcategory));
                                map2.put("date",encryption.encryptOrNull(date));
                                map2.put("count",encryption.encryptOrNull(stcount));
                                map2.put("neck_width",encryption.encryptOrNull(stneckwidth));
                                map2.put("surftobot",encryption.encryptOrNull(stsurftobot));
                                map2.put("surftoeggs",encryption.encryptOrNull(stsurftoeggs));
                                map2.put("cavwidth",encryption.encryptOrNull(stcavwidth));
                                map2.put("link",picdownurl);
                                map2.put("lat",encryption.encryptOrNull(lat));
                                map2.put("lon",encryption.encryptOrNull(lon));
                                message_root.updateChildren(map2);


                                root2 = FirebaseDatabase.getInstance().getReference().child("TD");
                                Map<String, Object> map3 = new HashMap<String, Object>();
                                temp_key1 = root2.push().getKey();
                                root2.updateChildren(map3);
                                DatabaseReference message_root2 = root2.child(temp_key1);
                                Map<String, Object> map4 = new HashMap<String, Object>();
                                map4.put("anestid",ts);
                                map4.put("date",date);
                                map4.put("title",nesttitle.getText().toString());
                                message_root2.updateChildren(map4);

                            }
                            break;


                        case "live turtle":
                        {

                            stleader = leader.getText().toString();
                            picdownurl = firebaseupload.downurl;

                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(ts, "");
                            root.updateChildren(map);
                            root = FirebaseDatabase.getInstance().getReference().child(ts);
                            Map<String, Object> map1 = new HashMap<String, Object>();
                            temp_key = root.push().getKey();
                            root.updateChildren(map1);
                            DatabaseReference message_root = root.child(temp_key);
                            Map<String, Object> map2 = new HashMap<String, Object>();
                            map2.put("anestid",encryption.encryptOrNull(ts));
                            map2.put("category",encryption.encryptOrNull(stcategory));
                            map2.put("date",encryption.encryptOrNull(date));
                            map2.put("desc",encryption.encryptOrNull(stleader));
                            map2.put("lat",encryption.encryptOrNull(lat));
                            map2.put("leader", encryption.encryptOrNull("Turtle Spotter "+ts));
                            map2.put("link",picdownurl);
                            map2.put("lon", encryption.encryptOrNull(lon));
                            map2.put("title",nesttitle.getText().toString());

                            message_root.updateChildren(map2);


                            root2 = FirebaseDatabase.getInstance().getReference().child("TUR");
                            Map<String, Object> map3 = new HashMap<String, Object>();
                            temp_key1 = root2.push().getKey();
                            root2.updateChildren(map3);
                            DatabaseReference message_root2 = root2.child(temp_key1);
                            Map<String, Object> map4 = new HashMap<String, Object>();
                            map4.put("anestid",ts);
                            map4.put("desc",stleader);
                            map4.put("date", date);
                            map4.put("title",nesttitle.getText().toString());
                            map4.put("url",picdownurl);
                            message_root2.updateChildren(map4);
                            break;
                        }

                        case "dead turtle":
                        {
                            chtext.setText("Enter a description");
                            picdownurl = firebaseupload.downurl;

                            stleader = leader.getText().toString();

                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(ts, "");
                            root.updateChildren(map);
                            root = FirebaseDatabase.getInstance().getReference().child(ts);
                            Map<String, Object> map1 = new HashMap<String, Object>();
                            temp_key = root.push().getKey();
                            root.updateChildren(map1);
                            DatabaseReference message_root = root.child(temp_key);
                            Map<String, Object> map2 = new HashMap<String, Object>();
                            map2.put("anestid",encryption.encryptOrNull(ts));
                            map2.put("category",encryption.encryptOrNull(stcategory));
                            map2.put("date",encryption.encryptOrNull(date));
                            map2.put("desc",encryption.encryptOrNull(stleader));
                            map2.put("lat", encryption.encryptOrNull(lat));
                            map2.put("leader", encryption.encryptOrNull("Turtle Spotter "+ts));
                            map2.put("link",picdownurl);
                            map2.put("lon", encryption.encryptOrNull(lon));
                            map2.put("title",encryption.encryptOrNull(nesttitle.getText().toString()));

                            message_root.updateChildren(map2);


                            root2 = FirebaseDatabase.getInstance().getReference().child("TUR");
                            Map<String, Object> map3 = new HashMap<String, Object>();
                            temp_key1 = root2.push().getKey();
                            root2.updateChildren(map3);
                            DatabaseReference message_root2 = root2.child(temp_key1);
                            Map<String, Object> map4 = new HashMap<String, Object>();
                            map4.put("anestid",ts);
                            map4.put("desc",stleader);
                            map4.put("date",date);
                            map4.put("title",nesttitle.getText().toString());
                            map4.put("url",picdownurl);
                            message_root2.updateChildren(map4);


                            break;
                        }
                    }
                    Toast.makeText(getContext(),"Success!",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getActivity(),"Please Login to continue",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case PERMISSION_REQUEST_CODE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

getlocation();
                } else {

                    Toast.makeText(getContext(),"Permission Denied, You cannot access location data.",Toast.LENGTH_LONG).show();

                }
                break;

        }
    }

    String dat,titl,nid;
    private void append_chat_conversation(DataSnapshot dataSnapshot,View view) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {

            nid=(String) ((DataSnapshot) i.next()).getValue();
            dat = (String) ((DataSnapshot) i.next()).getValue();
            titl = (String) ((DataSnapshot) i.next()).getValue();

        }
    }
    public void requestPermission(String strPermission,int perCode,Context _c){

        if (shouldShowRequestPermissionRationale(strPermission)){
            Toast.makeText(getContext(),"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();
        } else {

            requestPermissions(new String[]{strPermission},perCode);
        }
    }
    public static boolean checkPermission(String strPermission, Context _c){
        int result = ContextCompat.checkSelfPermission(_c, strPermission);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }
    private void getlocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.INTERNET}, 10);
            }

            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
    }
}
