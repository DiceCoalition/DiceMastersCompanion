package fluffyhairedkid.dicemasterscompanion;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class DataManagement extends Activity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    SQLDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_management);

        Button exportData = (Button) findViewById(R.id.btExport);
        Button importData = (Button) findViewById(R.id.btImport);
        Button backupData = (Button) findViewById(R.id.btBackup);
        Button restoreData = (Button) findViewById(R.id.btRestore);
        //TODO: Add ability to export and import team lists
        Button exportTeams = (Button) findViewById(R.id.btTeamsExport);
        Button importTeams = (Button) findViewById(R.id.btTeamsImport);

        final Activity dmActivity = this;
        exportData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder exportAlert = new AlertDialog.Builder(
                        DataManagement.this);
                exportAlert
                        .setMessage("This will create a CSV file named 'Transition Zone' in your Downloads folder. Likely only editable on your phone, or on your computer if your phone is rooted. Adding cards that don't exist in the app to the file won't add them to the app, but nice try.");
                exportAlert.setCancelable(true);
                exportAlert.setPositiveButton("Export",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                final ProgressDialog progress = ProgressDialog.show(DataManagement.this, "",
                                        "Exporting. Please wait...", true);

                                Thread backgroundThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        verifyStoragePermissions(dmActivity);
                                        datasource = new SQLDataSource(DataManagement.this);

                                        datasource.open();
                                        datasource.sqlExportData(DataManagement.this);
                                        datasource.close();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress.dismiss();
                                            }
                                        });
                                    }
                                });

                                backgroundThread.start();

                            }
                        });
                exportAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = exportAlert.create();

                alert.show();


            }
        });
        importData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder importAlert = new AlertDialog.Builder(
                        DataManagement.this);
                importAlert
                        .setMessage("Importing a file if you did something crazy to it could mess up your collection. If this happens, try restoring the back up version of the database. But also don't try anything crazy.");
                importAlert.setCancelable(true);
                importAlert.setPositiveButton("Import",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                File file = new File(Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS), "Transition Zone.csv");

                                if (file == null || !file.exists()) {
                                    AlertDialog.Builder noFile = new AlertDialog.Builder(
                                            DataManagement.this);
                                    noFile
                                            .setMessage("File does not exist!");
                                    noFile.setCancelable(true);
                                    noFile.setNeutralButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    noFile.show();
                                } else {


                                    final ProgressDialog progress = ProgressDialog.show(DataManagement.this, "",
                                            "Importing. Please wait...", true);

                                    Thread backgroundThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            verifyStoragePermissions(dmActivity);
                                            datasource = new SQLDataSource(DataManagement.this);

                                            datasource.open();
                                            datasource.sqlImportData(DataManagement.this);
                                            datasource.close();

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progress.dismiss();
                                                }
                                            });
                                        }
                                    });

                                    backgroundThread.start();
                                }
                            }
                        });
                importAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = importAlert.create();

                alert.show();


            }
        });
        backupData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder backupAlert = new AlertDialog.Builder(
                        DataManagement.this);
                backupAlert
                        .setMessage("A backup was automatically created the last time the app was updated. This action will replace the previous backup. Do not do this if your collection has been messed up!");
                backupAlert.setCancelable(true);
                backupAlert.setPositiveButton("Back Up",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                final ProgressDialog progress = ProgressDialog.show(DataManagement.this, "",
                                        "Backing up. Please wait...", true);

                                Thread backgroundThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        verifyStoragePermissions(dmActivity);
                                        datasource = new SQLDataSource(DataManagement.this);

                                        datasource.open();
                                        datasource.sqlBackup();
                                        datasource.close();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress.dismiss();
                                            }
                                        });
                                    }
                                });

                                backgroundThread.start();

                            }
                        });
                backupAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = backupAlert.create();

                alert.show();


            }
        });
        restoreData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder restoreAlert = new AlertDialog.Builder(
                        DataManagement.this);
                restoreAlert
                        .setMessage("This action cannot be undone, your current collection will be replaced with whatever was in it the last time the backup was run!");
                restoreAlert.setCancelable(true);
                restoreAlert.setPositiveButton("Restore",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                final ProgressDialog progress = ProgressDialog.show(DataManagement.this, "",
                                        "Restoring. Please wait...", true);

                                Thread backgroundThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        verifyStoragePermissions(dmActivity);
                                        datasource = new SQLDataSource(DataManagement.this);

                                        datasource.open();
                                        datasource.sqlRestore();
                                        datasource.close();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress.dismiss();
                                            }
                                        });
                                    }
                                });

                                backgroundThread.start();

                            }
                        });
                restoreAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = restoreAlert.create();

                alert.show();


            }
        });
        exportTeams.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder exportAlert = new AlertDialog.Builder(
                        DataManagement.this);
                exportAlert
                        .setMessage("This will create a CSV file named 'Transition Zone Teams' in your Downloads folder. Likely only editable on your phone, or on your computer if your phone is rooted. Adding cards that don't exist in the app to the file won't add them to the app, but nice try.");
                exportAlert.setCancelable(true);
                exportAlert.setPositiveButton("Export",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                final ProgressDialog progress = ProgressDialog.show(DataManagement.this, "",
                                        "Exporting. Please wait...", true);

                                Thread backgroundThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        verifyStoragePermissions(dmActivity);
                                        datasource = new SQLDataSource(DataManagement.this);

                                        datasource.open();
                                        datasource.sqlTeamsExport(DataManagement.this);
                                        datasource.close();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                progress.dismiss();
                                            }
                                        });
                                    }
                                });

                                backgroundThread.start();

                            }
                        });
                exportAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = exportAlert.create();

                alert.show();


            }
        });
        importTeams.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder importAlert = new AlertDialog.Builder(
                        DataManagement.this);
                importAlert
                        .setMessage("Importing a file if you did something crazy to it could mess up your collection. If this happens, try restoring the back up version of the database. But also don't try anything crazy.");
                importAlert.setCancelable(true);
                importAlert.setPositiveButton("Import",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                                File file = new File(Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS), "Transition Zone Teams.csv");

                                if (file == null || !file.exists()) {
                                    AlertDialog.Builder noFile = new AlertDialog.Builder(
                                            DataManagement.this);
                                    noFile
                                            .setMessage("File does not exist!");
                                    noFile.setCancelable(true);
                                    noFile.setNeutralButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    noFile.show();
                                } else {


                                    final ProgressDialog progress = ProgressDialog.show(DataManagement.this, "",
                                            "Importing. Please wait...", true);

                                    Thread backgroundThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            verifyStoragePermissions(dmActivity);
                                            datasource = new SQLDataSource(DataManagement.this);

                                            datasource.open();
                                            datasource.sqlTeamsImport(DataManagement.this);
                                            datasource.close();

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progress.dismiss();
                                                }
                                            });
                                        }
                                    });

                                    backgroundThread.start();
                                }
                            }
                        });
                importAlert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = importAlert.create();

                alert.show();


            }
        });
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
