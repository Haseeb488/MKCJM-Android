package cc.echonet.coolmicapp.debug;

import static android.graphics.Color.parseColor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuranActivity extends AppCompatActivity {

    BottomNavigationView bottomBar;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quran);

        bottomBar = findViewById(R.id.bottom_navigationBar);
        getSupportActionBar().setTitle("Quran Kareem Application");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(parseColor("#00101F")));
        bottomBar.setSelectedItemId(R.id.nav_quran);

        checkAppStatus();



        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nave_hadith:

                        Intent nt = new Intent(QuranActivity.this, HadithActivity.class);
                        startActivity(nt);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.nav_LiveStreaming:
                        Intent zt = new Intent(QuranActivity.this, WebActivity.class);
                        startActivity(zt);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.nav_prayerTiming:

                        Intent it = new Intent(QuranActivity.this, PrayerActivity.class);
                        startActivity(it);
                        overridePendingTransition(0, 0);
                        finish();
                        break;

                    case R.id.nav_events:

                        Intent mt = new Intent(QuranActivity.this, EventActivity.class);
                        startActivity(mt);
                        overridePendingTransition(0, 0);
                        finish();
                        break;
                }
                return true;
            }
        });
    }



    public  void checkAppStatus()
    {
        boolean installed = appInstalledOrNot("com.kahr.qurankareem");
        if (installed) {
            Intent i = new Intent(Intent.ACTION_MAIN);
            PackageManager managerclock = getPackageManager();
            i = managerclock.getLaunchIntentForPackage("com.kahr.qurankareem");
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);
        }
        else
        {
            downloadAppAlert();

        }
    }
    public void downloadAppAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(QuranActivity.this, R.style.AlertDialogStyle);

        builder.setTitle(Html.fromHtml("<font color='#FFFFFF'> Download Quran Kareem App</font>"))
                .setMessage(Html.fromHtml("<font color='#FFFFFF'> Unlock the wisdom of the Quran with our divine app by KAHR Designs." +
                        " Embrace the journey of faith, download now, and open the gateway to enlightenment directly from your device.</font>"
                ))

                .setPositiveButton(Html.fromHtml("<font color='#FFFFFF'>Download App</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                         try {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://play.google.com/store/apps/details?id=com.kahr.qurankareem"));
                startActivity(viewIntent);
            }catch(Exception e) {
                Toast.makeText(getApplicationContext(),"Unable to Connect Try Again...",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
                    }
                })
                .setNegativeButton(Html.fromHtml("<font color='#FFFFFF'>Cancel</font>"), null);
        AlertDialog alert = builder.create();
        alert.show();
        alert.setCancelable(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(parseColor("#1174d1")));
    }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }



}