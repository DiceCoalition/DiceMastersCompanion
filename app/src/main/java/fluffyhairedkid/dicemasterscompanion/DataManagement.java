package fluffyhairedkid.dicemasterscompanion;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class DataManagement extends Activity {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    SQLDataSource datasource;
    private final Activity dmActivity = this;
    private static final String TAG = MainActivity.class.getSimpleName();

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

        Button downloadManager = (Button) findViewById(R.id.btDownloadManager);
        Button dataDownload = (Button) findViewById(R.id.btDownload);
        Button dataClearDownload = (Button) findViewById(R.id.btClearDownload);

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

        downloadManager.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(DataManagement.this, DownloadManager.class));

            }
        });

        dataDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConnectivityManager cm =
                        (ConnectivityManager)dmActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(isConnected) {
                    boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
                    if(!isWiFi) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(dmActivity);
                        builder.setTitle("WiFi Recommended");
                        builder.setMessage("You are about to download a large number of image files. A WiFi connection is recommended. Do you wish to continue?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                downloadImageFiles();
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Download aborted.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.show();
                    }
                    else{
                        downloadImageFiles();
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(dmActivity);
                    builder.setTitle("Connection required.");
                    builder.setMessage("No internet connection detected. Please check your connection settings and/or connect to WiFi and try again.");

                    // add a button
                    builder.setPositiveButton("OK", null);

                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        dataClearDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(dmActivity);
                builder.setTitle("Clear downloaded files?");
                builder.setMessage("This will clear all previously downloaded card images.  Continue?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //loop through jpg files in directory and delete
                        File[] files = dmActivity.getFilesDir().listFiles(new FilenameFilter() {
                            public boolean accept(File dir, String name) {
                                return name.toLowerCase().endsWith(".jpg");
                            }
                        });
                        for(File f : files){
                            f.delete();
                        }
                        Toast.makeText(getApplicationContext(), "All downloaded cards removed.", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Delete aborted.", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void downloadImageFiles(){
        //String baseurl = "http://www.dicecoalition.com/cardservice/tzapp/";
        String baseurl = "http://www.dicecoalition.com/cardservice/Img.php?set=";
        Map<String, String> offlineCards = new HashMap<String, String>();
        offlineCards.put("ig", "152");
        offlineCards.put("wwe", "58");
        offlineCards.put("bit", "24");
        offlineCards.put("tag", "24");
        offlineCards.put("tiw", "59");
        offlineCards.put("aiw", "24");
        offlineCards.put("zhn", "24");
        offlineCards.put("xmf", "72");
        offlineCards.put("dxm", "24");
        offlineCards.put("xfo", "24");
        offlineCards.put("m2019_", "8");
        try {
            ArrayList<String> files = new ArrayList<String>();
            ArrayList<ArrayList<String>> query = new ArrayList<ArrayList<String>>();
            for(Map.Entry<String, String> entry : offlineCards.entrySet()){
                String key = entry.getKey();
                int cardcount = parseInt(entry.getValue());
                for(int i = 1; i <= cardcount; i++) {
                    String cardName = key+i;
                    String filename = cardName + ".jpg";

                    //String path = baseFileLoc + filename;
                    File f = new File(dmActivity.getFilesDir(), filename);
                    if (!f.exists()){
                        //String dl_url = baseurl + filename;
                        //files.add(dl_url);
                        String url2 = baseurl + key+"&cardnum="+i+"&res=l";
                        files.add(url2);
                    }
                }
            }
            DataManagement.FileDownload f = new DataManagement.FileDownload();
            f.FileList = files;
            f.execute();

        }catch(Exception ex) {

        }
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
/*
    public static void verifyInternetPermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.INTERNET);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    Manifest.permission.INTERNET,
                    1
            );
        }
    }
    */
    public class FileDownload extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        public ArrayList<String> FileList;
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(DataManagement.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            String message = "All cards downloaded";
            int count;
            int fileCount = FileList.size();
            for(int f = 0; f < fileCount; f ++) {
                String urlString = FileList.get(f);
                try {

                    //DataManagement.verifyStoragePermissions(dmActivity);

                    URL url = new URL(urlString);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    // getting file length
                    int lengthOfFile = connection.getContentLength();

                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(url.openStream());

                    //Extract file name from URL
                    fileName = urlString.substring(urlString.lastIndexOf('/') + 1, urlString.length());

                    File outputFile = new File(dmActivity.getFilesDir(), fileName);
                    if (!outputFile.exists())
                        outputFile.createNewFile();
                    // Output stream to write file
                    OutputStream output = openFileOutput(fileName, dmActivity.MODE_PRIVATE);

                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // publishing the progress....
                        // After this onProgressUpdate will be called
                        // publishProgress("" + (int) ((total * 100) / lengthOfFile));
                        //Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                        // writing data to file
                        output.write(data, 0, count);
                    }
                    publishProgress("" + (int) (((double)f/ (double)fileCount)*100));
                    Log.d(TAG, "Progress: " + (int) (((double)f / (double)fileCount)*100));

                    // flushing output
                    output.flush();

                    // closing streams
                    output.close();
                    input.close();
                    //return "Downloaded to: "+ outputFile.getAbsolutePath();//outputFilePath;

                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                    //Download failed, so delete file
                    File failedFile = new File(dmActivity.getFilesDir(), fileName);
                    if (failedFile.exists())
                        failedFile.delete();
                    message = "Failed to download all cards.";
                }

            }
            //return "Something went wrong";
            return message;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            Toast.makeText(getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();

        }
    }
}
