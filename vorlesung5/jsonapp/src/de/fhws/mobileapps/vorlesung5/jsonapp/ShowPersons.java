package de.fhws.mobileapps.vorlesung5.jsonapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Dialog;
import android.app.ListActivity;
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
			HttpGet get = new HttpGet( "http://" + HOST + ":8080/fhws/api/persons" );
			get.addHeader("Accept", "application/json" );
			DataAdapter adapter = new DataAdapter(ShowPersons.this);
			
			try
			{
				Genson genson = new Genson();
				HttpResponse response = client.execute(get);
				String reply = IOUtils.toString(response.getEntity().getContent());
				Collection<Person> cp = genson.deserialize(reply, new GenericType<List<Person>>(){} );

				for( Person p : cp )
				{
					adapter.addData(p);
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
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
