package de.fhws.mobapps.vorl8.demodatabase;

import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.FIELD_NAME;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ContentActivity extends ListActivity implements LoaderCallbacks<Cursor>
{
	protected SimpleCursorAdapter adapter;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_content );

		this.adapter =
			new SimpleCursorAdapter( this, android.R.layout.simple_list_item_1, null, new String[ ] { FIELD_NAME },
				new int[ ] { android.R.id.text1 }, 0 );

		setListAdapter( this.adapter );
		getLoaderManager( ).initLoader( 0, null, this );
	}

	@Override
	protected void onStart( )
	{
		super.onStart( );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater( ).inflate( R.menu.content, menu );
		return true;
	}

	@Override
	protected void onListItemClick( ListView l, View v, int position, long id )
	{
		super.onListItemClick( l, v, position, id );
		ContentResolver cr = getContentResolver( );
		Uri uri = ContentUris.withAppendedId( NameProvider.CONTENT_URI, id );
		int nrOfRowsDel = cr.delete( uri, null, null );
		Toast.makeText( this, "Selected: " + id, Toast.LENGTH_SHORT ).show( );
		getLoaderManager( ).restartLoader( 0, null, this );
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
			Intent intent = new Intent( this, MainActivity.class );
			startActivityForResult( intent, 10 );
			return true;
		}
		return super.onOptionsItemSelected( item );
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data )
	{
		super.onActivityResult( requestCode, resultCode, data );
		getLoaderManager( ).restartLoader( 0, null, this );
	}

	@Override
	public Loader<Cursor> onCreateLoader( int id, Bundle args )
	{
		return new CursorLoader( this, NameProvider.CONTENT_URI, null, null, null, null );
	}

	@Override
	public void onLoadFinished( Loader<Cursor> arg0, Cursor arg1 )
	{
		this.adapter.swapCursor( arg1 );
	}

	@Override
	public void onLoaderReset( Loader<Cursor> arg0 )
	{
		this.adapter.swapCursor( null );
	}

	private void listPersons( )
	{
		final ContentResolver cr = this.getContentResolver( );
		final Cursor cursor =
			cr.query( ContentUris.withAppendedId( NameProvider.CONTENT_URI, 2 ), null, null, null, null );

		if ( cursor == null )
		{
			Log.d( "PERSONS", "keine Personen gefunden" );
		}
		else
		{
			for ( int i = 0; i < 10 && cursor.moveToNext( ); i++ )
			{
				Log.d( "PERSONS", cursor.getString( 1 ) );
			}

			cursor.close( );
		}

	}

}
