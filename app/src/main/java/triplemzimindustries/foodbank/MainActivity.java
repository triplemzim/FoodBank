package triplemzimindustries.foodbank;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity  extends AppCompatActivity implements AsyncResponse {

    Button login;
    EditText usrName,Passwd;
    boolean loggedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrName = (EditText)findViewById(R.id.editTextUser);
        Passwd = (EditText) findViewById(R.id.editTextPasswd);
        login = (Button) findViewById(R.id.btnLogin);
        login.setEnabled(false);


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            Toast.makeText(MainActivity.this, "Connected to network", Toast.LENGTH_SHORT).show();
            login.setEnabled(true);
        }
        else{
            Toast.makeText(MainActivity.this, "No network Available", Toast.LENGTH_SHORT).show();
        }
    }
    public void check_authorization(View view){
//        Intent I = new Intent(MainActivity.this,HotelList.class);
//        startActivity(I);
        String usrname,passwd,data = null;


        usrname = usrName.getText().toString();
        passwd = Passwd.getText().toString();

        try {
            data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode("password","UTF-8");
            data += "&" + URLEncoder.encode(usrname,"UTF-8")+"="+URLEncoder.encode(passwd,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        String url_addr;
//        url_addr = "http://pizza.byethost9.com/Login.php";
//        try {
//            URL url = new URL(url_addr);
//            Toast.makeText(MainActivity.this, "came here", Toast.LENGTH_LONG).show();
//            HttpURLConnection httpurlcon = (HttpURLConnection) url.openConnection();
//
//            httpurlcon.setRequestMethod("POST");
//            httpurlcon.setDoOutput(true);
//
//            OutputStream outstream = httpurlcon.getOutputStream();
//            if(outstream == null){
//                Toast.makeText(MainActivity.this, "got null", Toast.LENGTH_SHORT).show();
//            }
//            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outstream,"UTF-8"));
//            wr.write(data);
//            wr.flush();
//            wr.close();
//            outstream.close();

//            InputStream instream = httpurlcon.getInputStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(instream,"UTF-8"));

//            String test = rd.readLine();
//            if(test.equals("success")){
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//            }

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }




        background_task bck = new background_task();
        bck.delegate = this;
        bck.execute(data);


//        Intent I = new Intent(MainActivity.this,HotelList.class);
//        startActivity(I);
//        Toast.makeText(MainActivity.this, "came here", Toast.LENGTH_LONG).show();

//        String ret = null;
//
//        if(ret.equals("success")){
//
//            Intent I = new Intent(MainActivity.this,HotelList.class);
//            startActivity(I);
//        }




    }

    @Override
    public void processFinish(String output) {
        if(output.equals("success")){
            Intent I = new Intent(MainActivity.this,HotelList.class);
            startActivity(I);
        }
        else{
            Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();
        }
    }

    class background_task extends AsyncTask<String,Void,String> {
        String url_addr;
        public AsyncResponse delegate = null;

        @Override
        protected void onPreExecute() {
            url_addr = "http://pizza.byethost9.com/Login.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String data = null,test = "";
            data = args[0];
            try {
                URL url = new URL(url_addr);
                HttpURLConnection httpurlcon = (HttpURLConnection) url.openConnection();

                httpurlcon.setRequestMethod("POST");
                httpurlcon.setDoOutput(true);

                OutputStream outstream = httpurlcon.getOutputStream();
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outstream,"UTF-8"));
                wr.write(data);
                wr.flush();
                wr.close();
                outstream.close();
                InputStream instream = httpurlcon.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(instream,"UTF-8"));

                test = rd.readLine();
                instream.close();
                rd.close();

                httpurlcon.disconnect();

                if(test.equals("success")){
                    return "success";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            delegate.processFinish(chk);
        }
    }

/*    public class background_login extends AsyncTask<Void,Void,Void>{
        String url_addr;
        public AsyncResponse delegate = null;
        @Override
        protected void onPreExecute() {
            url_addr = "http://pizza.byethost9.com/Login.php";
//            url_addr = "http://www.android.com";
        }

        @Override
        protected Void doInBackground(Void... args) {
            String data = null;
//            data = args[0];

            try {
                URL url = new URL(url_addr);

                Toast.makeText(MainActivity.this, "came here", Toast.LENGTH_LONG).show();
//                if(true) return false;
                HttpURLConnection httpurlcon = (HttpURLConnection) url.openConnection();

                httpurlcon.setRequestMethod("POST");
                httpurlcon.setDoOutput(true);
                Toast.makeText(MainActivity.this, "came here", Toast.LENGTH_LONG).show();

//                OutputStream outstream = httpurlcon.getOutputStream();
//                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outstream,"UTF-8"));
//                wr.write(data);
//                wr.flush();
//                wr.close();
//                outstream.close();
//
//                InputStream instream = httpurlcon.getInputStream();
//                BufferedReader rd = new BufferedReader(new InputStreamReader(instream,"UTF-8"));
//
//                String test = rd.readLine();
//                if(test.equals("success")){
//                    return true;
//                }



            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            return "Success";
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
//            if(chk){
//                Toast.makeText(getApplicationContext(), "Login Successfull!", Toast.LENGTH_SHORT).show();
//                delegate.processFinish("success");
//            }
//            else{
//                Toast.makeText(getApplicationContext(), "Login Failed! Try Again!", Toast.LENGTH_SHORT).show();
//                delegate.processFinish("fail");
//            }

        }
    }*/
}
