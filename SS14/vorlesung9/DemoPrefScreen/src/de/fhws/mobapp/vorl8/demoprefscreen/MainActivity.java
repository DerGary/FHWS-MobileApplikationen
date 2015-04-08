package de.fhws.mobapp.vorl8.demoprefscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
	}

	@Override
	protected void onRestart( )
	{
		super.onRestart( );
		Log.i( "PREF", "Restart Main Activity" );
		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences( this );
		Log.i( "PREF", p.getString( "eingabe", "keine Eingabe vorhanden" ) );
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		// TODO Auto-generated method stub
		super.onActivityResult( requestCode, resultCode, data );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater( ).inflate( R.menu.main, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId( );
		if ( id == R.id.action_settings )
		{
			Intent intent = new Intent( this, PreferenceActivity.class );
			intent.putExtra( PreferenceActivity.EXTRA_NO_HEADERS, true );
			intent.putExtra( PreferenceActivity.EXTRA_SHOW_FRAGMENT,
				"de.fhws.mobapp.vorl8.demoprefscreen.PreferenceActivity$PlaceholderFragment" );
			startActivityForResult( intent, 10 );
			return true;
		}
		return super.onOptionsItemSelected( item );
	}
}
