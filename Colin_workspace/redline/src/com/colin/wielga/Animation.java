package com.colin.wielga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class Animation extends SurfaceView implements Runnable {

	Bitmap line;
	Bitmap point_black, point_red, point_green;
	float x = 0;
	SurfaceHolder ourHolder;
	Thread ourThread = null;
	Boolean isRunning = false;
	int speed = 1;
	Grid noteGrid;
	SoundPool sp;
	int buhlom = 0;
	int sblack = 0;
	int sgreen = 0;
	String type = "red";

	public Animation(Context context) {
		super(context);
		init(context);
	}

	public Animation(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public Animation(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public void init(Context context){
		line = BitmapFactory.decodeResource(getResources(), R.drawable.rline);
		ourHolder = getHolder();
		point_black = BitmapFactory.decodeResource(getResources(),
				R.drawable.point);
		point_red = BitmapFactory.decodeResource(getResources(),
				R.drawable.point_red);
		point_green = BitmapFactory.decodeResource(getResources(),
				R.drawable.point_green);
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		buhlom = sp.load(context, R.raw.buhlom, 1);
		sblack = sp.load(context, R.raw.black, 1);
		sgreen = sp.load(context, R.raw.green, 1);
//		noteGrid = new Grid((int) this.getWidth() / 8,
//				(int) this.getHeight() / 8);
		noteGrid = new Grid(60, 20);
	}

	public void pause() {
		isRunning = false;
		while (true) {
			try {
				ourThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		ourThread = null;
	}

	public void resume() {
		isRunning = true;
		ourThread = new Thread(this);
		ourThread.start();
	}

	public void adjustspeed(int delta) {
		speed = speed + delta;
	}

	public void setColor(String s) {
		type = s;
	}

	public void addPoint(float x, float y) {
		Note noteToAdd = new Note((int) (x / 8), (int) (y / 8), type);
		noteGrid.addNote(noteToAdd);
	}

	public void run() {
		// TODO Auto-generated method stub
		while (isRunning) {
			if (!ourHolder.getSurface().isValid()) {
				continue;
			}
			Canvas canvas = ourHolder.lockCanvas();

			canvas.drawColor(Color.WHITE);
			canvas.drawBitmap(line, x, 0, null);

			for (int i = 0; i < noteGrid.getWidth(); i++) {
				for (int j = 0; j < noteGrid.getHeight(); j++) {
					if (noteGrid.getNote(i, j) != null) {
						Note note = noteGrid.getNote(i, j);
						String type = note.getType();
						if (type.equals("green")) {
							canvas.drawBitmap(point_green, note.getX() * 8,
									note.getY() * 8, null);
						} else if (type.equals("red")) {
							canvas.drawBitmap(point_red, note.getX() * 8,
									note.getY() * 8, null);
						} else if (type.equals("black")) {
							canvas.drawBitmap(point_black, note.getX() * 8,
									note.getY() * 8, null);
						}
					}
				}
			}
			// if the line is before the beginning, move it to the end
			if (x < -2) {
				x = canvas.getWidth();
			} else if (x < canvas.getWidth() + 1) {
				if ((int) (x / 8) != (int) ((x + speed) / 8)) {
					for (int i = (int) (x / 8); i < (x + speed) / 8
							&& i < noteGrid.getWidth(); i++) {
						for (int j = 0; j < noteGrid.getHeight(); j++) {
							if (noteGrid.getNote(i, j) != null) {
								String type = noteGrid.getNote(i, j).getType();
								float volume = (float) RedlineActivity.getVolume()*(20 - noteGrid.getNote(i, j).getY())/20;
								if (type.equals("red")){
									sp.play(buhlom, volume, volume, 0, 0, 1);
								}else if (type.equals("black")){
									sp.play(sblack, volume, volume, 0, 0, 1);
								}else if (type.equals("green")){
									sp.play(sgreen, volume, volume, 0, 0, 1);
								}
							}
						}
					}
				}
				x += speed;
			} else {
				x = 0;
			}

			ourHolder.unlockCanvasAndPost(canvas);
		}
	}
}