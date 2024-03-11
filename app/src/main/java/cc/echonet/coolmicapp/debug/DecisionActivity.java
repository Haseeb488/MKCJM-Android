package cc.echonet.coolmicapp.debug;

import static android.graphics.Color.parseColor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;


public class DecisionActivity extends AppCompatActivity {

    CircularDotsLoader loader;
    AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_decision);
        getSupportActionBar().hide();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        manager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);


        loader = findViewById(R.id.loader);


        loader.setDefaultColor(ContextCompat.getColor(this, R.color.grey)); //yellow

        loader.setSelectedColor(ContextCompat.getColor(this, R.color.grey)); //yellow
        loader.setBigCircleRadius(68);
        loader.setRadius(20);
        loader.setAnimDur(100);
        loader.setShowRunningShadow(true);
        loader.setFirstShadowColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)); //lightBrown
        loader.setSecondShadowColor(ContextCompat.getColor(this, R.color.grey)); //yellow


        if(manager.isMusicActive())
        {

            loader.setVisibility(View.GONE);
            Toast.makeText(DecisionActivity.this, "Please stop app background music", Toast.LENGTH_SHORT).show();
            showMusicAlert();
            return;


        }
        else
        {
            Intent it = new Intent(DecisionActivity.this, WebActivity.class);
            startActivity(it);
            loader.setVisibility(View.GONE);
        }


    }
    public void showMusicAlert() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DecisionActivity.this, R.style.AlertDialogStyle);

                builder.setTitle(Html.fromHtml("<font color='#FFFFFF'>Background Music Found</font>"))
                .setMessage(Html.fromHtml("<font color='#FFFFFF'>Please Stop background music and try again</font>")
                )

                .setPositiveButton(Html.fromHtml("<font color='#FFFFFF'>try again</font>")
                        , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(manager.isMusicActive())
                        {
                            Toast.makeText(DecisionActivity.this, "Please Stop background music and try again", Toast.LENGTH_SHORT).show();
                            showMusicAlert();
                        }
                        else
                        {
                            Intent it = new Intent(DecisionActivity.this, WebActivity.class);
                            startActivity(it);
                            loader.setVisibility(View.GONE);
                        }
                    }
                })
                .setNegativeButton(Html.fromHtml("<font color='#FFFFFF'>Exit</font>")
                        , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finishAffinity();
                        System.exit(0);
                    }
                });
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.show();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#1174d1")));
    }


}