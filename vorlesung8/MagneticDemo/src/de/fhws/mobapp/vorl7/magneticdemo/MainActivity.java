package de.fhws.mobapp.vorl7.magneticdemo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
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
	private TriggerEventListener triggerEventListener;
	private Sensor trigSensor;
	private MediaPlayer mediaPlayer;

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
	}

	@Override
	protected void onResume( )
	{
		super.onResume( );
		Sensor accSensor = sManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
		Sensor magSensor = sManager.getDefaultSensor( Sensor.TYPE_MAGNETIC_FIELD );
		trigSensor = sManager.getDefaultSensor( Sensor.TYPE_SIGNIFICANT_MOTION );
		this.mySensorListener = new MySensorListener( );
		this.triggerEventListener = new MyTriggerListener( );
		sManager.registerListener( this.mySensorListener, accSensor, SensorManager.SENSOR_DELAY_UI );
		sManager.registerListener( this.mySensorListener, magSensor, SensorManager.SENSOR_DELAY_UI );
		sManager.requestTriggerSensor( triggerEventListener, trigSensor );
		mediaPlayer = MediaPlayer.create( this, Settings.System.DEFAULT_RINGTONE_URI );
		mediaPlayer.start( );
	}

	@Override
	protected void onPause( )
	{
		super.onPause( );
		this.sManager.unregisterListener( mySensorListener );
		this.sManager.cancelTriggerSensor( triggerEventListener, trigSensor );
		this.mediaPlayer.release( );
	}

	class MySensorListener implements SensorEventListener
	{
		float[ ] mGravity;
		float[ ] mGeomagnetic;

		@Override
		public void onAccuracyChanged( Sensor sensor, int accuracy )
		{}

		@Override
		public void onSensorChanged( SensorEvent event )
		{
			if ( event.sensor.getType( ) == Sensor.TYPE_ACCELEROMETER )
			{
				mGravity = event.values;
			}

			if ( event.sensor.getType( ) == Sensor.TYPE_MAGNETIC_FIELD )
			{
				mGeomagnetic = event.values;
			}

			if ( mGravity != null && mGeomagnetic != null )
			{
				float R[] = new float[ 9 ];
				float I[] = new float[ 9 ];
				boolean success = SensorManager.getRotationMatrix( R, I, mGravity, mGeomagnetic );
				if ( success )
				{
					float orientation[] = new float[ 3 ];
					SensorManager.getOrientation( R, orientation );
					float azimut = orientation[ 0 ]; // orientation contains: azimut, pitch and roll
					float pitch = orientation[ 1 ]; // orientation contains: azimut, pitch and roll
					float roll = orientation[ 2 ]; // orientation contains: azimut, pitch and roll

					double gradNorth = Math.toDegrees( azimut );
					Log.i( "SENSOR", "Azimut: " + Math.toDegrees( azimut ) + " Pitch: " + Math.toDegrees( pitch ) +
						" Roll: " + Math.toDegrees( roll ) );

					if ( gradNorth < 5.0f && gradNorth > -5.0f )
					{
						Log.i( "SENSOR", "Gestoppt" );
						if ( MainActivity.this.mediaPlayer.isPlaying( ) )
						{
							MainActivity.this.mediaPlayer.pause( );
						}
					}
					else
					{
						if ( MainActivity.this.mediaPlayer.isPlaying( ) == false )
						{
							MainActivity.this.mediaPlayer.start( );
						}
					}

				}
			}
		}
	}

	class MyTriggerListener extends TriggerEventListener
	{
		@Override
		public void onTrigger( TriggerEvent event )
		{
			Log.i( "SENSOR", "Significant movement" );
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
