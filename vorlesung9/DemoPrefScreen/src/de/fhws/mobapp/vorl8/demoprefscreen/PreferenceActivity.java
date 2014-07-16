package de.fhws.mobapp.vorl8.demoprefscreen;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuItem;

public class PreferenceActivity extends android.preference.PreferenceActivity
{

	// @Override
	// public void onBuildHeaders( List<Header> target )
	// {
	// super.onBuildHeaders( target );
	// // loadHeadersFromResource( R.xml.pref_headers, target );
	// }

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater( ).inflate( R.menu.preference, menu );
		return true;
	}

	@Override
	protected boolean isValidFragment( String fragmentName )
	{
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

	public static class PlaceholderFragment extends PreferenceFragment
	{

		public PlaceholderFragment( )
		{}

		@Override
		public void onCreate( Bundle savedInstanceState )
		{
			super.onCreate( savedInstanceState );
			addPreferencesFromResource( R.xml.myprefs );
		}
	}

	public static class DisplaySettingsFragment extends PreferenceFragment
	{

		public DisplaySettingsFragment( )
		{}

		@Override
		public void onCreate( Bundle savedInstanceState )
		{
			super.onCreate( savedInstanceState );
			addPreferencesFromResource( R.xml.display );
		}
	}

}
