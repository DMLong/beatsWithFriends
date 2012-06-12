package com.segal.adam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener{
	
	private TextView canWrite, canRead;
	private String state;
	boolean canW, canR;
	Spinner spinner;
	String[] paths = {"music","Pictures","Downloads"};
	File path = null;
	File file = null;
	EditText saveFile;
	Button confrim, save;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite = (TextView) findViewById(R.id.tvcanwrite);
		canRead = (TextView) findViewById(R.id.tvcanread);
		confrim = (Button) findViewById(R.id.bConfrimSaveAs);
		save = (Button) findViewById(R.id.bSaveFile);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confrim.setOnClickListener(this);
		save.setOnClickListener(this);
		canWrite.setText("can write: ?");
		canRead.setText("can write: ?");
		state = Environment.getExternalStorageState();
		checkState();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this,android.R.layout.simple_spinner_item, paths);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}
	
	public void checkState(){
		if (state.equals(Environment.MEDIA_MOUNTED)){
			//we can read and write
			canWrite.setText("can write:True");
			canRead.setText("can read:True");
			canW =canR = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
			//read but can't write
			canWrite.setText("can write:False");
			canRead.setText("can read:True");
			canW =false;
			canR = true;
		}else{
			//read but can't write
			canWrite.setText("can write:False");
			canRead.setText("can read:False");	
			canW= canR = false;
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.bConfrimSaveAs:
			save.setVisibility(View.VISIBLE);
			break;
		case R.id.bSaveFile:
			String f = saveFile.getText().toString();
			file = new File(path,f + ".png");
			checkState();
			if (canW == canR == true){
				path.mkdirs();
				try {
					InputStream is = getResources().openRawResource(R.drawable.pressedbutton);
					OutputStream os = new FileOutputStream(file);
					byte[] data = new byte[is.available()]; 
					is.read(data);
					os.write(data);
					is.close();
					os.close();
					
					Toast t = Toast.makeText(ExternalData.this, "saved", Toast.LENGTH_LONG);
					t.show();
					
					//this bit updates the file system for the user
					MediaScannerConnection.scanFile(ExternalData.this , new String[] {file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
						
						public void onScanCompleted(String path, Uri uri) {
							Toast t = Toast.makeText(ExternalData.this, "complete", Toast.LENGTH_SHORT);
							t.show();
						}
					});
					
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			save.setVisibility(View.INVISIBLE);
			break;
		}
		
	}
}