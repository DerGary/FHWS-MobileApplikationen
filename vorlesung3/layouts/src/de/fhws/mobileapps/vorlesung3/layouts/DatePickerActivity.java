package de.fhws.mobileapps.vorlesung3.layouts;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerActivity extends Activity implements OnDateChangedListener{

	private static final String TAG = "Layouts";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);
		
		DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker1);
		datePicker.init(2013, 3, 8, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_picker, menu);
		return true;
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Log.d( TAG, "new date: " + dayOfMonth + "." + monthOfYear + "." + year );
	}

	public void buttonClicked( View view )
	{
		Intent intent = new Intent( this, ToastDialogActivity.class );
		startActivity( intent );
	}
	
}
