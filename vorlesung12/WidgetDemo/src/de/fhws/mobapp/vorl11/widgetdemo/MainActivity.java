package de.fhws.mobapp.vorl11.widgetdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity
{
	public static final String FORCE_UPDATE = "de.fhws.mobapp.vorl11.widgetdemo.forceupdate";

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		if ( savedInstanceState == null )
		{
			getFragmentManager( ).beginTransaction( )
				.add( R.id.container, new PlaceholderFragment( ) )
				.commit( );
		}

		AlarmManager am = ( AlarmManager ) getSystemService( Context.ALARM_SERVICE );
		Intent i = new Intent( FORCE_UPDATE );
		PendingIntent pi = PendingIntent.getBroadcast( this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT );
		am.setInexactRepeating( AlarmManager.RTC_WAKEUP, System.currentTimeMillis( ), 1000 * 10, pi );
		Log.d( "WIDGET", "Alarm einschalten" );

	}

	@Override
	protected void onStart( )
	{
		super.onStart( );
	}

	@Override
	protected void onDestroy( )
	{
		super.onDestroy( );
		Intent intent = new Intent( FORCE_UPDATE );
		PendingIntent sender = PendingIntent.getBroadcast( this, 0, intent, 0 );
		AlarmManager alarmManager = ( AlarmManager ) getSystemService( Context.ALARM_SERVICE );
		alarmManager.cancel( sender );
		Log.d( "WIDGET", "Alarm ausschalten" );
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
			return true;
		}
		return super.onOptionsItemSelected( item );
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment( )
		{}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState )
		{
			View rootView = inflater.inflate( R.layout.fragment_main, container, false );
			return rootView;
		}
	}

}
