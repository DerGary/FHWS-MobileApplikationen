package de.fhws.mobapp.vorl8.demoshardprefs;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
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
		private static final String LOGIN_PREFS = "LOGIN_PREFS";
		private static final String KEY_LOGIN = "login";
		private static final String KEY_PASSWORD = "password";

		private EditText loginField;
		private EditText passwordField;
		private SharedPreferences prefs;

		public PlaceholderFragment( )
		{}

		@Override
		public View onCreateView( LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState )
		{
			View rootView = inflater.inflate( R.layout.fragment_main, container, false );
			Button button1 = ( Button ) rootView.findViewById( R.id.button1 );
			button1.setOnClickListener( new View.OnClickListener( )
			{
				@Override
				public void onClick( View v )
				{
					safeAsPref( );
				}
			} );
			Button button2 = ( Button ) rootView.findViewById( R.id.button2 );
			button2.setOnClickListener( new OnClearClick( ) );
			this.loginField = ( EditText ) rootView.findViewById( R.id.editText1 );
			this.passwordField = ( EditText ) rootView.findViewById( R.id.editText2 );
			return rootView;
		}

		@Override
		public void onAttach( Activity activity )
		{
			super.onAttach( activity );
			this.prefs = activity.getSharedPreferences( LOGIN_PREFS, MODE_PRIVATE );
		}

		@Override
		public void onStart( )
		{
			super.onStart( );
			setFromPrefs( );
		}

		private void setFromPrefs( )
		{
			String login = prefs.getString( KEY_LOGIN, "" );
			String password = prefs.getString( KEY_PASSWORD, "" );

			if ( login.isEmpty( ) == false )
			{
				loginField.setText( login );
				passwordField.setText( password );
			}
		}

		private void safeAsPref( )
		{
			SharedPreferences.Editor edit = prefs.edit( );
			edit.putString( KEY_LOGIN, this.loginField.getText( ).toString( ) );
			edit.putString( KEY_PASSWORD, this.passwordField.getText( ).toString( ) );
			edit.apply( );
		}

		private void clearPrefs( )
		{
			SharedPreferences.Editor edit = prefs.edit( );
			edit.putString( KEY_LOGIN, "" );
			edit.putString( KEY_PASSWORD, this.passwordField.getText( ).toString( ) );
			edit.apply( );
		}

		class OnSaveClick implements View.OnClickListener
		{
			@Override
			public void onClick( View v )
			{
				safeAsPref( );
			}
		}

		class OnClearClick implements View.OnClickListener
		{
			@Override
			public void onClick( View v )
			{
				clearPrefs( );
			}
		}

	}
}
