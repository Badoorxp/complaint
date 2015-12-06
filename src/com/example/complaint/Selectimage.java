package com.example.complaint;

import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Selectimage extends Activity {
	ImageView photo;
	File imagefile;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimage);


		Button takephoto = (Button) findViewById(R.id.takephotobtn);
		photo = (ImageView) findViewById(R.id.take);
		Button send = (Button) findViewById(R.id.send);
		Button change = (Button) findViewById(R.id.newcom);
		Button exit = (Button) findViewById(R.id.exit);

		//عشان اجيب الداتا
		final Intent info = this.getIntent();
		String typecomp = info.getExtras().getString("typecomp");
		String email = info.getExtras().getString("email");
		String phone = info.getExtras().getString("phone");
		String name = info.getExtras().getString("name");
		String city = info.getExtras().getString("city");
		String details = info.getExtras().getString("details");


		//اكشن فتح الكاميرا
		takephoto.setOnClickListener(new OnClickListener()

		{
			@Override
			public void onClick(View view) {
				Intent a = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(a, 0);
			}
		});


		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Selectimage.this, Finish.class);

				startActivity(i);


			}

		});

		change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Selectimage.this, Newcomplaint2.class);
				startActivity(i);


			}


		});




		//كود التلفون
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		String IMMEI=telephonyManager.getDeviceId();
		Toast.makeText(getBaseContext(),IMMEI,Toast.LENGTH_SHORT).show();


		//mac address
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		String macAddress = wInfo.getMacAddress();


		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Selectimage.this, MainActivity.class);
				startActivity(i);
			}

		});


	}


	//كود الكاميرا
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				Bitmap th = (Bitmap) data.getExtras().get("data");

					photo.setImageBitmap(th);




			} else if (resultCode == RESULT_CANCELED) {

			}

		}






	}








}