package de.fhws.mobapp.vorl11.widgetdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

public class DemoAppWidget extends AppWidgetProvider
{
	public DemoAppWidget( )
	{}

	@Override
	public void onDeleted( Context context, int[ ] appWidgetIds )
	{
		super.onDeleted( context, appWidgetIds );
		Log.d( "WIDGET", "Deleted" );
	}

	@Override
	public void onDisabled( Context context )
	{
		super.onDisabled( context );
		Log.d( "WIDGET", "Disabled" );
	}

	@Override
	public void onEnabled( Context context )
	{
		super.onEnabled( context );
		Log.d( "WIDGET", "Started" );
	}

	@Override
	public void onUpdate( Context context, AppWidgetManager appWidgetManager, int[ ] appWidgetIds )
	{
		super.onUpdate( context, appWidgetManager, appWidgetIds );
		Log.d( "WIDGET", "Updated" );
		updateView( context );
	}

	@Override
	public void onReceive( Context context, Intent intent )
	{
		super.onReceive( context, intent );
		Log.d( "WIDGET", "on Receive from Alarm Manager" );
		updateView( context );
	}

	private void updateView( Context context )
	{
		new GetStatus( context ).execute( );
	}

	class GetStatus extends AsyncTask<Void, Void, String>
	{
		private static final String URL = "http://staging.applab.fhws.de:8080/tagpeter/demo/status";

		private Context context;

		private GetStatus( Context context )
		{
			this.context = context;
		}

		@Override
		protected String doInBackground( Void... params )
		{
			AndroidHttpClient httpClient = AndroidHttpClient.newInstance( "" );
			HttpGet get = new HttpGet( URL );

			try
			{
				HttpResponse response = httpClient.execute( get );
				HttpEntity entity = response.getEntity( );
				InputStreamReader reader = new InputStreamReader( entity.getContent( ) );
				BufferedReader bufReader = new BufferedReader( reader );

				String status = bufReader.readLine( );
				entity.consumeContent( );

				return status;
			}
			catch ( Exception e )
			{
				Log.e( "WIDGET", e.getMessage( ) );
			}

			return "No status received";
		}

		@Override
		protected void onPostExecute( String result )
		{
			super.onPostExecute( result );
			ComponentName componentName = new ComponentName( context, DemoAppWidget.class );
			AppWidgetManager awm = AppWidgetManager.getInstance( context );
			int[ ] appWidgetIds = awm.getAppWidgetIds( componentName );
			RemoteViews views = new RemoteViews( context.getPackageName( ), R.layout.widget );
			views.setTextViewText( R.id.textView1, result );
			awm.updateAppWidget( appWidgetIds, views );
		}

	}

}
