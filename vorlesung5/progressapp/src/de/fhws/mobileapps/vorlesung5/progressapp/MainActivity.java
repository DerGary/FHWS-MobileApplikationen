package de.fhws.mobileapps.vorlesung5.progressapp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String HOST = "backend.applab.fhws.de";

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
	
	class LoadFromNetwork extends AsyncTask<String, Integer, Integer>
	{
		@Override
		protected Integer doInBackground(String... url) {
			
			AndroidHttpClient httpClient = AndroidHttpClient.newInstance("test");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("size", Integer.toString(100)));
			nameValuePairs.add(new BasicNameValuePair("pause", Integer.toString(10)));
			
			HttpGet get = new HttpGet("http://" + HOST + ":8080/fhws/progress?" + URLEncodedUtils.format(nameValuePairs, "UTF-8"));
			
			int size = 0;
			
			try
			{
				HttpResponse response = httpClient.execute(get);
				size = Integer.parseInt(response.getFirstHeader(HTTP.CONTENT_LEN).getValue());
				
				InputStream is = response.getEntity().getContent();
				
				for( int i=0; i<size; i++ )
				{
					is.read();
					int progress = (int)((i/(float)size)*100);
					publishProgress(progress);
				}
				
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			return size;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) 
		{
			super.onProgressUpdate(values);
			ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar1);
			bar.setProgress(values[0]);
			Log.d("PROGRESS", ""+values[0]);
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			TextView tv = (TextView)findViewById(R.id.textView2);
			tv.setText(result +  " byte read");
		}
		
	}

}
