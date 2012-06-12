package com.segal.adam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	FileOutputStream fos;
	String FILENAME = "InternalString";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();		
	}
	
	private void setupVariables() {
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		dataResults = (TextView) findViewById(R.id.tvloadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);	
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public void onClick(View arg0) {
		switch (arg0.getId()){
		case R.id.bLoad:
			new LoadSomeStuff().execute(FILENAME);
			break;
		case R.id.bSave:
			String data = sharedData.getText().toString();
			/* saving data via File
			File f = new File(FILENAME);
			try {
				fos = new FileOutputStream(f);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			//saving data with write
			try {
				fos = openFileOutput(FILENAME,Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}		
	}
	
	public class LoadSomeStuff extends AsyncTask<String, Integer,String>{
		
		ProgressDialog dialog;
		
		protected void  onPreExecute(){
			//example of setting up something
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String Collected =null;
			FileInputStream fis = null;
			
			for(int i= 0; i < 20 ; i++){
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis = openFileInput(FILENAME);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1){
					Collected = new String(dataArray);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return Collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}
		
		protected void onProgressUpdate(Integer...progress){
			dialog.incrementSecondaryProgressBy(progress[0]);
		}
		
		protected void onPostExecute(String result){
			dataResults.setText(result);
		}
	
	}	
}
