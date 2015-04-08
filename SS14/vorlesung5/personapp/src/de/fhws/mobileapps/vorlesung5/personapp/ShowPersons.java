package de.fhws.mobileapps.vorlesung5.personapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.view.Menu;

public class ShowPersons extends ListActivity {

	private static final String HOST = "backend.applab.fhws.de";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_persons);
		new LoadAllPersons().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.show_persons, menu);
		return true;
	}
	
	class LoadAllPersons extends AsyncTask<Void,Void,DataAdapter>
	{
		Dialog progress;

	    @Override
	    protected void onPreExecute() {
//	        progress = ProgressDialog.show(ShowPersons.this, 
//	                "Loading data", "Please wait...");
	        super.onPreExecute();
	    }

		@Override
		protected DataAdapter doInBackground(Void... params) 
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet( "http://" + HOST + ":8080/fhws/persons" );
			DataAdapter adapter = new DataAdapter(ShowPersons.this);
			
			try
			{
				HttpResponse response = client.execute(get);
				InputStream is = response.getEntity().getContent();
				BufferedReader br = new BufferedReader( new InputStreamReader( is ));
				String line = null;
				
				while( (line = br.readLine()) != null )
				{
					adapter.addData(line);
				}
			}
			catch( Exception e )
			{
				// ignore
			}
			
			return adapter;
		}

		@Override
		protected void onPostExecute(DataAdapter result) {
//	        progress.dismiss();
			ShowPersons.this.setListAdapter(result);
		}
	}
}
