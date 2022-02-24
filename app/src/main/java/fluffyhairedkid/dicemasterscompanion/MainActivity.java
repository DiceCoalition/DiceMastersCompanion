package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends Activity {

    public static ArrayList<Map<String, String>> masterList = new ArrayList<Map<String, String>>();
    public static int collapseSetting = 0;
    public static String selectedTeam = "";
    private static final int WRITE_REQUEST_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Update this every version!!!!!
        AlertDialog.Builder closeAlert = new AlertDialog.Builder(
                MainActivity.this);
        closeAlert.setTitle("Updates");
        closeAlert
                .setMessage("Added Dark Phoenix Saga cards"
                        // + "\n" + "\n" +
                        // "Added Foil Collection Stats"
                        // + "\n" + "\n" +
                        // "Long-press of Collection buttons now auto-increments by 1"
                );
        closeAlert.setCancelable(true);
        closeAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = closeAlert.create();

        SharedPreferences prefs = getSharedPreferences("MyPrefs", 0);
        PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            // Change to == to test alert message!
            if (prefs.getLong("lastRunVersionCode", 0) < pInfo.versionCode) {
                alert.show();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong("lastRunVersionCode", pInfo.versionCode);
                editor.apply();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("MainActivity", "Error reading versionCode");
            e.printStackTrace();
        }

        Button search = (Button) findViewById(R.id.btSearchCards);
        Button marvel = (Button) findViewById(R.id.btMarvel);
        Button dc = (Button) findViewById(R.id.btDC);
        Button dnd = (Button) findViewById(R.id.btDnD);
        Button w40k = (Button) findViewById(R.id.btW40K);
        Button wwe = (Button) findViewById(R.id.btWWE);
        Button other = (Button) findViewById(R.id.btOther);
        Button tools = (Button) findViewById(R.id.btTools);
        Button credits = (Button) findViewById(R.id.btCredits);
        //Button test = (Button) findViewById(R.id.btTest);

        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Search.class);
                intent.putExtra("view", "collection");
                startActivity(intent);
            }
        });

        marvel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Marvel.class));
            }
        });

        dc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DCComics.class));
            }
        });

        dnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, DnD.class));
            }
        });

        w40k.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, W40K.class));
            }
        });

        wwe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, WWE.class));
            }
        });

        other.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, OtherSets.class));
            }
        });


        tools.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Tools.class));
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Credits.class));
            }
        });

        /*test.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               startActivity(new Intent(MainActivity.this, TeamViewer.class));

            }
        });*/


    }
}
