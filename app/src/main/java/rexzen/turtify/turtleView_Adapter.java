package rexzen.turtify;

/**
 * Created by harishananth on 28/02/17.
 */

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by harishananth on 20/12/16.
 */

public class turtleView_Adapter extends RecyclerView.Adapter<turtleView_Holder> {

    HashMap<String,String> months=new HashMap<>();
    HashMap<String,String> days=new HashMap<>();

    List<turdata> list = Collections.emptyList();
    Context context;
    int x;
    private Handler handler;
    private Runnable runnable;

    public turtleView_Adapter(List<turdata> list, Context context) {
        this.list = list;
        this.context = context;
        months.put("1","January");months.put("2","February");months.put("3","March"); months.put("4","April"); months.put("5","May"); months.put("6","June"); months.put("7","July"); months.put("8","August"); months.put("9","September");months.put("10","October"); months.put("11","November"); months.put("12","December");
        days.put("1","st"); days.put("2","nd"); days.put("3","rd"); days.put("4","th"); days.put("5","th"); days.put("6","th"); days.put("7","th"); days.put("8","th"); days.put("9","th"); days.put("10","th"); days.put("11","th"); days.put("12","th"); days.put("13","th"); days.put("14","th"); days.put("15","th"); days.put("16","th"); days.put("17","th"); days.put("18","th"); days.put("19","th"); days.put("20","th"); days.put("21","st"); days.put("22","nd"); days.put("23","rd"); days.put("24","th"); days.put("25","th"); days.put("26","th"); days.put("27","th"); days.put("28","th"); days.put("29","th"); days.put("30","th"); days.put("31","st");

    }

    @Override
    public turtleView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.turtlecard, parent, false);
        final turtleView_Holder holder = new turtleView_Holder(v);


        return holder;

    }

    @Override
    public void onBindViewHolder(final turtleView_Holder holder, final int position) {

        String arr[]=list.get(position).date.split("-");

        holder.turid.setText("Report id: "+list.get(position).id);
        holder.turdesc.setText(list.get(position).desc);
        holder.turdate.setText("Dated on: "+arr[2]+days.get(arr[2])+" "+months.get(arr[1])+", "+arr[0]);
        holder.turTitle.setText(list.get(position).title);
        Glide.with(context).load(list.get(position).url).into(holder.img);


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
    public void insert(int position, turdata data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
