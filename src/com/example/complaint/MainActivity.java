package com.example.complaint;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newcomp=(Button)findViewById(R.id.newcomp);


        newcomp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Newcomplaint2.class);
                startActivity(i);

            }
        });

    }

    //الخروج من البرنامج عند ضغط زر العودة على الجهاز بدل من العودة للشاشة السابقة
    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

}
