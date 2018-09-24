package rexzen.turtify;

//Created by Harish Anantharaman

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Fragment_1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_a,null);

        List<data> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mpView_Adapter adapter = new mpView_Adapter(data, getActivity());
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
                            case 0:
                                Intent newint=new Intent(getActivity(),theapp.class);
                                startActivity(newint);
                                break;
                            case 1:
                                Intent newint1=new Intent(getActivity(),userins.class);
                                startActivity(newint1);
                                break;
                            case 2:
                                Intent newint2=new Intent(getActivity(),turtlewalk.class);
                                startActivity(newint2);
                                break;
                            case 3:
                                Intent newint5=new Intent(getActivity(),nesting.class);
                                startActivity(newint5);

                                break;
                            case 4:
                                Intent newint7=new Intent(getActivity(),nest.class);
                                startActivity(newint7);

                                break;
                            case 5:
                                Intent newint6=new Intent(getActivity(),nest.class);
                                startActivity(newint6);

                                break;
                            case 6:

                                break;
                            case 7:

                                                               break;
                            case 8:

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
        return view;

    }
    public List<data> fill_with_data() {

        List<data> data = new ArrayList<>();

        data.add(new data("About Olive Ridley", "The second smallest after the Kemp’s ridley, the olive ridley turtles weigh between 75-100 pounds (34 - 45 kg) and reach 2-2 ½ feet (roughly .6 m) in length. They are named for their pale green carapace, or shell and are the most abundant of sea turtle species"));
        data.add(new data("User Instruction", "Details on how to use the Turtify app"));
        data.add(new data("Turtle walk", "Sea turtles are very old organisms. They live on the Earth more than 220 million years. They managed to survive weather changes which killed the dinosaurs"));
        data.add(new data("Nesting","These turtles nest in huge groups as many as 1,00,000 coming ashore day and night for several days in an event known as an arribada, Chennai coast is a minor nesting zone"));
        data.add(new data("Conservation Status", "The olive ridley is classified as vulnerable according to the International Union for Conservation of Nature and Natural Resources (IUCN), and is listed in Appendix I of CITES. These listings were largely responsible for halting the large scale commercial exploitation and trade of olive ridley skins."));

        return data;
    }


}