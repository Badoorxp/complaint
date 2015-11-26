package com.example.complaint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Selectimage extends Activity
{
	ImageView photo;
	File imagefile;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimage);
		Button takephoto= (Button)findViewById(R.id.takephotobtn);
		photo = (ImageView)findViewById(R.id.take);
		Button send=(Button)findViewById(R.id.send);
		Button change=(Button)findViewById(R.id.newcom);
		Button exit=(Button)findViewById(R.id.exit);

		 //عشان اجيب الداتا
		final Intent info=this.getIntent();
		String type_comp=info.getExtras().getString("typecomp");
		String location=info.getExtras().getString("location");
		String phone=info.getExtras().getString("phone");
		String name=info.getExtras().getString("name");
		String city=info.getExtras().getString("city");
		String details=info.getExtras().getString("details");


		//اكشن فتح الكاميرا
		takephoto.setOnClickListener(new OnClickListener()
			
		{
			@Override
			public void onClick(View view)
			{
				Intent a= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(a,0);
			}
		});




		send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Selectimage.this,Finish.class);

				startActivity(i);

				
			}
			
		});

		change.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Selectimage.this,Newcomplaint2.class);
				startActivity(i);
				
				
			}


			
		});


		//mac address
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		 	String macAddress = wInfo.getMacAddress();




		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i=new Intent(Selectimage.this,MainActivity.class);
				startActivity(i);
			}

		});
	}

//كود الكاميرا
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==0)
		{
			if (resultCode==RESULT_OK)
			{
				Bitmap th=(Bitmap) data.getExtras().get("data");

				photo.setImageBitmap(th);
				imagefile  = new File("/galary/Complaint/test_image.jpg");
				th= BitmapFactory.decodeFile(imagefile.getPath());
			}
			else if (resultCode==RESULT_CANCELED)
			{

			}

		}

	}


}
