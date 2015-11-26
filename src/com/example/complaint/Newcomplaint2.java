package com.example.complaint;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Newcomplaint2 extends Activity {
    EditText name = (EditText) findViewById(R.id.editText);
    EditText phone = (EditText) findViewById(R.id.editText2);
    Spinner city = (Spinner) findViewById(R.id.spinner);
    EditText location = (EditText) findViewById(R.id.editText3);
    EditText details = (EditText) findViewById(R.id.editText4);
    String typecomp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcomplaint2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button next1 = (Button) findViewById(R.id.next1);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Newcomplaint2.this, Selectimage.class);
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String location1 = location.getText().toString();
                String details1 = details.getText().toString();
                String city1 = city.getSelectedItem().toString();
                i.putExtra("typecomp",typecomp);
                i.putExtra("location", location1);
                i.putExtra("phone", phone1);
                i.putExtra("name", name1);
                i.putExtra("details", details1);
                i.putExtra("city", city1);
                startActivity(i);

            }
        });

        class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }

        }}
//ميثود نوع الشكوى

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton5:
                if (checked)
                    typecomp = "نفايات";
                break;
            case R.id.radioButton:
                if (checked)
                    typecomp = "مياه";
                break;
            case R.id.radioButton3:
                if (checked)
                    typecomp = "صرف صحي";
                break;
            case R.id.radioButton6:
                if (checked)
                    typecomp = "اخرى";
                break;

        }

    }
}

