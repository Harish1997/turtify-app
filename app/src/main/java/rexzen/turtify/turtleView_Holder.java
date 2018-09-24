package rexzen.turtify;

import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by harishananth on 28/02/17.
 */

public class turtleView_Holder extends RecyclerView.ViewHolder {

    CardView cd;
    TextView turTitle,turid;
    TextView turdesc,turdate;
    ImageView img;

    public turtleView_Holder(View itemView) {
        super(itemView);
        cd=(CardView)itemView.findViewById(R.id.turtcardView);
        turid=(TextView)itemView.findViewById(R.id.turidtxt);
        turdate=(TextView)itemView.findViewById(R.id.turdate);
        turTitle=(TextView)itemView.findViewById(R.id.turtitle);
        turdesc=(TextView) itemView.findViewById(R.id.turdesc);
        img=(ImageView) itemView.findViewById(R.id.turtimg);


    }
}
