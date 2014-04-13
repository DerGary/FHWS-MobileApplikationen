package de.fhws.mobileapps.vorlesung3.splashscreen;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Thread mythread = new Thread() {
            public void run() {
                try {
            		sleep(3000);
                }
                catch(Exception e) {}
                finally {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK); 
                    startActivity(intent);
                }
            }
        };
       
        mythread.start();
	}
	
	
}
