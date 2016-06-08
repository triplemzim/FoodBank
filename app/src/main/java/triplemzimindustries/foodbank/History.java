package triplemzimindustries.foodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    ProgressDialog progress;
    Button btn;
    ListView lst;
    List<JSONObject> rcvList;
    List<String> history;
    JSONObject job;
    JSONArray jar;
    String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        btn = (Button) findViewById(R.id.btnBack);
        lst = (ListView) findViewById(R.id.listHistory);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Retrieving menu...\nPlease wait...");

        progress.show();
        retrieve_history();
        progress.dismiss();

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.menulayout,R.id.foodtxtid,history);

        lst.setAdapter(adapter);

    }

    public void goback(View view){
        Intent i = new Intent(History.this,Cart.class);
        startActivity(i);
    }

    public void retrieve_history(){

        String data=null;
        try {
            data = URLEncoder.encode("command","UTF-8")+"="+URLEncoder.encode("history","UTF-8");
            data +="&"+URLEncoder.encode("customer_id","UTF-8")+"="+URLEncoder.encode(MainActivity.customer_id,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BackgroundWork bckw = new BackgroundWork();
        bckw.myURL = bckw.commonURL+"activity.php";
        rcvList = bckw.initialize(History.this,"normal",data);



        try {


            history = new ArrayList<>();
            String temp ="";
            int count = 0;
            Toast.makeText(History.this, "history: "+Integer.toString(rcvList.size()), Toast.LENGTH_SHORT).show();
            while(count<rcvList.size()) {
                temp = "";
                JSONObject jo, jo2;
                jo = rcvList.get(count);
                temp = jo.getString("order_time");
                jar = jo.getJSONArray("order_string");
                int i = 0;
                while (i < jar.length()) {
                    jo2 = jar.getJSONObject(i);
                    i++;
                    temp += "\n" + jo2.getString("hotel_name");
                    temp += ": " + jo2.getString("food_name");
                }
                temp += "\n\nPrice: " + jo.getString("total_price");
                temp+="\n";
                history.add(temp);



                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
