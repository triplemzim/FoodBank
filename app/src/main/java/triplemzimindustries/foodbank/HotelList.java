package triplemzimindustries.foodbank;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelList extends AppCompatActivity {
    ListView hotellist;
    Button cart;
    JSONObject job;
    JSONArray jar;
    String jsonString;
    List<String> hotels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        Toast.makeText(HotelList.this, "This page will show hotel list", Toast.LENGTH_SHORT).show();
       
        hotellist = (ListView) findViewById(R.id.hotellistView);
        cart = (Button) findViewById(R.id.btnCart);

       /* jsonString = getIntent().getExtras().getString("Response");
        try {
            job = new JSONObject(jsonString);
            jar = job.getJSONArray("Response");
            hotels = new ArrayList<>();

            int count = 0;

            while(count<jar.length()){
                JSONObject jo;
                jo = jar.getJSONObject(count);
                hotels.add(jo.getString("name"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        final List<String> hotelNames = Arrays.asList("Star", "Royal", "Pizza King", "Plate21", "Korai Gost");
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.lvlayout,R.id.hoteltxtid,hotelNames);

        hotellist.setAdapter(adapter);

        hotellist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked on "+ hotelNames.get(position),Toast.LENGTH_SHORT).show();
                Intent I = new Intent(HotelList.this,MenuList.class);
                I.putExtra("HotelName", hotelNames.get(position));
                startActivity(I);
            }
        });



    }
    public void gotocart(View view){
        Toast.makeText(HotelList.this, "Clicked on cart from Hotel List", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(HotelList.this,Cart.class);
        startActivity(I);
    }


}
