package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Created by Walker on 2/21/2016.
 */
public class Tools extends Activity {

    SQLDataSource datasource;
    private final Activity dmActivity = this;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools);

        Button teamBuilder = (Button) findViewById(R.id.btTeamBuilder);
        Button lifeCounter = (Button) findViewById(R.id.btLifeCounter);
        Button bulkAdd = (Button) findViewById(R.id.btBulkAdd);
        Button dataManagement = (Button) findViewById(R.id.btDataManagement);
        Button dataDownload = (Button) findViewById(R.id.btDownload);


        teamBuilder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tools.this, TeamBuilder.class));
            }
        });

        lifeCounter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tools.this, LifeCounter.class));

            }
        });

        bulkAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Tools.this);
                dialog.setContentView(R.layout.mass_add);
                dialog.setTitle("Bulk Add");


                Button add = (Button) dialog.findViewById(R.id.btAddCards);
                Button cancel = (Button) dialog.findViewById(R.id.btCancelAdd);

				final ToggleButton xmfStarter = (ToggleButton) dialog.findViewById(R.id.tbXMFStarter);
                final ToggleButton xfoStarter = (ToggleButton) dialog.findViewById(R.id.tbXFOStarter);
                final ToggleButton dxmStarter = (ToggleButton) dialog.findViewById(R.id.tbDXMStarter);
                final ToggleButton jusStarter = (ToggleButton) dialog.findViewById(R.id.tbJUSStarter);
                final ToggleButton doomStarter = (ToggleButton) dialog.findViewById(R.id.tbDOOMStarter);
                final ToggleButton mystStarter = (ToggleButton) dialog.findViewById(R.id.tbMYSTStarter);
                final ToggleButton bfuStarter = (ToggleButton) dialog.findViewById(R.id.tbBFUStarter);
                final ToggleButton orkStarter = (ToggleButton) dialog.findViewById(R.id.tbORKStarter);
                final ToggleButton swStarter = (ToggleButton) dialog.findViewById(R.id.tbSWStarter);
                final ToggleButton aiStarter = (ToggleButton) dialog.findViewById(R.id.tbAIStarter);
                final ToggleButton kiStarter = (ToggleButton) dialog.findViewById(R.id.tbKIStarter);
                final ToggleButton jllStarter = (ToggleButton) dialog.findViewById(R.id.tbJLLStarter);
                final ToggleButton hqStarter = (ToggleButton) dialog.findViewById(R.id.tbHQStarter);
                final ToggleButton smmcStarter = (ToggleButton) dialog.findViewById(R.id.tbSMMCStarter);
                final ToggleButton swwStarter = (ToggleButton) dialog.findViewById(R.id.tbSWWStarter);
                final ToggleButton tdStarter = (ToggleButton) dialog.findViewById(R.id.tbTDStarter);
                final ToggleButton imwmStarter = (ToggleButton) dialog.findViewById(R.id.tbIMWMStarter);
                final ToggleButton hhsStarter = (ToggleButton) dialog.findViewById(R.id.tbHHSStarter);
                final ToggleButton dsStarter = (ToggleButton) dialog.findViewById(R.id.tbDSStarter);
                final ToggleButton tmntStarter = (ToggleButton) dialog.findViewById(R.id.tbTMNTStarter);
                final ToggleButton cwStarter = (ToggleButton) dialog.findViewById(R.id.tbCWStarter);
                final ToggleButton wfStarter = (ToggleButton) dialog.findViewById(R.id.tbWFStarter);
                final ToggleButton fusStarter = (ToggleButton) dialog.findViewById(R.id.tbFUSStarter);
                final ToggleButton asmStarter = (ToggleButton) dialog.findViewById(R.id.tbASMStarter);
                final ToggleButton wolStarter = (ToggleButton) dialog.findViewById(R.id.tbWoLStarter);
                final ToggleButton aouStarter = (ToggleButton) dialog.findViewById(R.id.tbAoUStarter);
                final ToggleButton jlStarter = (ToggleButton) dialog.findViewById(R.id.tbJLStarter);
                final ToggleButton bffStarter = (ToggleButton) dialog.findViewById(R.id.tbBFFStarter);
                final ToggleButton s1Starter = (ToggleButton) dialog.findViewById(R.id.tbS1Starter);
                final ToggleButton uxStarter = (ToggleButton) dialog.findViewById(R.id.tbUXStarter);
                final ToggleButton avxStarter = (ToggleButton) dialog.findViewById(R.id.tbAvXStarter);

                datasource = new SQLDataSource(Tools.this);

                cancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        String starterCriteria = "";
                        String fullCriteria = "";
                        
						 if (xmfStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'XMF'";
                            } else {
                                starterCriteria = starterCriteria + ",'XMF'";
                            }
                        }if (xfoStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'XFO'";
                            } else {
                                starterCriteria = starterCriteria + ",'XFO'";
                            }
                        }if (dxmStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'DXM'";
                            } else {
                                starterCriteria = starterCriteria + ",'DXM'";
                            }
                        }if (jusStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'JUS'";
                            } else {
                                starterCriteria = starterCriteria + ",'JUS'";
                            }
                        }if (doomStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'DOOM'";
                            } else {
                                starterCriteria = starterCriteria + ",'DOOM'";
                            }
                        }if (mystStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'MYST'";
                            } else {
                                starterCriteria = starterCriteria + ",'MYST'";
                            }
                        }if (bfuStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'BFU'";
                            } else {
                                starterCriteria = starterCriteria + ",'BFU'";
                            }
                        }if (orkStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'ORK'";
                            } else {
                                starterCriteria = starterCriteria + ",'ORK'";
                            }
                        }if (swStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'SW'";
                            } else {
                                starterCriteria = starterCriteria + ",'SW'";
                            }
                        }if (aiStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'AI'";
                            } else {
                                starterCriteria = starterCriteria + ",'AI'";
                            }
                        }if (kiStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'KI'";
                            } else {
                                starterCriteria = starterCriteria + ",'KI'";
                            }
                        }if (jllStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'JLL'";
                            } else {
                                starterCriteria = starterCriteria + ",'JLL'";
                            }
                        }if (hqStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'HQ'";
                            } else {
                                starterCriteria = starterCriteria + ",'HQ'";
                            }
                        }if (smmcStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'SMMC'";
                            } else {
                                starterCriteria = starterCriteria + ",'SMMC'";
                            }
                        }if (swwStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'SWW'";
                            } else {
                                starterCriteria = starterCriteria + ",'SWW'";
                            }
                        }
                        if (tdStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'TD'";
                            } else {
                                starterCriteria = starterCriteria + ",'TD'";
                            }
                        }
                        if (imwmStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'IMWM'";
                            } else {
                                starterCriteria = starterCriteria + ",'IMWM'";
                            }
                        }
                        if (hhsStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'HHS'";
                            } else {
                                starterCriteria = starterCriteria + ",'HHS'";
                            }
                        }
                        if (dsStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'DS'";
                            } else {
                                starterCriteria = starterCriteria + ",'DS'";
                            }
                        }
                        if (tmntStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'TMNT'";
                            } else {
                                starterCriteria = starterCriteria + ",'TMNT'";
                            }
                        }
                        if (cwStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'CW'";
                            } else {
                                starterCriteria = starterCriteria + ",'CW'";
                            }
                        }
                        if (wfStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'WF'";
                            } else {
                                starterCriteria = starterCriteria + ",'WF'";
                            }
                        }
                        if (fusStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'FUS'";
                            } else {
                                starterCriteria = starterCriteria + ",'FUS'";
                            }
                        }
                        if (asmStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'ASM'";
                            } else {
                                starterCriteria = starterCriteria + ",'ASM'";
                            }
                        }
                        if (wolStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'WoL'";
                            } else {
                                starterCriteria = starterCriteria + ",'WoL'";
                            }
                        }
                        if (aouStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'AoU'";
                            } else {
                                starterCriteria = starterCriteria + ",'AoU'";
                            }
                        }
                        if (jlStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'JL'";
                            } else {
                                starterCriteria = starterCriteria + ",'JL'";
                            }
                        }
                        if (bffStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'BFF'";
                            } else {
                                starterCriteria = starterCriteria + ",'BFF'";
                            }
                        }
                        if (s1Starter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'S1'";
                            } else {
                                starterCriteria = starterCriteria + ",'S1'";
                            }
                        }
                        if (uxStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'UX'";
                            } else {
                                starterCriteria = starterCriteria + ",'UX'";
                            }
                        }
                        if (avxStarter.isChecked()) {
                            if (starterCriteria.equals("")) {
                                starterCriteria = "'AVX'";
                            } else {
                                starterCriteria = starterCriteria + ",'AVX'";
                            }
                        }

                        datasource.open();
                        datasource.sqlAddStarter(starterCriteria);
                        datasource.close();

                        dialog.dismiss();
                        Toast.makeText(Tools.this, "Cards added!",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                dialog.show();
            }
        });

        dataManagement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tools.this, DataManagement.class));

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
                boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
                if(isConnected) {
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

    }

    private void downloadImageFiles(){
        String baseurl = "http://www.dicecoalition.com/cardservice/tzapp/";
        Map<String, String> offlineCards = new HashMap<String, String>();
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
                        String dl_url = baseurl + filename;
                        files.add(dl_url);
                    }
                }
            }
            FileDownload f = new FileDownload();
            f.FileList = files;
            f.execute();

        }catch(Exception ex) {

        }
    }
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
            this.progressDialog = new ProgressDialog(Tools.this);
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
