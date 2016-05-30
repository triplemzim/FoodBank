package triplemzimindustries.foodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText usrName,Passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrName = (EditText)findViewById(R.id.editTextUser);
        Passwd = (EditText) findViewById(R.id.editTextPasswd);
        login = (Button) findViewById(R.id.btnLogin);
    }
    public void check_authorization(View view){
        Intent I = new Intent(MainActivity.this,HotelList.class);
        startActivity(I);
    }
}
