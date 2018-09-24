package rexzen.turtify;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by harishananth on 10/06/17.
 */

public class Fragment_4 extends android.support.v4.app.Fragment {

    private DatabaseReference root;
    public RecyclerView recyclerView;
    private String temp_key;
    List<String> titleslist=new ArrayList<String>();
    List<String> dateslist=new ArrayList<String>();
    List<String> desclist=new ArrayList<String>();
    List<String> urllist=new ArrayList<String>();
    List<String> turids=new ArrayList<>();

    LinkedHashSet<String> hs=new LinkedHashSet<>();




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_d,null);

        root = FirebaseDatabase.getInstance().getReference().child("TUR");


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                add_turtles(dataSnapshot,view);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                add_turtles(dataSnapshot,view);
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

    public List<turdata> fill_with_data() {

        List<turdata> data = new ArrayList<>();

        hs.addAll(titleslist);
        titleslist.clear();
        titleslist.addAll(hs);
        for(int i=0;i<titleslist.size();i++)
        {
            //if(!titleslist.contains(i))
            data.add(new turdata(turids.get(i),titleslist.get(i),desclist.get(i),urllist.get(i),dateslist.get(i)));
        }
        return data;
    }
    String id,date,titl,desc,url;
    private void add_turtles(DataSnapshot dataSnapshot,View view) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {

            id = (String) ((DataSnapshot) i.next()).getValue();
            date = (String) ((DataSnapshot) i.next()).getValue();
            desc = (String) ((DataSnapshot) i.next()).getValue();
            titl = (String) ((DataSnapshot) i.next()).getValue();
            url = (String) ((DataSnapshot) i.next()).getValue();

                turids.add(id);
                titleslist.add(titl);
                dateslist.add(date);
                desclist.add(desc);
                urllist.add(url);




        }
        List<turdata> data = fill_with_data();
        turtleView_Adapter adapter = new turtleView_Adapter(data, getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);
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
                                Intent intent=new Intent(getActivity(),turtleitem.class);
                                intent.putExtra("turid",turids.get(position));
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
