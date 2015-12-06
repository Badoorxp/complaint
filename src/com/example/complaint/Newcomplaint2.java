package com.example.complaint;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

public class Newcomplaint2 extends Activity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomplaint2);
        //التعاريف
        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText phone = (EditText) findViewById(R.id.editText2);
        final Spinner city = (Spinner) findViewById(R.id.spinner);
        final Spinner typecomp = (Spinner) findViewById(R.id.typecomp);
        final EditText email = (EditText) findViewById(R.id.editText3);
        final EditText details = (EditText) findViewById(R.id.editText4);
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
                Intent i = new Intent(Newcomplaint2.this, Selectimage.class);
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String email1 = email.getText().toString();
                String details1 = details.getText().toString();
                String city1 = city.getSelectedItem().toString();
                String typecomp1 = typecomp.getSelectedItem().toString();

                i.putExtra("typecomp", typecomp1);
                i.putExtra("email", email1);
                i.putExtra("phone", phone1);
                i.putExtra("name", name1);
                i.putExtra("details", details1);
                i.putExtra("city", city1);
                startActivity(i);

            }
        });








    }










}



