package de.fhws.mobapps.vorl8.demodict;

import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
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
	protected void onStart( )
	{
		super.onStart( );
		listNextCalenderEvents( );
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

	private void listNextCalenderEvents( )
	{
		ContentResolver cr = this.getContentResolver( );
		Cursor cursor = null;

		// cursor = cr.query( ContactsContract.Contacts.CONTENT_URI, new String[ ] { PhoneLookup.DISPLAY_NAME,
		// PhoneLookup.PHOTO_URI }, null, null,
		// null );

		long begin = System.currentTimeMillis( ); // starting time in milliseconds
		long end = begin + ( 1000 * 60 * 60 * 2 ); // ending time in milliseconds
		String[ ] proj =
			new String[ ] {
				Instances._ID,
				Instances.BEGIN,
				Instances.END,
				Instances.EVENT_ID };

		cursor = Instances.query( getContentResolver( ), proj, begin, end );

		if ( cursor == null )
		{
			Log.d( "CONTACTS", "keine Kontakte gefunden" );
		}
		else
		{
			cursor.moveToFirst( );
			while ( cursor.isAfterLast( ) == false )
			{
				Log.d( "CONTACTS", cursor.getString( 0 ) + " " + cursor.getString( 1 ) + " " + cursor.getString( 2 ) +
					" " + cursor.getString( 3 ) );
				printEventDetails( Long.parseLong( cursor.getString( 3 ) ) );
				cursor.moveToNext( );
			}
		}
		cursor.close( );
	}

	private void printEventDetails( long eventId )
	{
		long selectedEventId = eventId; // the event-id;

		String[ ] proj =

			new String[ ] {

				Events._ID,

				Events.DTSTART,

				Events.DTEND,

				Events.RRULE,

				Events.TITLE, Events.ACCOUNT_NAME };

		Cursor cursor =

			getContentResolver( ).

				query(

					Events.CONTENT_URI,

					proj,

					Events._ID + " = ? ",

					new String[ ] { Long.toString( selectedEventId ) },

					null );

		if ( cursor.moveToFirst( ) )
		{
			long startDate = cursor.getLong( 1 );
			Date date = new Date( startDate );
			Log.d( "CONTACTS", date.toString( ) + " " + cursor.getString( 4 ) + " " + cursor.getString( 5 ) );
		}

	}
}
