package com.segal.adam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLLiteExample extends Activity implements OnClickListener{
	
	Button sqlUpdate, sqlView, sqlGetInfo, sqlEditEntry, sqlDeleteEntry;
	EditText sqlName,sqlHotness,sqlRowId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlliteexample);
		
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLOpenView);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHottness);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlEditEntry = (Button) findViewById(R.id.bEditEntry);
		sqlDeleteEntry = (Button) findViewById(R.id.bDeleteEntry);
		sqlRowId = (EditText) findViewById(R.id.etRowId);
		sqlView = (Button) findViewById(R.id.bSQLOpenView);
		
		sqlDeleteEntry.setOnClickListener(this);
		sqlEditEntry.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String s;
		long l;
		HotorNot hon;
		String name, hotness;
		switch (arg0.getId()){
		case R.id.bSQLOpenView:
			Intent i = new Intent("com.segal.adam.LinkTo");
			startActivity(i);
			break;
		case R.id.bSQLUpdate:
			boolean didItWork = true;
			Dialog d = new Dialog(this);
			name = sqlName.getText().toString();
			hotness = sqlHotness.getText().toString();
			try{	
				HotorNot entry = new HotorNot(SQLLiteExample.this);
				entry.open();
				entry.createEntry(name,hotness);
				entry.close();
			} catch (Exception e){
				didItWork = false;
				String error = e.toString();
				d.setTitle("fail");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}finally{

				if (didItWork){
					d.setTitle("Stored");
					TextView tv = new TextView(this);
					tv.setText("Name: "+ name +"\n" + "Hotness: " + hotness);
					d.setContentView(tv);
					d.show();
					sqlHotness.setText("");
					sqlName.setText("");
					}
			}
			
			break;
		case R.id.bGetInfo:
			s = sqlRowId.getText().toString();
			l = Long.parseLong(s);
			hon = new HotorNot(this);
			hon.open();
			String returnedname = hon.getName(l);
			String returnedhotness = hon.getHotness(l);
			hon.close();
			
			sqlName.setText(returnedname);
			sqlHotness.setText(returnedhotness);
			
			break;
		case R.id.bEditEntry:
			s = sqlRowId.getText().toString();
			l = Long.parseLong(s);
			name = sqlName.getText().toString();
			hotness = sqlHotness.getText().toString();
			
			hon = new HotorNot(this);
			hon.open();
			hon.updateEntrey(l,name,hotness);
			hon.close();
			
			break;
		case R.id.bDeleteEntry:
			
			break;
		}	
	}
}