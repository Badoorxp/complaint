package com.example.complaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		Button newcomp=(Button)findViewById(R.id.newcom);
		Button goback=(Button)findViewById(R.id.exit);
		Button exit=(Button)findViewById(R.id.exitapp);

		
		newcomp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Finish.this,Newcomplaint.class);
				startActivity(i);
				
				
			}
			
		});
	
	goback.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(Finish.this,MainActivity.class);
			startActivity(i);
			
			
		}
		
	});
    exit.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View arg0) {
android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        }
    });
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
