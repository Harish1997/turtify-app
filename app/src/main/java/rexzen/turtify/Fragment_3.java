package rexzen.turtify;
//Created by Harish Anantharaman

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Fragment_3 extends Fragment{

    Handler handler;
    String day,hour,minute,second;

    private DatabaseReference root;
    public RecyclerView recyclerView;
    private String temp_key;
    List<String> titleslist=new ArrayList<String>();
    List<String> dateslist=new ArrayList<String>();
    List<String> nestids=new ArrayList<>();
    LinkedHashSet<String> hs=new LinkedHashSet<>();
    LinkedHashSet<String> hs2=new LinkedHashSet<>();
    LinkedHashSet<String> hs3=new LinkedHashSet<>();
  int pos=0;
  HashMap<String,String> mapping=new HashMap<>();
int i=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_c,null);



        root = FirebaseDatabase.getInstance().getReference().child("TD");


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                add_nests(dataSnapshot,view);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                add_nests(dataSnapshot,view);
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
        return view;

    }
    public List<datanest> fill_with_data() {

        List<datanest> data = new ArrayList<>();

            hs.addAll(titleslist);
            titleslist.clear();
            titleslist.addAll(hs);

           // hs.clear();

           hs2.addAll(nestids);
            nestids.clear();
            nestids.addAll(hs2);

            //hs.clear();


       for(int i=0;i<titleslist.size();i++)
        {
            data.add(new datanest(nestids.get(i),titleslist.get(i), day, hour, minute, second,dateslist.get(i)));
           // data.add(new datanest(pair.getKey().toString(),titleslist.get(i),day,hour,minute,second,pair.getValue().toString()));
        }
           /* Iterator<String> itr1 = hs.iterator();
            Iterator<String> itr2 = hs2.iterator();
            Iterator<String> itr3 = hs3.iterator();
            while(itr1.hasNext() && itr2.hasNext() &&itr3.hasNext()){
                data.add(new datanest(itr1.next(),itr2.next(),day, hour, minute, second,itr3.next()));
            }*/

        return data;
    }
    String anest,dat,titl;
    private void add_nests(DataSnapshot dataSnapshot,View view)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {

            anest=(String)((DataSnapshot)i.next()).getValue();
            dat= (String) ((DataSnapshot)i.next()).getValue();
            titl= (String) ((DataSnapshot)i.next()).getValue();

                nestids.add(anest);
                titleslist.add(titl);
                dateslist.add(dat);
                mapping.put(anest,dat);
              /* hs.add(anest);
               hs2.add(titl);
               hs3.add(dat);*/
          pos++;


        }
        List<datanest> datanest = fill_with_data();
        nestView_Adapter adapter = new nestView_Adapter(datanest, getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.addOnItemTouchListener(
                new mpRecyclerItemClickListener(getActivity(), recyclerView ,new mpRecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position)
                        {

                            default:
                                Intent intent=new Intent(getActivity(),Recycleritem.class);
                                intent.putExtra("nestid",nestids.get(position));
                                intent.putExtra("title",titleslist.get(position));
                                intent.putExtra("date",dateslist.get(position));
                                startActivity(intent);
                                break;
                        }
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                //Toast.makeText(getActivity(),"item one long",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );

    }


}