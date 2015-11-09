package com.example.complaint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Selectimage extends Activity {
	Button takephoto;
	ImageView photo;
	public static final int CAM_REQUEST = 1313;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimage);
		 takephoto = (Button)findViewById(R.id.newc);
		photo = (ImageView)findViewById(R.id.take);
		Button send=(Button)findViewById(R.id.exitapp);
		Button change=(Button)findViewById(R.id.newcom);
		Button exit=(Button)findViewById(R.id.exit);
		photo.setOnClickListener(new takephotoClicker());

		send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent("finish");
				startActivity(i);
				
				
			}
			
		});

		change.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent("newcomplaint");
				startActivity(i);
				
				
			}


			
		});


		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();
				
				
			}
			
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==CAM_REQUEST){
			Bitmap th=(Bitmap) data.getExtras().get("data");
			photo.setImageBitmap(th);
		}
	}
	class takephotoClicker implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraintent,CAM_REQUEST);
	}}
}
