package rexzen.turtify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by harishananth on 03/03/17.
 */


public class AlarmToastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm for nest has been set",Toast.LENGTH_LONG).show();
    }
}
