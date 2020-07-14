package fluffyhairedkid.dicemasterscompanion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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

public class DownloadManager extends Activity {
    SQLDataSource datasource;
    private final Activity dmActivity = this;
    private static final String TAG = MainActivity.class.getSimpleName();
    private final Map<String, String> offlineCards = new HashMap<String, String>();
    private final String[] offlinePrizeCards = new String[]
            {"dp23alt.jpg", "dp92alt.jpg","dp119alt.jpg","gatf48alt.jpg","gatf58alt.jpg","gotg72alt.jpg",
                    "xmfc8alt.jpg", "xmfc56alt.jpg", "xmfc63alt.jpg", "xmfc74alt.jpg", "xmfc76alt.jpg",
            "xmfc102alt.jpg", "xmfc110alt.jpg", "xmfc119alt.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //offlineCards.put("asm", "154");
        offlineCards.put("m2019_", "8");

        setContentView(R.layout.download_manager);
        //TODO: for some reason DL from here was producing blank images
        //Button dlCards = (Button) findViewById(R.id.btDLCards);
        Button deleteCards = (Button) findViewById(R.id.btDeleteCards);

        /*
       dlCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> sets = getSelectedSets();
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
                                downloadImageFiles(sets);
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
                        downloadImageFiles(sets);
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
                //TODO: this update needs to happen after the download completes.. need event handler or something
                updateButtonText();
                //Toast.makeText(getApplicationContext(), "All selected cards downloaded.", Toast.LENGTH_SHORT).show();

            }
        });
        */
        deleteCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dmActivity);
                builder.setTitle("Confirm Delete");
                builder.setMessage("You are about to delete image files from the system.  They will need to be re-downloaded to view them again. Do you wish to continue?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    ArrayList<String> sets = getSelectedSets();
                    for(String set : sets)
                        deleteCardsFromSet(set);
                    updateButtonText();
                    Toast.makeText(getApplicationContext(), "All selected cards removed.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Delete aborted.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        updateButtonText();
    }

    private ArrayList getSelectedSets(){
        ArrayList<String> sets = new ArrayList<String>();
        final ToggleButton igStarter = (ToggleButton) findViewById(R.id.tbDLIG);
        if(igStarter.isChecked())
            sets.add("IG");
        final ToggleButton wweStarter = (ToggleButton) findViewById(R.id.tbDLWWE);
        if(wweStarter.isChecked())
            sets.add("WWE");
        final ToggleButton bitStarter = (ToggleButton) findViewById(R.id.tbDLBIT);
        if(bitStarter.isChecked())
            sets.add("BIT");
        final ToggleButton tagStarter = (ToggleButton) findViewById(R.id.tbDLTAG);
        if(tagStarter.isChecked())
            sets.add("TAG");
        final ToggleButton tiwStarter = (ToggleButton) findViewById(R.id.tbDLTIW);
        if(tiwStarter.isChecked())
            sets.add("TIW");
        final ToggleButton aiwStarter = (ToggleButton) findViewById(R.id.tbDLAIW);
        if(aiwStarter.isChecked())
            sets.add("AIW");
        final ToggleButton zhnStarter = (ToggleButton) findViewById(R.id.tbDLZHN);
        if(zhnStarter.isChecked())
            sets.add("ZHN");
        final ToggleButton xmfStarter = (ToggleButton) findViewById(R.id.tbDLXMF);
        if(xmfStarter.isChecked())
            sets.add("XMF");
        final ToggleButton xfoStarter = (ToggleButton) findViewById(R.id.tbDLXFO);
        if(xfoStarter.isChecked())
            sets.add("XFO");
        final ToggleButton dxmStarter = (ToggleButton) findViewById(R.id.tbDLDXM);
        if(dxmStarter.isChecked())
            sets.add("DXM");
       // final ToggleButton asmStarter = (ToggleButton) findViewById(R.id.tbDLASM);
       // if(asmStarter.isChecked())
       //     sets.add("ASM");
       final ToggleButton dlOP = (ToggleButton) findViewById(R.id.tbDLOP);
       if(dlOP.isChecked())
            sets.add("m2019_");
       // final ToggleButton dlPrize = (ToggleButton) findViewById(R.id.tbDLPrize);
       // if(dlPrize.isChecked())
       //     sets.add("prize");
        return sets;
    }

    private void updateButtonText(){
        final ToggleButton igStarter = (ToggleButton) findViewById(R.id.tbDLIG);
        String text = "Infinity Gauntlet " + getOfflineCardCountForSet("IG")+ "/152";
        setToggleButtonText(igStarter, text);
        final ToggleButton wweStarter = (ToggleButton) findViewById(R.id.tbDLWWE);
        text = "WWE " + getOfflineCardCountForSet("WWE")+ "/58";
        setToggleButtonText(wweStarter, text);
        final ToggleButton bitStarter = (ToggleButton) findViewById(R.id.tbDLBIT);
        text = "Bitter Rivals " + getOfflineCardCountForSet("BIT")+ "/24";
        setToggleButtonText(bitStarter, text);
        final ToggleButton tagStarter = (ToggleButton) findViewById(R.id.tbDLTAG);
        text = "Tag Team " + getOfflineCardCountForSet("TAG")+ "/24";
        setToggleButtonText(tagStarter, text);
        final ToggleButton tiwStarter = (ToggleButton) findViewById(R.id.tbDLTIW);
        text = "Trouble in Waterdeep " + getOfflineCardCountForSet("TIW")+ "/59";
        setToggleButtonText(tiwStarter, text);
        final ToggleButton aiwStarter = (ToggleButton) findViewById(R.id.tbDLAIW);
        text = "Adventurers in Waterdeep " + getOfflineCardCountForSet("AIW")+ "/24";
        setToggleButtonText(aiwStarter, text);
        final ToggleButton zhnStarter = (ToggleButton) findViewById(R.id.tbDLZHN);
        text = "Zhentarim " + getOfflineCardCountForSet("ZHN")+ "/24";
        setToggleButtonText(zhnStarter, text);
        final ToggleButton xmfStarter = (ToggleButton) findViewById(R.id.tbDLXMF);
        text = "X-Men Forever " + getOfflineCardCountForSet("XMF")+ "/72";
        setToggleButtonText(xmfStarter, text);
        final ToggleButton xfoStarter = (ToggleButton) findViewById(R.id.tbDLXFO);
        text = "X-Force " + getOfflineCardCountForSet("XFO")+ "/24";
        setToggleButtonText(xfoStarter, text);
        final ToggleButton dxmStarter = (ToggleButton) findViewById(R.id.tbDLDXM);
        text = "Dark X-Men " + getOfflineCardCountForSet("DXM")+ "/24";
        setToggleButtonText(dxmStarter, text);
        //final ToggleButton asmStarter = (ToggleButton) findViewById(R.id.tbDLASM);
        //text = "Amazing Spider-Man " + getOfflineCardCountForSet("ASM")+ "/154";
        //setToggleButtonText(asmStarter, text);
        //TODO: removing MarvelOP because "_" in name is causing some problems.
        final ToggleButton dlOP = (ToggleButton) findViewById(R.id.tbDLOP);
        text = "Marvel OP 2019 " + getOfflineCardCountForSet("m2019_")+ "/8";
        setToggleButtonText(dlOP, text);
       // final ToggleButton dlPrize = (ToggleButton) findViewById(R.id.tbDLPrize);
       // text = "Prize Cards " + "?"+ "/14";
        //setToggleButtonText(dlPrize, text);
    }
    private void setToggleButtonText(ToggleButton button, String text){
        button.setTextOff(text);
        button.setTextOn(text);
        button.requestLayout();
        button.setChecked((button.isChecked()));
        button.setText(text);
    }

    private int getOfflineCardCountForSet(String set){
        final String setSearch = set;
        File[] files = dmActivity.getFilesDir().listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().startsWith(setSearch.toLowerCase());
            }
        });
        return files.length;
    }

    private int deleteCardsFromSet(String set){

        final String setSearch = set;
        File[] files = dmActivity.getFilesDir().listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().startsWith(setSearch.toLowerCase());
            }
        });
        for(File f : files){
            f.delete();
        }

        return files.length;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void downloadImageFiles(ArrayList<String> sets){
        //String baseurl = "http://www.dicecoalition.com/cardservice/tzapp/";
        String baseurl = "http://www.dicecoalition.com/cardservice/Img.php?set=";
        try {
            ArrayList<String> files = new ArrayList<String>();
            ArrayList<ArrayList<String>> query = new ArrayList<ArrayList<String>>();
            //for(Map.Entry<String, String> entry : offlineCards.entrySet()){
            for(String set : sets){

                String key = set.toLowerCase();
                int cardcount = parseInt(offlineCards.get(set.toLowerCase()));
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
            DownloadManager.FileDownload f = new DownloadManager.FileDownload();
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
            this.progressDialog = new ProgressDialog(DownloadManager.this);
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
            for (int f = 0; f < fileCount; f++) {
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
                    if(fileName.startsWith("Img.php")){
                        int setStart = fileName.indexOf("set=");
                        setStart += 4;
                        int setEnd = fileName.indexOf("&", setStart);
                        int numStart =  fileName.indexOf("cardnum=");
                        numStart +=8;
                        int numEnd = fileName.indexOf("&", numStart);
                        String set = fileName.substring(setStart, setEnd);
                        String num = fileName.substring(numStart, numEnd);
                        fileName = set+num+".jpg";
                    }
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
                    publishProgress("" + (int) (((double) f / (double) fileCount) * 100));
                    Log.d(TAG, "Progress: " + (int) (((double) f / (double) fileCount) * 100));

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

            updateButtonText();
            // Display File path after downloading
            Toast.makeText(getApplicationContext(),
                    message, Toast.LENGTH_LONG).show();

        }
    }
}
