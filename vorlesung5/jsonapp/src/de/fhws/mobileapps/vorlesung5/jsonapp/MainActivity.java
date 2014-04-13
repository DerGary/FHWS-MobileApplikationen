package de.fhws.mobileapps.vorlesung5.jsonapp;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.owlike.genson.Genson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
private static final String HOST = "backend.applab.fhws.de";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void savePerson( View view )
	{
		EditText firstNameEdit = (EditText)findViewById(R.id.editText1);
		EditText lastNameEdit = (EditText)findViewById(R.id.editText2);
		
		new SavePerson().execute(firstNameEdit.getText().toString(), lastNameEdit.getText().toString() );
		
	}
	
	
	class SavePerson extends AsyncTask<String, Void, Boolean>
	{
		
		@Override
		protected Boolean doInBackground(String... params) 
		{
			String first = params[0];
			String last = params[1];
			
			Person person = new Person( first, last );
			HttpClient httpClient = new DefaultHttpClient();

			try
			{
				HttpPost post = new HttpPost("http://" + HOST + ":8080/fhws/api/persons" );
				post.setHeader("Content-type", "application/json");
				Genson genson = new Genson();
				post.setEntity( new StringEntity(genson.serialize(person).toString()));
				HttpResponse response = httpClient.execute(post);
				int status = response.getStatusLine().getStatusCode();
				return status == HttpStatus.SC_CREATED;
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
			
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			String text = "";
			
			if( result == true )
			{
				text = "Data saved";
			}
			else
			{
				text = "Data not saved";
			}
			
			Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent( MainActivity.this, ShowPersons.class );
			startActivity(intent);
		}
	}

}
