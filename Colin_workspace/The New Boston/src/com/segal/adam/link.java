package com.segal.adam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class link extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linktostart);
		Button blink = (Button) findViewById(R.id.bGoToLinkTo);
		
		blink.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		if (arg0.getId() == R.id.bGoToLinkTo){
			Intent i = new Intent("icanwritewhateveriwant");
			startActivity(i);
		}
	}
}
