package de.fhws.mobileapps.vorlesung3.layouts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends Activity implements OnItemSelectedListener {

	private static final String TAG = "Layouts";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		List<String> values = Arrays.asList( "Januar", "Februar", "MŠrz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember" );
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values );
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setOnItemSelectedListener(this);
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.spinner, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d( TAG, "on item selected: " + position );
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		Log.d( TAG, "nothing selected" );
	}
	
	public void buttonClicked( View view )
	{
		Intent intent = new Intent( this, DatePickerActivity.class );
		startActivity( intent );
	}

}
