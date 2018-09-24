package rexzen.turtify;


/**
 * Created by harishananth on 25/12/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by harishananth on 20/12/16.
 */

public class abtView_Adapter extends RecyclerView.Adapter<abtView_Holder> {

    List<abtdata> list = Collections.emptyList();
    Context context;

    public abtView_Adapter(List<abtdata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public abtView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.abtturtlecard, parent, false);
        abtView_Holder holder = new abtView_Holder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(abtView_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        //holder.description.setText(list.get(position).description)
        holder.desc.setText(list.get(position).desc);
        holder.plimg.setImageResource(list.get(position).imageId);

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, abtdata pldata) {
        list.add(position, pldata);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(abtdata pldata) {
        int position = list.indexOf(pldata);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
