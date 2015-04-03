package test.com.test;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testButton = (Button) findViewById(R.id.button);
        final Context context = getApplicationContext();
        final CharSequence text = "Hello toast!";
        final int duration = Toast.LENGTH_LONG;


        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, text, duration);
                Log.v(TAG, "test : ");

                new ConnectNetworkTask().execute();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

class ConnectNetworkTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    private static final String TAG = "ConnectNetworkTaskTAG";

    protected String doInBackground(String... urls) {
        try {

            HttpGet httpGet = new HttpGet("http://ip.jsontest.com/");
            HttpClient client = new DefaultHttpClient();
            try {
                HttpResponse response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String result = convertStreamToString(instream);
                    instream.close();
                    Log.v(TAG, "result : " +result);
                }
            } catch (ClientProtocolException e) {
                Log.e(TAG, e.getMessage(), e);
            }catch (UnknownHostException e) {
                Log.e(TAG, e.getMessage(), e);
            }catch (ConnectTimeoutException e) {
                Log.e(TAG, e.getMessage(), e);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }

            return "";
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String s) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            Log.e("SHIMDEE_LIBS", e.getMessage(), e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e("SHIMDEE_LIBS", e.getMessage(), e);
            }
        }
        return sb.toString();
    }
}
