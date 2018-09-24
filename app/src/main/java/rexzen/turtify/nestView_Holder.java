package rexzen.turtify;

import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by harishananth on 28/02/17.
 */

public class nestView_Holder extends RecyclerView.ViewHolder {

    CardView cd;
    TextView Title;
    TextView day;
    TextView hour;
    TextView minute;
    TextView second;
    TextView nestid;
    TextView date;

CountDownTimer timer;

    public nestView_Holder(View itemView) {
            super(itemView);
        cd=(CardView)itemView.findViewById(R.id.cardViewnest);
        nestid=(TextView)itemView.findViewById(R.id.nestid);
        date=(TextView)itemView.findViewById(R.id.datetxt);
        Title=(TextView)itemView.findViewById(R.id.nesttitle);
        day=(TextView) itemView.findViewById(R.id.txtTimerDay);
        hour=(TextView)itemView.findViewById(R.id.txtTimerHour);
        minute=(TextView)itemView.findViewById(R.id.txtTimerMinute);
        second=(TextView)itemView.findViewById(R.id.txtTimerSecond);

    }
}
