package fluffyhairedkid.dicemasterscompanion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import android.net.Uri;
import android.widget.ImageButton;

public class Credits extends Activity {

    SQLDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        ImageButton donate = (ImageButton) findViewById(R.id.ibDonate);
        //Button resetCollection = (Button) findViewById(R.id.btResetCollection);

        donate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder closeAlert = new AlertDialog.Builder(
                        Credits.this);
                closeAlert.setTitle("Warning");
                closeAlert
                        .setMessage("This action will open a web page in your browser outside the app.");
                closeAlert.setCancelable(true);
                closeAlert.setPositiveButton("Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Uri uri = Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=RKRV88LRUZ65G"); // missing 'http://' will cause crashed
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                closeAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = closeAlert.create();
                alert.show();
            }
        });

        ImageButton donate2 = (ImageButton) findViewById(R.id.ibDonate2);
        //Button resetCollection = (Button) findViewById(R.id.btResetCollection);

        donate2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder closeAlert = new AlertDialog.Builder(
                        Credits.this);
                closeAlert.setTitle("Warning");
                closeAlert
                        .setMessage("This action will open a web page in your browser outside the app.");
                closeAlert.setCancelable(true);
                closeAlert.setPositiveButton("Continue",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Uri uri = Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=J7Z27XLJG82KL"); // missing 'http://' will cause crashed
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                closeAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = closeAlert.create();
                alert.show();
            }
        });

/*
        resetCollection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(
                        Credits.this);
                deleteAlert
                        .setMessage("WARNING: This will reset all of your tracked cards and dice!");
                deleteAlert.setCancelable(true);
                deleteAlert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                datasource = new SQLDataSource(Credits.this);
                                datasource.open();
                                datasource.sqlResetCards();
                                datasource.close();
                            }
                        });
                deleteAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = deleteAlert.create();

                alert.show();
            }
        });
*/
    }


}
