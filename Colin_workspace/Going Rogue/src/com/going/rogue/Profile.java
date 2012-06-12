package com.going.rogue;

import com.going.rogue.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends Activity implements OnClickListener{
	String pokemon=null;
	TextView stat1,stat2,stat3,stat4,name;
	Button wiki , goback, map, crazy ;
	ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		stat1 = (TextView) findViewById(R.id.tvstat1);
		stat2 = (TextView) findViewById(R.id.tvstat2);
		stat3 = (TextView) findViewById(R.id.tvstat3);
		stat4 = (TextView) findViewById(R.id.tvstat4);
		name = (TextView) findViewById(R.id.tvname);
		
		image = (ImageView) findViewById(R.id.tvname);
		
		wiki = (Button) findViewById(R.id.bgowiki);
		goback = (Button) findViewById(R.id.bgoback);
		map = (Button) findViewById(R.id.bmap);
		crazy = (Button) findViewById(R.id.bcray);
		
		wiki.setOnClickListener(this);
		goback.setOnClickListener(this);
		map.setOnClickListener(this);
		crazy.setOnClickListener(this);
		
		/*switch (pokemon){
		case "zapdos":
			break;
		case "jigglypuff":
			break;
		case "snorlax":
			break;
		case "zealot":
			break;
		case "prowlithe":
			break;
		case "ditto":
			break;
		}*/
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		case R.id.bgowiki:
			break;
		case R.id.bgoback:
			break;
		case R.id.bmap:
			break;
		case R.id.bcray:
			break;
		}
		
	}
}
