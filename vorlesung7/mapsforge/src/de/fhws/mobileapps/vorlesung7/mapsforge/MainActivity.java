package de.fhws.mobileapps.vorlesung7.mapsforge;

import java.io.File;

import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.ArrayCircleOverlay;
import org.mapsforge.android.maps.overlay.ArrayItemizedOverlay;
import org.mapsforge.android.maps.overlay.ArrayWayOverlay;
import org.mapsforge.android.maps.overlay.OverlayCircle;
import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.android.maps.overlay.OverlayWay;
import org.mapsforge.core.GeoPoint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends MapActivity implements LocationListener
{

	protected MapView mapView;

	protected Drawable meAsIcon;

	protected OverlayItem lastOverlayItem;

	protected ArrayItemizedOverlay overlay;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		this.mapView = new MapView( this );
		mapView.setClickable( true );
		mapView.setBuiltInZoomControls( true );
		mapView.setMapFile( new File( "/sdcard/Maps/bayern.map" ) );
		GeoPoint geo = new GeoPoint( 49.777, 9.9632 );
		mapView.setCenter( geo );
		Drawable defaultMarker = getResources( ).getDrawable( R.drawable.fhws );
		ArrayItemizedOverlay itemizedOverlay = new ArrayItemizedOverlay(
			defaultMarker, true );
		OverlayItem item = new OverlayItem( geo, "FHWS", "..." );
		itemizedOverlay.addItem( item );
		mapView.getOverlays( ).add( itemizedOverlay );

		meAsIcon = getResources( ).getDrawable( R.drawable.ic_launcher );
		overlay = new ArrayItemizedOverlay( meAsIcon, true );
		mapView.getOverlays( ).add( overlay );

		Paint p1 = createLinePaint( );
		ArrayWayOverlay wayOverlay = new ArrayWayOverlay( p1, p1 );
		mapView.getOverlays( ).add( wayOverlay );
		wayOverlay.addWay( createWay( ) );

		ArrayCircleOverlay aco = new ArrayCircleOverlay( null, null );
		OverlayCircle circle = new OverlayCircle( geo, 500, createCircleFill( ),
			createCirclePaint( ), "FHWS" );
		aco.addCircle( circle );
		mapView.getOverlays( ).add( aco );

		LocationManager locationManager = ( LocationManager ) this
			.getSystemService( Context.LOCATION_SERVICE );
		Criteria criteria = new Criteria( );
		criteria.setAccuracy( Criteria.ACCURACY_FINE );
		criteria.setPowerRequirement( Criteria.POWER_LOW );
		String provider = locationManager.getBestProvider( criteria, true );
		locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
			10000, 10, this );
		// locationManager.requestLocationUpdates( provider, 5000, 10, this );

		setContentView( mapView );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater( ).inflate( R.menu.main, menu );
		return true;
	}

	@Override
	public void onLocationChanged( Location location )
	{
		Log.d( "LM", "New location " + location.toString( ) );
		GeoPoint geo = new GeoPoint( location.getLatitude( ),
			location.getLongitude( ) );
		OverlayItem item = new OverlayItem( geo, "Me", "..." );

		// try
		// {
		// Geocoder coder = new Geocoder( this );
		// List<Address> a = coder.getFromLocation( location.getLatitude( ),
		// location.getLongitude( ), 10 );
		// Log.d( "LM", a.toString( ) );
		// }
		// catch ( Exception e )
		// {
		// e.printStackTrace( );
		// }

		if ( lastOverlayItem != null )
		{
			overlay.removeItem( lastOverlayItem );
		}

		lastOverlayItem = item;
		overlay.addItem( item );
	}

	@Override
	public void onStatusChanged( String provider, int status, Bundle extras )
	{

	}

	@Override
	public void onProviderEnabled( String provider )
	{

	}

	@Override
	public void onProviderDisabled( String provider )
	{

	}

	protected Paint createLinePaint( )
	{
		Paint paint = new Paint( );
		paint.setAntiAlias( true );
		paint.setStyle( Style.STROKE );
		paint.setStrokeWidth( 5 );
		paint.setColor( Color.RED );
		return paint;
	}

	protected Paint createCirclePaint( )
	{
		Paint paint = new Paint( );
		paint.setAntiAlias( true );
		paint.setStyle( Style.STROKE );
		paint.setColor( Color.YELLOW );
		return paint;
	}

	protected Paint createCircleFill( )
	{
		Paint paint = new Paint( );
		paint.setAntiAlias( true );
		paint.setStyle( Style.FILL );
		paint.setColor( Color.DKGRAY );
		return paint;
	}

	protected OverlayWay createWay( )
	{
		OverlayWay overlayWay = new OverlayWay( );
		GeoPoint[ ] points = new GeoPoint[ 4 ];

		points[ 0 ] = new GeoPoint( 49.78, 9.95 );
		points[ 1 ] = new GeoPoint( 49.79, 9.96 );
		points[ 2 ] = new GeoPoint( 49.785, 9.97 );
		points[ 3 ] = points[ 0 ];

		overlayWay.setWayNodes( new GeoPoint[ ][ ] { points } );

		return overlayWay;
	}

}
