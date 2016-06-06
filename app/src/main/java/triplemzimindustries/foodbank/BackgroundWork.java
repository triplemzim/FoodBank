package triplemzimindustries.foodbank;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;

/**
 * Created by Zim on 6/3/2016.
 */
public class BackgroundWork implements AsyncResponse {
    JSONObject job;
    JSONArray jar;
    Activity thatActivity;

    String JsonString = null;
    public static String myURL = "";
    public static String commonURL = "http://10.202.48.151/foodbank/";
//    String myUrl = "hhtp://192.168";
    public List<JSONObject> ret;
    public List<JSONObject> initialize(Activity thatAct,String type, String rcvData){
        thatActivity = thatAct;
        //                data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(b,"UTF-8");
//                data += "&" + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(c,"UTF-8");
        ret = new ArrayList<>();
        background_task bck = new background_task();

        //bck.delegate = this;
        //Toast.makeText(thatActivity, "BCK: "+rcvData, Toast.LENGTH_SHORT).show();
        bck.execute(rcvData);



        try {
            bck.get();
            Toast.makeText(thatActivity, bck.getStatus().toString(), Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Toast.makeText( thatActivity,"Task finished", Toast.LENGTH_SHORT).show();

        return ret;

    }
    @Override
    public void processFinish(String output) {
        try {
            job = new JSONObject(output);

            jar = job.getJSONArray("server_response");
            int count = 0;
            int length = jar.length();
            ret = new ArrayList<>();
            while(count < jar.length()){
                JSONObject jo;
                jo = jar.getJSONObject(count);
                ret.add(jo);
                count++;
            }
            // Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class background_task extends AsyncTask<String,Void,String> {
        String url_addr;
        public AsyncResponse delegate = null;

        @Override
        protected void onPreExecute() {
            url_addr = myURL;
        }

        @Override
        protected String doInBackground(String... args) {
            String data= null,test = "";
            data = args[0];
           // Toast.makeText(thatActivity, "doing: "+data, Toast.LENGTH_SHORT).show();

            try {
                URL url = new URL(url_addr);
                HttpURLConnection httpurlcon = (HttpURLConnection) url.openConnection();

                httpurlcon.setRequestMethod("POST");
                httpurlcon.setDoOutput(true);
                httpurlcon.setDoInput(true);

                //Toast.makeText(MainActivity.this, "paici", Toast.LENGTH_SHORT).show();

                //Toast.makeText(thatActivity, "doing bck: "+data, Toast.LENGTH_SHORT).show();

                OutputStream outstream = httpurlcon.getOutputStream();

                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outstream,"UTF-8"));

                wr.write(data);
                wr.flush();
                wr.close();
                outstream.close();



                // usrName.setText("Output Completed", TextView.BufferType.EDITABLE);
                //if(true) return data;
                // if(true) return "{\"server_response\":[{\"status\":\"success\"}]}";
                InputStream instream = httpurlcon.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(instream));

                StringBuilder stringBuilder = new StringBuilder();
                while((JsonString = rd.readLine()) !=null){
                    stringBuilder.append(JsonString + "\n");

                }
                instream.close();
                rd.close();

                httpurlcon.disconnect();

                test=stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                job = new JSONObject(test);

                jar = job.getJSONArray("server_response");
                int count = 0;
                int length = jar.length();
                ret = new ArrayList<>();
                while(count < jar.length()){
                    JSONObject jo;
                    jo = jar.getJSONObject(count);
                    ret.add(jo);
                    count++;
                }
                // Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return test;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String chk) {



            //Toast.makeText(thatActivity, "post : "+chk, Toast.LENGTH_SHORT).show();
           // delegate.processFinish(chk);
        }
    }


}
