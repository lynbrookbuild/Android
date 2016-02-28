package com.example.todo;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	EditText enterTodo;

	Button add;

	ListView list;

	DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new DBAdapter(this);
		db.open();
		enterTodo = (EditText) findViewById(R.id.enterTodo);
		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (enterTodo.getText().toString() != null) {
					db.insertRow(enterTodo.getText().toString());
					enterTodo.setText("");
					populateListView();
				} else {
					Toast.makeText(MainActivity.this, "Text field empty", Toast.LENGTH_SHORT).show();
				}
			}
		});
		list = (ListView) findViewById(R.id.list);
		populateListView();
	}

	private void populateListView() {
		ArrayList<String> todos = new ArrayList<String>();
		Cursor cursor = db.getAllRows();
		if (cursor != null) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				todos.add(cursor.getString(DBAdapter.COL_TODO));
				cursor.moveToNext();
			}
		}
		if (todos != null) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
					android.R.layout.simple_list_item_1, todos);
			list.setAdapter(adapter);
		}
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		db.close();
	}
}
