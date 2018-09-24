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

public class nestView_Adapter extends RecyclerView.Adapter<nestView_Holder> {

    HashMap<String,String> months=new HashMap<>();
    HashMap<String,String> days=new HashMap<>();
    List<datanest> list = Collections.emptyList();
    Context context;
    int x;
    private Handler handler;
     private Runnable runnable;
    Calendar calendar;
    Long turtletime=4752000000L;

    public nestView_Adapter(List<datanest> list, Context context) {
        this.list = list;
        this.context = context;
        months.put("1","January");months.put("2","February");months.put("3","March"); months.put("4","April"); months.put("5","May"); months.put("6","June"); months.put("7","July"); months.put("8","August"); months.put("9","September");months.put("10","October"); months.put("11","November"); months.put("12","December");
      days.put("1","st"); days.put("2","nd"); days.put("3","rd"); days.put("4","th"); days.put("5","th"); days.put("6","th"); days.put("7","th"); days.put("8","th"); days.put("9","th"); days.put("10","th"); days.put("11","th"); days.put("12","th"); days.put("13","th"); days.put("14","th"); days.put("15","th"); days.put("16","th"); days.put("17","th"); days.put("18","th"); days.put("19","th"); days.put("20","th"); days.put("21","st"); days.put("22","nd"); days.put("23","rd"); days.put("24","th"); days.put("25","th"); days.put("26","th"); days.put("27","th"); days.put("28","th"); days.put("29","th"); days.put("30","th"); days.put("31","st");

    }

    @Override
    public nestView_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nestcard, parent, false);
        final nestView_Holder holder = new nestView_Holder(v);


        return holder;

    }

    @Override
    public void onBindViewHolder(final nestView_Holder holder, final int position) {


        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        Date futuredate = null;
        try {
            futuredate= dateFormat.parse(list.get(position).date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date today = new Date();
        final long currentTime = today.getTime();
        long expiryTime = futuredate.getTime() - currentTime+turtletime;
        if (holder.timer != null) {
            holder.timer.cancel();
        }
        holder.timer = new CountDownTimer(expiryTime, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long second=seconds%60;
                long minute=minutes%60;
                long hr=hours%24;

                long days = hours / 24;
                String time = days+" "+"days" +" :" +hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
                holder.day.setText("" + String.format("%02d", days));
                holder.hour.setText("" + String.format("%02d", hr));
                holder.minute.setText(""
                        + String.format("%02d", minute));
                holder.second.setText(""
                        + String.format("%02d", second));
            }

            public void onFinish() {

            }
        }.start();

        String arr[]=list.get(position).date.split("-");
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.nestid.setText("Nest id: "+list.get(position).anestid);
        holder.date.setText("Nest reported on: "+arr[2]+days.get(arr[2])+" "+months.get(arr[1])+", "+arr[0]);
        holder.Title.setText(list.get(position).Title);


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
    public void insert(int position, datanest data) {
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
