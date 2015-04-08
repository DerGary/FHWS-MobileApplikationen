package de.fhws.mobileapps.vorlesung2.changeorientation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private int clickCounter = 0;
	
	public MainActivity() {
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		switch (display.getRotation()) {
	    case Surface.ROTATION_0:
	    case Surface.ROTATION_180:
	    	setContentView(R.layout.activity_main);
	        break;
	    case Surface.ROTATION_270:
	    case Surface.ROTATION_90:
	    	setContentView(R.layout.activity_main_landscape);
	        break;
		}
		
		TextView tv = (TextView)findViewById(R.id.hello );
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener( this );

		if( savedInstanceState != null )
		{
			this.clickCounter = savedInstanceState.getInt("counter");
			showNumberOfClicks();
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("counter", this.clickCounter );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		this.clickCounter++;
		showNumberOfClicks();
	}
	
	private void showNumberOfClicks()
	{
		String s = getString(R.string.pressed_times, this.clickCounter );
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText( s );
	}
}
