package de.fhws.mobapp.vorl7.googlemapsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MainActivity extends Activity
{
	private static final LatLng FHWS_SHL = new LatLng( 49.7774d, 9.9635d );
	private static final LatLng FHWS_MUENZSTR = new LatLng( 49.7876d, 9.9327d );

	protected MapFragment mapFragment;
	protected GoogleMap theMap;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );

		this.mapFragment = MapFragment.newInstance( );
		getFragmentManager( ).beginTransaction( )
			.add( R.id.container, this.mapFragment )
			.commit( );
	}

	@Override
	protected void onStart( )
	{
		super.onStart( );
		this.theMap = this.mapFragment.getMap( );
		this.theMap.setMyLocationEnabled( true );
		final Marker mark1 = this.theMap.addMarker( new MarkerOptions( )
			.position( new LatLng( 0, 0 ) )
			.title( "Hello world" ) );

	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater( ).inflate( R.menu.main, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{

		return super.onOptionsItemSelected( item );
	}

}
