package triplemzimindustries.foodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Arrays;
import java.util.List;

public class HotelList extends AppCompatActivity {
    ProgressDialog progress;
    ListView hotellist;
    Button cart;
    JSONObject job;
    JSONArray jar;
    String jsonString;
    List<String> hotels;
    List<JSONObject> rcvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        Toast.makeText(HotelList.this, "This page will show hotel list", Toast.LENGTH_SHORT).show();
       
        hotellist = (ListView) findViewById(R.id.hotellistView);
        cart = (Button) findViewById(R.id.btnCart);
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Retrieving Hotel names.\nPlease wait...");
        progress.show();
        retrieve_hotel_names();
        progress.dismiss();

//        Toast.makeText(HotelList.this,"Showing hotel no: "+ Integer.toString(hotels.size()), Toast.LENGTH_SHORT).show();
        final List<String> hotelNames = Arrays.asList("Star", "Royal", "Pizza King", "Plate21", "Korai Gost");
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.lvlayout,R.id.hoteltxtid,hotels);

        hotellist.setAdapter(adapter);

        hotellist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),"Clicked on "+ hotels.get(position),Toast.LENGTH_SHORT).show();
                Intent I = new Intent(HotelList.this,MenuList.class);
//                I.putExtra("HotelName", hotelNames.get(position));
                MainActivity.hotel_name = hotels.get(position);
                startActivity(I);

            }
        });


    }
    public void gotocart(View view){
//        Toast.makeText(HotelList.this, "Clicked on cart from Hotel List", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(HotelList.this,Cart.class);
        startActivity(I);
    }

    public void retrieve_hotel_names(){

        String data=null;
        try {
            data = URLEncoder.encode("command","UTF-8")+"="+URLEncoder.encode("hotel","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BackgroundWork bckw = new BackgroundWork();
        bckw.myURL = bckw.commonURL+"activity.php";
        rcvList = bckw.initialize(HotelList.this,"hotel",data);



        try {


            hotels = new ArrayList<>();

            int count = 0;

            while(count<rcvList.size()){
                JSONObject jo;
                jo = rcvList.get(count);
                hotels.add(jo.getString("hotel_name"));
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


}
