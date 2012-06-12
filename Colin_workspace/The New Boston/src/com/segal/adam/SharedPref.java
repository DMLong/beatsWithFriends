package com.segal.adam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPref extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	public static String fileName = "MySharedString";
	SharedPreferences someData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);
		setupVariables();
		someData = getSharedPreferences(fileName , 0);
	}

	private void setupVariables() {
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		dataResults = (TextView) findViewById(R.id.tvloadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);	
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.bLoad:
			someData = getSharedPreferences(fileName,0);
			String dataReturn = someData.getString("sharedString","Couldn't load Data");
			dataResults.setText(dataReturn);			
			break;
		case R.id.bSave:
			String stringData = sharedData.getText().toString();
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("sharedString" , stringData);
			editor.commit();
			break;
		}		
	}
}