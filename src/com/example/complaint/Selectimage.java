package com.example.complaint;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Selectimage extends Activity {
	Button takephoto;
	ImageView photo;
	File imagefile;
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
		takephoto.setOnClickListener(new takephotoClicker());


		send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Selectimage.this,Finish.class);
				startActivity(i);
				finish();
				
			}
			
		});

		change.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Selectimage.this,Newcomplaint.class);
				startActivity(i);
				
				
			}


			
		});


		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {




			}

		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==CAM_REQUEST){
			Bitmap th=(Bitmap) data.getExtras().get("data");

			photo.setImageBitmap(th);
			imagefile = new File("/galary/Complaint/test_image.jpg");
			th= BitmapFactory.decodeFile(imagefile.getPath());


		}


	}
	class takephotoClicker implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraintent,CAM_REQUEST);

	}
	}

}
