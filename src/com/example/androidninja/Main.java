package com.example.androidninja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void about(View v)
    {
    	//Intent i = new Intent(this,About.class);
    	//startActivity(i);
    	
    }
	
	public void settings(View v)
    {
    	Intent i = new Intent(this,Settings.class);
    	startActivity(i);
    	
    }
	
}
