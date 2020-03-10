package fluffyhairedkid.dicemasterscompanion;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class CardViewer extends Activity {
    int groupPosition = 0;
    int childPosition = 0;
    int maxChar = 0;
    int maxCard = 0;
    List<Integer> heroCards;
    Map<String, List<Integer>> imageCollection;
    Map<String, List<String>> imageNameCollection;
    List<String> groupList;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String url;
    private final Activity dmActivity = this;
    private final String baseurl = "http://www.dicecoalition.com/cardservice/tzapp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_viewer);
        Bundle extras = getIntent().getExtras();

        groupPosition = extras.getInt("group");
        childPosition = extras.getInt("child");

        //imageCollection = CardCollector.imageCollection;
        //groupList = CardCollector.groupList;
        imageCollection = CardCollectorNEW.imageCollection;
        groupList = CardCollectorNEW.groupList;
        imageNameCollection = CardCollectorNEW.cardImageNames;

        ImageView imgView = (ImageView) findViewById(R.id.ivViewer);

        maxChar = groupList.size() - 1;
        setValues();
        setImageView();

        imgView.setOnTouchListener(new OnSwipeTouchListener(CardViewer.this) {
            public void onSwipeTop() {
                oneCardDown();
            }

            public void onSwipeRight() {
                oneCardUp();
            }

            public void onSwipeLeft() {
                oneCardDown();
            }

            public void onSwipeBottom() {
                oneCardUp();
            }

        });

    }

    public void setImageView() {

        ImageView imgView = (ImageView) findViewById(R.id.ivViewer);
        int cardimage = heroCards.get(childPosition);
        // if it will return 0, then the resource you are looking for does not exist
        //imgView.getResources().getIdentifier(cardimage)
        String cardName = imageNameCollection.get(groupList.get(groupPosition)).get(childPosition);
        String filename = cardName + ".jpg";
        if (cardimage > 0) {
            imgView.setBackgroundResource(0);
            imgView.setImageResource(cardimage);
        } else {
            try {
                imgView.setBackgroundResource(0);
                File f = new File(dmActivity.getFilesDir(), filename);
                if(!f.exists() || f.length() == 0)
                    new FileDownload().execute(baseurl + filename);
                else {
                    Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                    Drawable drawable = new BitmapDrawable(bitmap);
                    imgView.setImageDrawable(drawable);
                }
            }catch(Exception ex) {
				File f = new File(dmActivity.getFilesDir(), filename);
				if(f.exists())
					f.delete();
                imgView.setBackgroundResource(this.getResources().getIdentifier("nopic", "drawable", this.getPackageName()));
                imgView.setImageResource(0);
            }
        }
    }

    public void oneCardUp() {
        if (childPosition != 0) {
            childPosition = childPosition - 1;
            setImageView();
        } else {
            if (groupPosition != 0) {
                groupPosition = groupPosition - 1;
                setValues();
                childPosition = maxCard;
                setImageView();
            }
        }
    }

    public void oneCardDown() {
        if (childPosition != maxCard) {
            childPosition++;
            setImageView();
        } else {
            if (groupPosition != maxChar) {
                groupPosition++;
                setValues();
                childPosition = 0;
                setImageView();
            }
        }
    }

    public void setValues() {
        //TODO: IndexOutOfBoundsException being thrown for some users
        if(groupPosition != -1 && groupList.size() > groupPosition && !imageCollection.isEmpty()) {
            heroCards = imageCollection.get(groupList.get(groupPosition));
            maxCard = heroCards.size() - 1;
        }
    }

    public class FileDownload extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(CardViewer.this);
            this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                if(!isNetworkConnected()){
                    return "Please connect to the internet to download offline cards.";
                }
                //DataManagement.verifyStoragePermissions(dmActivity);
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream());

                //Extract file name from URL
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());
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
                if(!outputFile.exists())
                    outputFile.createNewFile();
                // Output stream to write file
                OutputStream output = openFileOutput(fileName, dmActivity.MODE_PRIVATE);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return "Downloaded to: "+ outputFile.getAbsolutePath();//outputFilePath;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            //Download failed, so delete file
            if(fileName != null && !fileName.trim().isEmpty()) {
                File failedFile = new File(dmActivity.getFilesDir(), fileName);
                if (failedFile.exists())
                    failedFile.delete();
            }

            return "Something went wrong";
        }

        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        }
        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.progressDialog.dismiss();

            // Display File path after downloading
            //Toast.makeText(getApplicationContext(),
            //        message, Toast.LENGTH_LONG).show();

            ImageView imgView = (ImageView) findViewById(R.id.ivViewer);
            if(message.contains(": ")) {
                String path = message.split(": ")[1];
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                Drawable drawable = new BitmapDrawable(bitmap);
                imgView.setImageDrawable(drawable);
            }
            else {
                imgView.setBackgroundResource(dmActivity.getResources().getIdentifier("nopic", "drawable", dmActivity.getPackageName()));
                imgView.setImageResource(0);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
