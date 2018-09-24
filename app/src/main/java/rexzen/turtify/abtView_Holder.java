package rexzen.turtify;

//Created by Harish Anantharaman

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class abtView_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title, desc;
    ImageView plimg;

    abtView_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.abtcard);
        title = (TextView) itemView.findViewById(R.id.title);
        desc = (TextView) itemView.findViewById(R.id.desc);
        plimg = (ImageView) itemView.findViewById(R.id.abtimage);
    }


}