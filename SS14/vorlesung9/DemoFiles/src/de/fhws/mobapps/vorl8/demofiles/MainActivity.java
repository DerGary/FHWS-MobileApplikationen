package de.fhws.mobapps.vorl8.demofiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
		private static final String FILE = "demo.txt";

		private EditText textForm;

		public PlaceholderFragment( )
		{}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState )
		{
			View rootView = inflater.inflate( R.layout.fragment_main, container, false );
			this.textForm = ( EditText ) rootView.findViewById( R.id.editText1 );
			Button button = ( Button ) rootView.findViewById( R.id.button1 );
			button.setOnClickListener( new ButtonListener( ) );
			return rootView;
		}

		@Override
		public void onStart( )
		{
			super.onStart( );
			fileInfo( );
			loadFromFile( );
		}

		private void saveIntoFile( )
		{
			saveIntoFile( this.textForm.getText( ).toString( ) );
			this.textForm.setText( "" );
		}

		private void saveIntoFile( String text )
		{
			FileOutputStream fos = null;
			OutputStreamWriter osw = null;

			try
			{
				fos = getActivity( ).openFileOutput( FILE, MODE_APPEND );
				osw = new OutputStreamWriter( fos );
				osw.append( text ).append( "\n" );
			}
			catch ( Exception e )
			{
				Log.e( "FILE", "Fehler beim Schreiben", e );
			}
			finally
			{
				if ( osw != null )
				{
					try
					{
						osw.close( );
					}
					catch ( Exception e )
					{
						Log.e( "FILE", "Fehler beim Schließen der Streams" );
					}
				}

				if ( fos != null )
				{
					try
					{
						fos.close( );
					}
					catch ( Exception e )
					{
						Log.e( "FILE", "Fehler beim Schließen der Streams" );
					}
				}

			}
		}

		private void fileInfo( )
		{
			File file = getActivity( ).getFilesDir( );
			Log.i( "FILE", file.getAbsolutePath( ) );
		}

		private void loadFromFile( )
		{
			FileInputStream fis = null;
			BufferedReader reader = null;
			String line = null;

			try
			{
				fis = getActivity( ).openFileInput( FILE );
				reader = new BufferedReader( new InputStreamReader( fis ) );
				while ( ( line = reader.readLine( ) ) != null )
				{
					Log.i( "FILE", "Zeile: " + line );
				}
			}
			catch ( Exception e )
			{
				Log.e( "FILE", "Fehler beim Schreiben", e );
			}
			finally
			{
				if ( fis != null )
				{
					try
					{
						fis.close( );
					}
					catch ( Exception e )
					{
						Log.e( "FILE", "Fehler beim Schließen der Streams" );
					}
				}

				if ( reader != null )
				{
					try
					{
						reader.close( );
					}
					catch ( Exception e )
					{
						Log.e( "FILE", "Fehler beim Schließen der Streams" );
					}
				}
			}
		}

		class ButtonListener implements View.OnClickListener
		{
			@Override
			public void onClick( View v )
			{
				saveIntoFile( );
			}
		}

	}

}
