package de.fhws.mobileapps.vorlesung4.contextmenu;

import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 150;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setListAdapter( new DataAdapter(this));
		
		Button b = (Button)findViewById(R.id.button1);
		ListView l = (ListView)findViewById(android.R.id.list);
		l.setDivider(getResources().getDrawable(R.drawable.transperent_color));
		l.setDividerHeight(50);
		registerForContextMenu(b);
		
		Button b2 = (Button)findViewById(R.id.button2);
		b2.setOnLongClickListener( new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				Button b = (Button)v;
				b.setText("Press");
				return false;
			}
		});
		
//		gestureDetector = new GestureDetector(this, new MyGestureDetector());
//        gestureListener = new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        };
//        
//        b.setOnTouchListener(gestureListener);
//        l.setOnTouchListener(gestureListener);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.d("ListView", "Position " + position + " selected" );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}
	
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        	Toast.makeText(MainActivity.this, "Tst", Toast.LENGTH_SHORT);
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(MainActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(MainActivity.this, "Right Swipe", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// TODO Auto-generated method stub
        	Toast.makeText(MainActivity.this, "Tst1", Toast.LENGTH_SHORT).show();

			return super.onDoubleTap(e);
		}


    }

}
