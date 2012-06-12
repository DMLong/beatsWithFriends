package com.link.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LinkActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button letterA = (Button) findViewById(R.id.bletterA);
		Button letterB = (Button) findViewById(R.id.bletterB);
		tv = (TextView) findViewById(R.id.textView1);
		letterA.setOnClickListener(this);
		letterB.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		Intent ourIntent = new Intent("gogo");
		Button clicked = (Button) findViewById(arg0.getId());
		ourIntent.putExtra("letter", clicked.getText().toString());
		startActivity(ourIntent);
	}
}