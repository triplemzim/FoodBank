package triplemzimindustries.foodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Cart extends AppCompatActivity {
    Button chkout;
    TextView totalprice;
    ListView finalList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        chkout = (Button)findViewById(R.id.btnCheckOut);
        totalprice = (TextView) findViewById(R.id.totalprice);
        finalList = (ListView)findViewById(R.id.cartlistView);


    }
    public void complete_checkout(View view){
        Toast.makeText(Cart.this, "Checkout Complete", Toast.LENGTH_SHORT).show();
        Intent I = new Intent(Cart.this,HotelList.class);
        startActivity(I);

    }
}
