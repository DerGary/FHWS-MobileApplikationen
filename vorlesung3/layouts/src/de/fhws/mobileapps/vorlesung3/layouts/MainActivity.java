package de.fhws.mobileapps.vorlesung3.layouts;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class MainActivity extends Activity implements OnSeekBarChangeListener {

	private static final String TAG = "Layouts";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar1);
		seekbar.setOnSeekBarChangeListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void buttonPressed( View view )
	{
		Log.d(TAG, "Button pressed" );
		Intent intent = new Intent( this, FrameActivity.class );
		startActivity(intent);
	}
	
	public void onRadioButtonClicked( View view )
	{
		boolean checked = ((RadioButton)view).isChecked();
		
		switch( view.getId() )
		{
		case R.id.radio0:
			Log.d(TAG,"Radio left checked: " + checked );
			break;
		case R.id.radio1:
			Log.d(TAG,"Radio right checked: " + checked );
			break;
		}
	}
	
	public void checkBoxClicked( View view )
	{
		CheckBox cb = (CheckBox)view;
		
		switch( view.getId() )
		{
		case R.id.checkBox1:
			Log.d(TAG, "Left: "  + cb.isChecked() );
			break;
		case R.id.checkBox2:
			Log.d(TAG, "Right: " + cb.isChecked() );
			break;
		}
	}
	
	public void onSwitchClicked( View view )
	{
		Switch sw = (Switch)view;
		Log.d(TAG, "Switch: " + sw.isChecked() );
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		Log.d(TAG, "progress changed to " + progress );
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		Log.d(TAG, "on start tracking" );
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		Log.d(TAG, "on stop tracking, final value: " + seekBar.getProgress() );
	}

}
