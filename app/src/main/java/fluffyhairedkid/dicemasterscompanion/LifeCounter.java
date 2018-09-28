package fluffyhairedkid.dicemasterscompanion;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.WindowManager;

public class LifeCounter extends Activity {

    int mhInt = 20;
    int ohInt = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_counter);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        final TextView oppHealth = (TextView) findViewById(R.id.tvOppHealth);
        final TextView myHealth = (TextView) findViewById(R.id.tvMyHealth);
        Button meOneDamage = (Button) findViewById(R.id.btMeOneDamage);
        Button meFiveDamage = (Button) findViewById(R.id.btMeFiveDamage);
        Button meOneHeal = (Button) findViewById(R.id.btMeOneHeal);
        Button meTwoHeal = (Button) findViewById(R.id.btMeTwoHeal);
        Button oppOneDamage = (Button) findViewById(R.id.btOppOneDamage);
        Button oppFiveDamage = (Button) findViewById(R.id.btOppFiveDamage);
        Button oppOneHeal = (Button) findViewById(R.id.btOppOneHeal);
        Button oppTwoHeal = (Button) findViewById(R.id.btOppTwoHeal);
        Button reset = (Button) findViewById(R.id.btReset);

        meOneDamage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(myHealth.getText().toString());
                myHealth.setText(String.valueOf(tempInt - 1));
                mhInt = tempInt - 1;

            }

        });

        meFiveDamage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(myHealth.getText().toString());
                myHealth.setText(String.valueOf(tempInt - 5));
                mhInt = tempInt - 5;

            }

        });

        meOneHeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(myHealth.getText().toString());
                myHealth.setText(String.valueOf(tempInt + 1));
                mhInt = tempInt + 1;

            }

        });

        meTwoHeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(myHealth.getText().toString());
                myHealth.setText(String.valueOf(tempInt + 2));
                mhInt = tempInt + 2;

            }

        });

        oppOneDamage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(oppHealth.getText().toString());
                oppHealth.setText(String.valueOf(tempInt - 1));
                ohInt = tempInt - 1;

            }

        });

        oppFiveDamage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(oppHealth.getText().toString());
                oppHealth.setText(String.valueOf(tempInt - 5));
                mhInt = tempInt - 5;

            }

        });

        oppOneHeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(oppHealth.getText().toString());
                oppHealth.setText(String.valueOf(tempInt + 1));
                mhInt = tempInt + 1;

            }

        });

        oppTwoHeal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                int tempInt = Integer.valueOf(oppHealth.getText().toString());
                oppHealth.setText(String.valueOf(tempInt + 2));
                mhInt = tempInt + 2;

            }

        });

        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mhInt = 20;
                ohInt = 20;
                myHealth.setText("20");
                oppHealth.setText("20");

                // Toast.makeText(LifeCounter.this, "Life totals reset",
                // Toast.LENGTH_SHORT).show();

            }

        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder closeAlert = new AlertDialog.Builder(
                LifeCounter.this);
        closeAlert
                .setMessage("Warning: Leaving this screen will reset life totals!");
        closeAlert.setCancelable(true);
        closeAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        closeAlert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = closeAlert.create();

        if (mhInt != 20 || ohInt != 20) {
            alert.show();
        } else {
            super.onBackPressed();
        }

    }

}
