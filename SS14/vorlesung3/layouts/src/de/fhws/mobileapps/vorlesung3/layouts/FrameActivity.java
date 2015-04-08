package de.fhws.mobileapps.vorlesung3.layouts;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FrameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.frame, menu);
		return true;
	}
	
	public void onImageClicked( View view )
	{
		Intent intent = new Intent( this, SpinnerActivity.class );
		startActivity(intent);
	}

}
