package com.example.androidninja;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("DrawAllocation")
public class Game extends Activity {
	 MediaPlayer gamesound,jump,catchninja,mainsound;
	 gameloop gameLoopThread;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new GameView(this));		
	}
	
	@SuppressLint("DrawAllocation")
	public class GameView extends SurfaceView{
	      Bitmap bmp,pause;
	      Bitmap background,background2,background3;
	      Bitmap sharukin,note1,powerimg,note2;
	      Bitmap run1;
	      Bitmap run2;
	      Bitmap run3;
	      Bitmap ninja;
	      Bitmap off,on;
	      Bitmap exit;
	     
	      private SurfaceHolder holder;
	      private int x = 0,z=0,delay=0,getx,gety,sound=1;
	      int show=0,sx,sy;
	      int cspeed=0,kspeed=0,gameover=0;
	      int score=0,health=100,reset=0;
	      int pausecount=0,volume,power=0,powerrun=0,shieldrun=0;
	      
	      
	      @SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		public GameView(Context context) 
	      {
	    	  super(context);
	    	  
	    	  gameLoopThread = new gameloop(this);
	    	  holder = getHolder();

	             holder.addCallback(new SurfaceHolder.Callback() {
				//@SuppressWarnings("deprecation")
				@Override
                public void surfaceDestroyed(SurfaceHolder holder) 
                {
					 //for stoping the game
					gameLoopThread.setRunning(false);
					gameLoopThread.getThreadGroup().interrupt();
	             }
                
                @SuppressLint("WrongCall")
				@Override
                public void surfaceCreated(SurfaceHolder holder) 
                {
                	  gameLoopThread.setRunning(true);
                	  gameLoopThread.start();
                	  
                	  
	             }
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format,int width, int height) 
	                    {
	                    }
	             });
	             
	             //getting the screen size 
	             Display display = getWindowManager().getDefaultDisplay();
					
					sx = display.getWidth();
					sy = display.getHeight();;
					cspeed=sx/2;
					kspeed=sx/2;
					powerrun=(3*sx/4);
					shieldrun=sx/8;
	    	  background = BitmapFactory.decodeResource(getResources(), R.drawable.back);
	    	  background2 = BitmapFactory.decodeResource(getResources(), R.drawable.back2);
	    	  background3 = BitmapFactory.decodeResource(getResources(), R.drawable.back3);
	    	  run1=BitmapFactory.decodeResource(getResources(), R.drawable.n);
	    	  run2=BitmapFactory.decodeResource(getResources(), R.drawable.n4);
	    	  run3=BitmapFactory.decodeResource(getResources(), R.drawable.n2);
	    	  ninja=BitmapFactory.decodeResource(getResources(), R.drawable.ninja);
	    	  sharukin=BitmapFactory.decodeResource(getResources(), R.drawable.sharukin);
	    	  note1=BitmapFactory.decodeResource(getResources(), R.drawable.note1);
	    	  pause=BitmapFactory.decodeResource(getResources(), R.drawable.pause);
	    	  powerimg=BitmapFactory.decodeResource(getResources(), R.drawable.power);
	    	  note2=BitmapFactory.decodeResource(getResources(), R.drawable.note2);	  
	    	  pause=Bitmap.createScaledBitmap(pause, 25,25, true);
	    	  powerimg=Bitmap.createScaledBitmap(powerimg, 25,25, true);
	    	  note2=Bitmap.createScaledBitmap(note2, sx,sy, true);
	
	    	  background=Bitmap.createScaledBitmap(background, 2*sx,sy, true);
	    	  background2=Bitmap.createScaledBitmap(background2, 2*sx,sy, true);
	    	  background3=Bitmap.createScaledBitmap(background3, 2*sx,sy, true);
	    	  //health dec
	    	  note1=Bitmap.createScaledBitmap(note1, sx,sy, true);
	    	  
	    	  gamesound=MediaPlayer.create(Game.this,R.raw.game);
	    	  jump=MediaPlayer.create(Game.this,R.raw.jump);
	    	  catchninja=MediaPlayer.create(Game.this,R.raw.ninjatake);
	    	  mainsound=MediaPlayer.create(Game.this,R.raw.mainsound);
	      }
	      
	      // on touch method
	      
	      @Override
			public boolean onTouchEvent(MotionEvent event) {
				
	    	  	if(event.getAction()==MotionEvent.ACTION_DOWN)
	    	  	{
	    	  		show=1;
	    	  		
	    	  		getx=(int) event.getX();
	    	  		gety=(int) event.getY();
	    	  		// restart game
	    	  		if(getx>91&&gety<25)
	    	  		{
	    	  			if(health<=0)
	    	  			{
	    	  				gameLoopThread.setPause(0);
							health=100;
							score=0;
	    	  			
	    	  			}
	    	  		}
	    	  		//pause game
	    	  		if(getx>(sx-25)&&gety<25&&pausecount==0)
	    	  		{
	    	  			
	    	  			gameLoopThread.setPause(1);
	    	  			gamesound.stop();
	    	  			mainsound.start();
	    	  			pausecount=1;
	    	  		}
	    	  		else if(getx>(sx-25)&&gety<25&&pausecount==1)
	    	  		{
	    	  			gameLoopThread.setPause(0);
	    	  			gamesound.start();
	    	  			mainsound.start();
	    	  			pausecount=0;
	    	  		}
	    	  	}
		  		
		  		return true;
			}
			
	      
	        @SuppressLint("WrongCall")
			@Override
		      protected void onDraw(Canvas canvas) 
		      {
	        	
	        	
		   
	    	  //volume 
	        	SharedPreferences pref = getApplicationContext().getSharedPreferences("higher", MODE_PRIVATE);
	      //  	Editor editor = pref.edit();
	        	volume=pref.getInt("vloume", 0);
	        	sound=pref.getInt("vloume", 0);
	        	if(volume==0)
	        	{
	        		sound=0;
	        	}
	  	    
	    	  	canvas.drawColor(Color.BLACK);
	    	  	
	    	  	//background moving
		    	z=z-5;
		    	if(z==-sx)
		    	{
		    		z=0;
		    		if(score<100)
		    		{
		    			canvas.drawBitmap(background, z, 0, null);
		    		}
		    		else if(score>=100 && score <200)
		    		{
		    			canvas.drawBitmap(background2, z, 0, null);		    			
		    		}
		    		else
		    		{
		    			canvas.drawBitmap(background3, z, 0, null);			    			
		    		
		    		}
		    		
		    	}
		    	else
		    	{
		    		if(score<100)
		    		{
		    			canvas.drawBitmap(background, z, 0, null);
		    		}
		    		else if(score>=100 && score <200)
		    		{
		    			canvas.drawBitmap(background2, z, 0, null);		    			
		    		}
		    		else
		    		{
		    			canvas.drawBitmap(background3, z, 0, null);			    			
		    		
		    		}
		    	}
		    	
		    	//running player 
		    	
		    		 x+=10;
		    		 if(x==20)
		    		 {
		    			 x=5;
		    		 }
		    		 
		    		  if(show==0)
		    		  {
		    			  if(x%2==0)
		    			  {
		    				  canvas.drawBitmap(run1, sx/16, 15*sy/18, null);
		    				 
		    			  }
		    			  else 
		    			  {
		    				  canvas.drawBitmap(run3, sx/16, 15*sy/18, null);
		    				 
		    			  }
		    			  //sharukin hit
	    				  if(kspeed==20)
	    				  {
	    					  kspeed=sx;
	    					  health-=25;
	    					  canvas.drawBitmap(note1, 0, 0, null);
	    				  }
	    				  //power take
	    				  if(powerrun==20)
	     				  	{
	    					  powerrun=3*sx;
	     					  health+=25;
	     					  canvas.drawBitmap(note2, 0, 0, null);
	     				  	}
		    		  }
		    		  
		    		  
		    		  // for jump
			    	 if(show==1)
			    	 {
			    		 if(sound==1)
			    		 {
			    		 jump.start();
			    		 }
			    		 
		    				  canvas.drawBitmap(run2, sx/16,  18*sy/25, null);
		    				  //score
		    				  if(cspeed<=sx/8&&cspeed>=sx/16)
		    				  {
		    					  if(sound==1)
		 			    		 {
		    						  catchninja.start();
		    						  
		 			    		 }
		    					  cspeed=sx/2;
		    					  score+=10;
		    					
		    				  }
		    				 
		    			 
		    				
			    		// jump-hold
			    		 delay+=1;
			    		 if(delay==3)
			    		 {
			    		 show=0;
			    		 delay=0;
			    		 }
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	//power
		    		 powerrun=powerrun-10;
		    		 canvas.drawBitmap(powerimg, powerrun, 15*sy/18, null);
		    		 
		    		 if(powerrun<0)
		    		 {
		    			 powerrun=3*sx/4;
		    		 }
		    		
		    		
		    		  //for ninja catch
		    		  cspeed=cspeed-5;
				    	if(cspeed==-sx/2)
				    	{
				    		cspeed=sx/2;
				    		canvas.drawBitmap(ninja, cspeed, 3*sy/4, null);
				   
				    	}
				    	else
				    	{
				    		canvas.drawBitmap(ninja, cspeed, 3*sy/4, null);	
				    	}
				    	
				    	//sharukin
			    		 kspeed=kspeed-20;
			    		 canvas.drawBitmap(sharukin, kspeed, 15*sy/18, null);
			    		 if(kspeed<0)
			    		 {
			    			 kspeed=sx;
			    		 }
			    		 
			    		 
				    	//score
				    	 	Paint paint = new Paint();
				    	    paint.setColor(Color.BLUE);
				    	    paint.setAntiAlias(true);
				    	    paint.setFakeBoldText(true);
				    	    paint.setTextSize(15);
				    	    paint.setTextAlign(Align.LEFT);
				    	    canvas.drawText("Score :"+score, 3*sx/4, 20, paint);
		    		  	//exit
				       	  if(sound==1)
				    		  {
				    		  gamesound.start();
				    		  gamesound.setLooping(true);
				    		  }
					    	  else
					    	  {
					    		 gamesound.stop();
					    		 mainsound.stop();
					    	  }
		    		  //health
					    Paint myPaint = new Paint();
					     myPaint.setColor(Color.RED);
					     myPaint.setStrokeWidth(10);
					     myPaint.setAntiAlias(true);
					     myPaint.setFakeBoldText(true);
					    canvas.drawText("Health :"+health, 0, (sy/8)-5, myPaint);
					    canvas.drawRect(0, sy/8, health, sy/8+10, myPaint);
					    
					  //game over
					    if(health<=0)
					    {
					    	Intent i=new Intent(this.getContext(),MainActivity.class);
					    	
							startActivity(i);
					}
					   // restart
					    
						if(reset==1)
						{
							gameLoopThread.setPause(0);
							health=100;
							score=0;
						}
						
						canvas.drawBitmap(pause, (sx-25), 0, null);
						
		    	  }
	        
		    
		      }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
	

}
