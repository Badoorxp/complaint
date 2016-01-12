package com.example.complaint;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Finish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);

		Button goback=(Button)findViewById(R.id.ret_main);
		Button newcomp=(Button)findViewById(R.id.newcom);
		Button exit=(Button)findViewById(R.id.exit);

		
		newcomp.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Finish.this,Newcomplaint2.class);
				startActivity(i);
			}
			
		});
	
	goback.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(Finish.this, MainActivity.class);
			startActivity(i);
		}

	});


		exit.setOnClickListener(new OnClickListener()
	{
		@Override
		public void onClick(View view)
		{
			onBackPressed();
        }
    });
}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finishAffinity();
	}

}
