package de.fhws.mobapps.vorl8.demodatabase;

import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.FIELD_NAME;
import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.FIELD_POINTS;
import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.FIELD_ROUND;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{

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
			Button b = ( Button ) rootView.findViewById( R.id.button1 );
			b.setOnClickListener( new ButtonListener( ) );
			return rootView;
		}

		public class ButtonListener implements View.OnClickListener
		{
			@Override
			public void onClick( View v )
			{
				PointDatabaseHelper db = new PointDatabaseHelper( getActivity( ) );
				String name = ( ( EditText ) getActivity( ).findViewById( R.id.editText1 ) ).getText( ).toString( );

				ContentResolver cr = getActivity( ).getContentResolver( );

				ContentValues values = new ContentValues( );
				values.put( FIELD_NAME, name );
				values.put( FIELD_POINTS, 10 );
				values.put( FIELD_ROUND, 1 );

				cr.insert( NameProvider.CONTENT_URI, values );

				Toast.makeText( getActivity( ), "Saved", Toast.LENGTH_SHORT ).show( );
				db.close( );
				getActivity( ).finish( );
			}
		}
	}
}
