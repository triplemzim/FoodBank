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

public class MenuList extends AppCompatActivity {

    Button cart;
    ListView menu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        cart = (Button) findViewById(R.id.btnCart);
        menu = (ListView)findViewById(R.id.menulistView);



        String food[] = {"Hydrabadi Biriani\nPrice: 400TK\n\n","Beef Biriyani\nPrice: 120TK\n\n","Tehari (FULL)\nPrice: 120TK\n\n","Beef Burger\nPrice: 80TK\n\n","Chicken Burger\nPrice: 70TK\n\n"};
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.menulayout,R.id.foodtxtid,food);
        menu.setAdapter(adapter);

        String hotelName = getIntent().getStringExtra("HotelName");

        Toast.makeText(MenuList.this, "Selected Hotel is: "+hotelName+ "printed from MenuList", Toast.LENGTH_SHORT).show();

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    public void gotocart(View view){
        Toast.makeText(MenuList.this, "Clicked on cart from menulist", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(MenuList.this,Cart.class);
        startActivity(I);
    }
}
