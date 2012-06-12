package com.segal.adam;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener,
		OnCheckedChangeListener {

	TextView question, test;
	RadioGroup answers;
	// RadioButton crazy, sexy, both;
	Button enter;
	String gotBread, setData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		initialize();
		
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et = getData.getString("name", "Travis is...");
		String values = getData.getString("list", "4");
		if(values.contentEquals("1")){
			question.setText(et);
		}
		
	//	Bundle gotBasket = getIntent().getExtras();
	//	gotBread = gotBasket.getString("key");
	//	question.setText(gotBread);
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvTest);
		answers = (RadioGroup) findViewById (R.id.rgAnswers);
		// crazy = (RadioButton) findViewById (R.id.rCrazy);
		// sexy = (RadioButton) findViewById (R.id.rSexy);
		// both = (RadioButton) findViewById (R.id.rBoth);
		enter = (Button) findViewById(R.id.bReturn);
		enter.setOnClickListener(this);
		answers.setOnCheckedChangeListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent person = new Intent();
		Bundle backpack = new Bundle();
		backpack.putString("answer", setData);
		person.putExtras(backpack);
		setResult(RESULT_OK, person);
		finish();
	}

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rCrazy:
			setData = "Probably right!";
			break;
		case R.id.rSexy:
			setData = "Definitely right!";
			break;
		case R.id.rBoth:
			setData = "Spot on!";
			break;
		}
		test.setText(setData);
	}

}
