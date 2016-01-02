package com.example.complaint;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Selectimage extends Activity  {


	ImageView photo;
	GPSTracker gps;

	String encodedPhoto;

	String typecomp;
	String phone;
	String name;
	String city;
	String extra;
	String IMEI;
	String latitude;
	String longitude;
	String gmail;
	Intent NewCom;
	List<NameValuePair> nV;
	InputStream is;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectimage);
		 NewCom= getIntent();
		typecomp = NewCom.getStringExtra("typecomp");
		phone= NewCom.getStringExtra("phone");
		name = NewCom.getStringExtra("name");
		city= NewCom.getStringExtra("city");
		extra = NewCom.getStringExtra("extra");


		Toast.makeText(getBaseContext(), city, Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(), typecomp, Toast.LENGTH_SHORT).show();

		StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(threadPolicy);
		//كود التلفون
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = telephonyManager.getDeviceId();
		//Toast.makeText(getBaseContext(), IMEI, Toast.LENGTH_SHORT).show();

/*
		//mac address
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		String macAddress = wInfo.getMacAddress();
*/

		Button takephoto = (Button) findViewById(R.id.takephotobtn);
		photo = (ImageView) findViewById(R.id.take);
		Button send = (Button) findViewById(R.id.send);
		Button change = (Button) findViewById(R.id.newcom);
		Button exit = (Button) findViewById(R.id.exit);


		//اكشن فتح الكاميرا
		takephoto.setOnClickListener(new OnClickListener()

		{
			@Override
			public void onClick(View view) {
				Intent a = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(a, 0);
			}
		});




		change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});




		exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(Selectimage.this, MainActivity.class);
				startActivity(i);
			}

		});

		//التحديث للموقع

		gps = new GPSTracker(this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			latitude = gps.getLatitude()+"";
			longitude = gps.getLongitude()+"";

			// \n is for new line
			Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();

		}   else{
		// can't get location
		// GPS or Network is not enabled
		// Ask user to enable GPS/network in settings
		gps.showSettingsAlert();
	}

			//كود الايميل

			Pattern gmailPattern = Patterns.EMAIL_ADDRESS;
			Account[] accounts = AccountManager.get(this).getAccounts();
			for (Account account : accounts) {
				if (gmailPattern.matcher(account.name).matches()) {
					gmail = account.name;
				}
			}

			Toast.makeText(this, gmail, Toast.LENGTH_SHORT).show();



		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view)
			{
				is=null;
				nV=new ArrayList<NameValuePair>(3);
				nV.add(new BasicNameValuePair("typecomp",typecomp));
				nV.add(new BasicNameValuePair("city",city));
				nV.add(new BasicNameValuePair("extra",extra));
				nV.add(new BasicNameValuePair("longitude",longitude));
				nV.add(new BasicNameValuePair("latitude",latitude));
				nV.add(new BasicNameValuePair("photo",encodedPhoto));

				nV.add(new BasicNameValuePair("imei", IMEI));
				nV.add(new BasicNameValuePair("name",name));
				nV.add(new BasicNameValuePair("email",gmail));
				nV.add(new BasicNameValuePair("phone",phone));


				try{
					//Default http
					HttpClient hC=new DefaultHttpClient();

					//Setting POST
					HttpPost hP=new HttpPost("http://192.168.43.116/android-webservice.php");


					hP.setEntity(new UrlEncodedFormEntity(nV));

					//Getting response
					HttpResponse r=hC.execute(hP);

					//Setting Entity
					HttpEntity en=r.getEntity();

					//Setting Content in input stream
					is=en.getContent();

					//Toast to check
					Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

					Intent i=new Intent(getApplicationContext(),Finish.class);
					startActivity(i);

				}

				catch (ClientProtocolException e)
				{
					String msg= "Error Connecting to DataBase \nPlease Check Connection";
					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
					Log.e("ClientProtocol","Log_tag");
					e.printStackTrace();
				}

				catch (IOException e)
				{
					String msg= "Error Connecting to DataBase \nPlease Check Connection";
					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
					Log.e("Log_tag","IOException");
					e.printStackTrace();
				}


			}
		});
	}


	//on create end


	//Camera Codes

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data){
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == 0) {
				if (resultCode == RESULT_OK) {
					Bitmap th = (Bitmap) data.getExtras().get("data");
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					th.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
					byte[] b = baos.toByteArray();
					 encodedPhoto = Base64.encodeToString(b, Base64.DEFAULT);
					photo.setImageBitmap(th);
				}
				else
				if (resultCode == RESULT_CANCELED)
				{
					Toast.makeText(getApplicationContext(),"Unable to get Image",Toast.LENGTH_LONG).show();
				}
			}
		}
}
