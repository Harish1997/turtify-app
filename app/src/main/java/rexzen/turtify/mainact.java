package rexzen.turtify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifTextView;

/**
 * Created by harishananth on 01/02/17.
 */

public class mainact extends AppCompatActivity {
    private DatabaseReference root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainact);
        TextView tx = (TextView)findViewById(R.id.textView7);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Pacifico.ttf");

        tx.setTypeface(custom_font);
        SharedPreferences prefs = getSharedPreferences("login result", MODE_PRIVATE);
        String result = prefs.getString("result", "no");

        if (result.equals("yes")) {
            int st = prefs.getInt("st_value", 0);
            login.st=1;
            Intent intent=new Intent(mainact.this,MainActivity.class);
            intent.putExtra("status", "success");
            startActivity(intent);
        }
        else {
            GifTextView imageView;
            imageView = (GifTextView) findViewById(R.id.gif);
            TranslateAnimation animation = new TranslateAnimation(-100.0f, -1100.0f,
                    0.0f, 0.0f);
            animation.setDuration(3000);
            animation.setRepeatCount(7);
            animation.setRepeatMode(2);
            animation.setFillAfter(true);
            imageView.startAnimation(animation);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mainact.this, login.class);
                    startActivity(intent);
                }
            }, 2000);

        }

    }
}
