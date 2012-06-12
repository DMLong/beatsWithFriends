package com.colin.wielga;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class RedlineActivity extends Activity implements OnTouchListener, OnClickListener, SeekBar.OnSeekBarChangeListener {
	/** Called when the activity is first created. */

	Animation ourView;
	RelativeLayout.LayoutParams paramsd;
	Button faster,slower,black,green,red,clb;
	SeekBar setVolume;
	static float masterVolume=1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ourView = (Animation) findViewById(R.id.lineBox);
		faster = (Button) findViewById(R.id.bfaster);
		slower = (Button) findViewById(R.id.bslower);	
		black = (Button) findViewById(R.id.bblack);	
		red = (Button) findViewById(R.id.bred);	
		green = (Button) findViewById(R.id.bgreen);	
		clb = (Button) findViewById(R.id.bclb);
		setVolume = (SeekBar) findViewById(R.id.sbMasterVolume);
		
		//Because the default color is red, that button starts bolded
		red.setTypeface(null, Typeface.BOLD);
		
		faster.setOnClickListener(this);
		slower.setOnClickListener(this);
		black.setOnClickListener(this);
		red.setOnClickListener(this);
		green.setOnClickListener(this);
		ourView.setOnTouchListener(this);
		clb.setOnClickListener(this);
		setVolume.setOnSeekBarChangeListener(this);
		setVolume.setProgress(99);
		}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourView.resume();

	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
				ourView.addPoint(event.getX(), event.getY());
				//String toshow =  (event.getX() / 8) + " " + (event.getY()/8);
				//Toast.makeText(RedlineActivity.this, toshow, Toast.LENGTH_SHORT).show();
				break;
		}
		return false;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()){
			case R.id.bfaster:
				ourView.adjustspeed(1);
				break;
			case R.id.bslower:
				ourView.adjustspeed(-1);
				break;
			case R.id.bblack:
				ourView.setColor("black");
				black.setTypeface(null, Typeface.BOLD);
				red.setTypeface(null, Typeface.NORMAL);
				green.setTypeface(null, Typeface.NORMAL);
				break;
			case R.id.bgreen:
				ourView.setColor("green");
				black.setTypeface(null, Typeface.NORMAL);
				red.setTypeface(null, Typeface.NORMAL);
				green.setTypeface(null, Typeface.BOLD);
				break;
			case R.id.bred:
				ourView.setColor("red");
				black.setTypeface(null, Typeface.NORMAL);
				red.setTypeface(null, Typeface.BOLD);
				green.setTypeface(null, Typeface.NORMAL);
				break;	
			case R.id.bclb:
				Toast.makeText(RedlineActivity.this, "Chris loves buttons!", Toast.LENGTH_SHORT).show();
				break;					
		}
	}

	public static float getVolume(){
		return masterVolume;
	}
	
	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		masterVolume = (float)((float) arg1)/100;
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
}
