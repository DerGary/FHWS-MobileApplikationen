package de.fhws.mobileapps.vorlesung3.layouts;

import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ToastDialogActivity extends FragmentActivity implements DemoDialog.DemoDialogListener {

	private final static String TAG = "Layouts";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast_dialog);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toast_dialog, menu);
		return true;
	}

	public void showToast( View view )
	{
		Toast.makeText(this, "Ein Toast", Toast.LENGTH_LONG ).show();
	}
	
	public void showMyDialog( View view )
	{
		DialogFragment dialog = new DemoDialog();
		dialog.show( getFragmentManager(), "achtung");
	}

	@Override
	public void onOk(DialogFragment dialog) {
		Log.d(TAG,"Ok pressed");		
	}

	@Override
	public void onCancel(DialogFragment dialog) {
		Log.d(TAG,"Cancel pressed");
	}
	
	
	
	
}
