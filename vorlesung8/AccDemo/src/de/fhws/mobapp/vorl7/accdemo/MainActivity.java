package de.fhws.mobapp.vorl7.accdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity
{
	private SensorManager sManager;
	private MySensorListener mySensorListener;

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

		String serviceName = Context.SENSOR_SERVICE;
		sManager = ( SensorManager ) this.getSystemService( serviceName );
		Sensor accSensor = sManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
		this.mySensorListener = new MySensorListener( );
		sManager.registerListener( this.mySensorListener, accSensor, SensorManager.SENSOR_DELAY_UI );
	}

	@Override
	protected void onPause( )
	{
		super.onPause( );
		this.sManager.unregisterListener( mySensorListener );
	}

	class MySensorListener implements SensorEventListener
	{
		protected static final long NS2S = 1000000000l;
		protected static final float THRESH_1 = 15.0f;
		protected static final float THRESH_2 = -4.0f;
		protected static final float THRESH_4 = 10.0f;

		protected int state = 0;

		protected long nsStart1;

		@Override
		public void onAccuracyChanged( Sensor sensor, int accuracy )
		{}

		@Override
		public void onSensorChanged( SensorEvent event )
		{
			float xAxis = event.values[ 0 ];
			float yAxis = event.values[ 1 ];
			float zAxis = event.values[ 2 ];

			// Log.i( "SENSOR", yAxis + " und Zustand " + state );

			// if ( state == 0 && yAxis > THRESH_1 )
			// {
			// state = 1;
			// nsStart1 = event.timestamp;
			// }
			// else if ( state == 1 && yAxis < THRESH_2 )
			// {
			// state = 2;
			// }
			// else if ( state == 2 && yAxis > THRESH_1 )
			// {
			// state = 3;
			// }
			// else if ( state == 3 && yAxis < THRESH_4 )
			// {
			// state = 4;
			// }
			//
			// if ( state == 4 )
			// {
			// Log.i( "SENSOR", "ERKANNT" );
			// state = 0;
			// }
			//
			// if ( state != 0 && ( event.timestamp - nsStart1 ) > NS2S )
			// {
			// Log.i( "SENSOR", "Reset" );
			// state = 0;
			// }

			float mAccelCurrent = FloatMath.sqrt( xAxis * xAxis + yAxis * yAxis + zAxis * zAxis );

			if ( mAccelCurrent > 11.0f || mAccelCurrent < 8.0f )
			{
				Log.i( "SENSOR", "Bewegung" );
			}
			// Log.i( "SENSOR", "Acc: " + mAccelCurrent );
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
			return rootView;
		}
	}

}
