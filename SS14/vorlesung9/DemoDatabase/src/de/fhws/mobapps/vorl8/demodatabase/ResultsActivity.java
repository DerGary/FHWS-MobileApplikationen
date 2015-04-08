package de.fhws.mobapps.vorl8.demodatabase;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ResultsActivity extends ListActivity
{
	private PointDatabaseHelper db;
	private Cursor cursor;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_results );

		this.db = new PointDatabaseHelper( this );
		// this.cursor = this.db.getAllPersons( );

		setListAdapter( new DatabaseAdapter( this, this.cursor, true ) );
	}

	@Override
	protected void onDestroy( )
	{
		super.onDestroy( );
		this.db.close( );
	}

	@Override
	protected void onListItemClick( ListView l, View v, int position, long id )
	{
		super.onListItemClick( l, v, position, id );
		Toast.makeText( this, "Click " + id, Toast.LENGTH_SHORT ).show( );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater( ).inflate( R.menu.results, menu );
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
}
