package com.example.complaint;


import android.content.Intent;

import android.os.Bundle;
import android.app.Activity;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


import android.widget.Spinner;


public class Newcomplaint2 extends Activity {

    EditText name;
    EditText phone;
    Spinner city;
    Spinner typecomp;
    EditText extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomplaint2);

        name = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
        city = (Spinner) findViewById(R.id.spinner);
        typecomp = (Spinner) findViewById(R.id.typecomp);
        extra = (EditText) findViewById(R.id.editText4);

        Button next1 = (Button) findViewById(R.id.next1);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.type_complaint, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        typecomp.setAdapter(adapter2);





        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        city.setAdapter(adapter);






        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Selectimage.class);

                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();



                boolean ok=true;

                if (name1.length()==0) {
                    name.setError(getString(R.string.name_empty));
                    ok=false;
                }

                if(!(AlpCheck(name1)))
                {
                    name.setError(getString(R.string.hasNum));
                    ok=false;
                }

                int c=SpcCount(name1);
                if((c!=2))
                {
                    name.setError(getString(R.string.hasSpc));
                    ok=false;
                }

                String phoneS=phone1+"";
                if (!(phoneS.length() == 10/*||phone1.length()==13*/))
                {
                    phone.setError(getString(R.string.shortNum));
                    ok=false;
                }


                if(ok)
                {


                    i.putExtra("typecomp", Integer.toString(typecomp.getSelectedItemPosition()));
                    //Toast.makeText(getBaseContext(), Integer.toString(typecomp.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
                    i.putExtra("phone", phone.getText().toString());
                    i.putExtra("name", name.getText().toString());
                    i.putExtra("extra", extra.getText().toString());
                    i.putExtra("city", Integer.toString(city.getSelectedItemPosition()));
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




