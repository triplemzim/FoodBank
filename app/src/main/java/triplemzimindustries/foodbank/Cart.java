package triplemzimindustries.foodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Cart extends AppCompatActivity {
    public static Vector<Food> addedfood = new Vector<>();
    Button chkout;
    TextView totalprice;
    ListView finalList;
    List<String> foodlist;
    int sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        chkout = (Button)findViewById(R.id.btnCheckOut);
        totalprice = (TextView) findViewById(R.id.totalprice);
        finalList = (ListView)findViewById(R.id.cartlistView);
        foodlist = new ArrayList<>();

        int count =0;
        sum = 0;
        while(count<addedfood.size()){
            String temp="";
            Food tempFood = addedfood.get(count);
            temp = tempFood.name;
            temp+="\nMenu Type: "+tempFood.menuType;
            temp+="\nPrice: "+tempFood.price;
            temp+="\nQuantity: "+Integer.toString(tempFood.quantity);
            temp+="\n\n";
            foodlist.add(temp);
            count++;
            sum += Integer.parseInt(tempFood.price)*tempFood.quantity;
        }

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.menulayout,R.id.foodtxtid,foodlist);
        finalList.setAdapter(adapter);
        totalprice.setText(Integer.toString(sum)+" BDT");

    }
    public void complete_checkout(View view){
        Toast.makeText(Cart.this, "Checkout Complete", Toast.LENGTH_SHORT).show();
        addedfood.clear();
        Intent I = new Intent(Cart.this,HotelList.class);
        startActivity(I);

    }
}
