package com.xiazhi.mydialog;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	public static MainActivity mainActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		mainActivity = this;
		findViewById(R.id.start_dialog).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						new MyDialog(MainActivity.this).showDialog();
					}
				});

		findViewById(R.id.stop_dialog).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						new MyDialog(MainActivity.this).dimissDialog();
					}
				});
	}

}
