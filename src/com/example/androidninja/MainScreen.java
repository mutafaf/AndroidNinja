package com.example.androidninja;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;



public class MainScreen extends Activity {
	 Animation myAnimation;
	 TextView myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_screen);
       //myText = (TextView)findViewById(R.id.mytext);
        
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.myanimation);
		myText.startAnimation(myAnimation);
                   
        
    }

    public void main(View v)
    {
    	Intent i = new Intent(this,Main.class);
    	startActivity(i);
    	
    }
}
