package com.link.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Linkto extends Activity implements OnClickListener{
	String letter = null;
	TextView what;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.linkto);
        Button gobackA = (Button) findViewById(R.id.bGoBackA);
        Button gobackB = (Button) findViewById(R.id.bGoBackB);
        what = (TextView) findViewById(R.id.textView1);
                
        gobackA.setOnClickListener(this);
        gobackB.setOnClickListener(this);
        
        Bundle extras = getIntent().getExtras(); 
        if(extras !=null)
        {
        letter = extras.getString("letter");
        }
        what.setText(letter);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bGoBackA:
			this.finish();
			break;
		case R.id.bGoBackB:
			this.finish();
			break;
		}
	}
}
