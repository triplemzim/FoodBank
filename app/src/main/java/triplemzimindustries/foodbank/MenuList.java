package triplemzimindustries.foodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MenuList extends AppCompatActivity {
    Food tempFood;
    public static Vector<String> v;
    Button cart;
    ListView menu ;
    EditText qText;
    Button btnSubmit;
    List<String> foods;
    List<JSONObject> rcvList;
    Vector<Food> foodids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        cart = (Button) findViewById(R.id.btnCart);
        menu = (ListView)findViewById(R.id.menulistView);
        qText = (EditText) findViewById(R.id.txtQuantity);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        qText.setVisibility(View.INVISIBLE);
        btnSubmit.setVisibility(View.INVISIBLE);

        retrieve_food_names();

        Toast.makeText(MenuList.this, "Food no: "+Integer.toString(foods.size()), Toast.LENGTH_SHORT).show();
        String food[] = {"Hydrabadi Biriani\nPrice: 400TK\n\n","Beef Biriyani\nPrice: 120TK\n\n","Tehari (FULL)\nPrice: 120TK\n\n","Beef Burger\nPrice: 80TK\n\n","Chicken Burger\nPrice: 70TK\n\n"};
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.menulayout,R.id.foodtxtid,foods);
        menu.setAdapter(adapter);

        //String hotelName = getIntent().getStringExtra("HotelName");

       // Toast.makeText(MenuList.this, "Selected Hotel is: "+hotelName+ "printed from MenuList", Toast.LENGTH_SHORT).show();

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menu.setVisibility(View.INVISIBLE);
                qText.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);

                tempFood = foodids.get(position);



            }
        });

    }

    public void retrieve_food_names(){

        String data=null;
        try {
            data = URLEncoder.encode("command","UTF-8")+"="+URLEncoder.encode("hotel_select","UTF-8");
            data+= "&"+URLEncoder.encode("hotel_name","UTF-8")+"="+URLEncoder.encode(MainActivity.hotel_name,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BackgroundWork bckw = new BackgroundWork();
        bckw.myURL = bckw.commonURL+"activity.php";
        rcvList = bckw.initialize(MenuList.this,"food",data);




        try {


            foods = new ArrayList<>();
            foodids = new Vector<>();

            int count = 0;
            String a,b,c,d;
            while(count<rcvList.size()){
                JSONObject jo;
                jo = rcvList.get(count);
                a = (jo.getString("food_id"));
                b = (jo.getString("food_name"));
                c = (jo.getString("menu_type"));
                d = (jo.getString("price"));
                foodids.add(new Food(a,b,c,d));
                b = b+"\nMenu Type: "+c+"\nPrice: "+d+"\n\n";

                foods.add(b);

                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public void submit_quantity(View view){
        qText.setVisibility(view.INVISIBLE);
        btnSubmit.setVisibility(view.INVISIBLE);
        menu.setVisibility(view.VISIBLE);
        String q;
        int val  = Integer.parseInt(qText.getText().toString());
        tempFood.quantity = val;
        Cart.addedfood.add(tempFood);

        qText.setText("");

    }

    public void gotocart(View view){
        Toast.makeText(MenuList.this, "Clicked on cart from menulist", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(MenuList.this,Cart.class);
        startActivity(I);
    }
}
