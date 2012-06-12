package com.segal.adam;

import android.app.Activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener{

	SoundPool sp;
	int explosion = 0;
	MediaPlayer mp;
	boolean longclick = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);
		setContentView(v);
		sp=new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.buhlom, 1);
		mp = MediaPlayer.create(this, R.raw.backgroundmusic);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
	if (explosion != 0 && longclick == false)
		sp.play(explosion, 1, 1, 0, 0, 1);		
	longclick = false;
	}

	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		mp.start();
		longclick = true;
		return false;
	}	
}
