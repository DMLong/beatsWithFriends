package com.colin.wielga;

import android.media.AudioManager;
import android.media.SoundPool;

public class Note {
	int x;
	int y;
	String type;
	int sound;
	SoundPool sp;
	
	public Note(){
		x = 0;
		y = 0;
		type = "nothing";	
	}
	
	public Note(int newX, int newY, String newType){
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		x = newX;
		y = newY;
		type = newType;
//		if (type.equals("black")){
//			sound = sp.load(context, R.raw.buhlom, 1);
//		}else if (type.equals("red")) {
//			
//		} else if (type.equals("green")) {
//			
//		}
	}
	
	public int getY() {
		return y;
	}

	public int getX(){
		return x;
	}
	
	public String getType(){
		return type;
	}

	public int getSound() {
		// TODO Auto-generated method stub
		return 0;
	}
}
