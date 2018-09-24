package rexzen.turtify;

/**
 * Created by harishananth on 23/02/17.
 */
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by harishananth on 20/12/16.
 */

public class mpView_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    mpView_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.titlemc);
        description = (TextView) itemView.findViewById(R.id.description);
    }
}
