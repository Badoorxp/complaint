package com.example.complaint;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Newcomplaint2 extends Activity {

    public static final String prefs = "MyAppPreferences";
    EditText name;
    EditText phone;
    Spinner city;
    Spinner typecomp;
    EditText extra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomplaint2);
        //التعاريف
        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        city = (Spinner) findViewById(R.id.spinner);
        typecomp = (Spinner) findViewById(R.id.typecomp);
        extra = (EditText) findViewById(R.id.editText4);

        Button next1 = (Button) findViewById(R.id.next1);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type_complant, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        typecomp.setAdapter(adapter2);





// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        city.setAdapter(adapter);






        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Selectimage.class);

                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                /*String details1 = details.getText().toString();
                String city1 = city.getSelectedItem().toString();
                String typecomp1 = typecomp.getSelectedItem().toString();*/

                i.putExtra("typecomp", typecomp.getSelectedItemId()+"");
                i.putExtra("phone", phone.getText().toString());
                i.putExtra("name", name.getText().toString());
                i.putExtra("extra", extra.getText().toString());
                i.putExtra("city", city.getSelectedItemId()+"");
                boolean ok=true;

                if (name1.length()==0) {
                    name.setError("الرجاء إدخال الاسم");
                    ok=false;
                }

                if(!(AlpCheck(name1)))
                {
                    name.setError("الرجاء إدخال الاسم صحيحاً بدون أرقام");
                    ok=false;
                }

                if((SpcCount(name1)!=2))
                {
                    name.setError("الرجاء إدخال الاسم الثلاثي");
                    ok=false;
                }

                String phoneS=phone1+"";
                if (!(phoneS.length() == 10/*||phone1.length()==13*/))
                {
                    phone.setError("الرجاء إدخال رقم الهاتف المكون من 10 خانات");
                    ok=false;
                }


                if(ok)
                {
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public static boolean AlpCheck(String s) {
        boolean x = true;
        for (int i = 0; i < s.length(); i++) {
            if ((Character.isLetter(s.charAt(i))) || (s.charAt(i) == ' ')) {
                continue;
            } else {
                x = false;
                break;
            }
        }
        return (x);
    }

    public static int SpcCount(String s) {
        int x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==' ')
            {
                x++;
            }
        }
        return (x);
    }
}




