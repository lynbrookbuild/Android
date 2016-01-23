package com.example.emoticongenerator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	Button change;

	ImageView emoticon;

	int[] emoticons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		change = (Button) findViewById(R.id.change);
		emoticon = (ImageView) findViewById(R.id.emoticon);
		emoticons = new int[] { R.drawable.cry, R.drawable.help, R.drawable.hungry, R.drawable.silly };
		change.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				emoticon.setImageResource(emoticons[(int) (Math.random() * 3)]);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
