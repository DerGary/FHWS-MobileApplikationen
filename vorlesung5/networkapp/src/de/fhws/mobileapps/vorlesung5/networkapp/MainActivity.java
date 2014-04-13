package de.fhws.mobileapps.vorlesung5.networkapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener( new MyOnClickListener() );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class MyOnClickListener implements View.OnClickListener 
	{

		@Override
		public void onClick(View v) {
			new LoadFromNetwork().execute("url");
		}
		
	}
	
	class LoadFromNetwork extends AsyncTask<String, Void, String>
	{
		@Override
		protected String doInBackground(String... url) {
			
			AndroidHttpClient httpClient = AndroidHttpClient.newInstance("test");
			HttpGet get = new HttpGet("http://backend.applab.fhws.de:8080/fhws/simple");
			
			try
			{
				HttpResponse response = httpClient.execute(get);
				InputStream is = response.getEntity().getContent();
				
				return IOUtils.toString(is);
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			return "Error";
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			TextView tv = (TextView)findViewById(R.id.textView2);
			tv.setText(result);
		}
		
	}

}
