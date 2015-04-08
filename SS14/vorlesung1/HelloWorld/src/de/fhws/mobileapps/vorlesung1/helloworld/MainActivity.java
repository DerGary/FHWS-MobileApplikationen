package de.fhws.mobileapps.vorlesung1.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		final Button b = ( Button ) findViewById( R.id.okButton );
		b.setOnClickListener( new OnClickListener( )
		{
			@Override
			public void onClick( View v )
			{
				EditText edit = ( EditText ) findViewById( R.id.username );
				String userName = edit.getText( ).toString( );
				TextView result = ( TextView ) findViewById( R.id.welcome );
				String welcomeText = getString( R.string.welcome, userName );
				result.setText( welcomeText );
			}
		} );
	}

	@Override
	protected void onResume( )
	{
		super.onResume( );
		EditText edit = ( EditText ) findViewById( R.id.username );
		edit.setText( "" );
		TextView result = ( TextView ) findViewById( R.id.welcome );
		result.setText( "" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater( ).inflate( R.menu.main, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		Toast.makeText( this, getString( R.string.aboutText ), Toast.LENGTH_LONG ).show( );
		return true;
	}
}
