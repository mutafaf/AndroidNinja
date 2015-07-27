package com.example.androidninja;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
	Animation myAnimation;
	 TextView myText;
	 
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);	
		
		
		myText = (TextView)findViewById(R.id.textView1);
        
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.myanimation);
		myText.startAnimation(myAnimation);
		
		MediaPlayer mainsound =MediaPlayer.create(this,R.raw.mainsound);
		mainsound.start();

	}

	public void play(View v)
	{
		Intent i=new Intent(this,Game.class);
	//	mainsound.stop();
		startActivity(i);
	}
	
	public void setting(View v)
	{
		Intent i=new Intent(this,Setting.class);
	//	mainsound.stop();
		startActivity(i);
	}
	
	public void highscore(View v)
	{
		Toast.makeText(getApplicationContext(), "In Progress", 
				   Toast.LENGTH_LONG).show();
	}
	
	
	public void aboutus(View v)
	{
		Intent i=new Intent(this,AboutusActivity.class);
	//	mainsound.stop();
		startActivity(i);
	}
	
	
	public void exit(View v)
	{
	//	mainsound.stop();
		System.exit(0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
	

}