package de.fhws.mobileapps.vorlesung4.actionbar;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();
		actionBar.setCustomView(R.layout.searchbar);
		actionBar.setTitle("AB Test");
		Drawable d = getResources().getDrawable(R.drawable.grey_wash_wall);
		actionBar.setBackgroundDrawable(d);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE|ActionBar.DISPLAY_SHOW_CUSTOM);
		
		final SearchView sv = (SearchView)actionBar.getCustomView().findViewById(R.id.searchbar);
		sv.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
			@Override
		    public boolean   onQueryTextChange( String newText ) {
				return true;
		    }

		    @Override
		    public boolean   onQueryTextSubmit(String query) {
				Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
				
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);

				sv.onActionViewCollapsed();

		    	return true;
		    }
		});

		Tab tab = actionBar
				.newTab()
				.setText("First tab")
				.setIcon( R.drawable.action_help )
				.setTabListener(
						new MyTabListener( new Fragment1()));
		
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText("Second Tab")
				.setTabListener(
						new MyTabListener(new Fragment2()));
		actionBar.addTab(tab);

//		ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
//		tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				ActionBar actionBar = getActionBar();
//
//				if (isChecked) {
//					actionBar.hide();
//				} else {
//					actionBar.show();
//				}
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
		switch (item.getItemId()) {
		case android.R.id.home:
			ActionBar actionBar = getActionBar();
			actionBar.setSubtitle("Home");
			break;
		}

		return true;
	}

	public static class MyTabListener implements
			TabListener {
		private Fragment fragment;

		public MyTabListener(Fragment fragment) {
			this.fragment = fragment;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.replace( R.id.fragment_container, fragment );
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove( fragment );
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}

	public static class Fragment1 extends Fragment
	{

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment1, container, false );
			return view;
					
		}

		
	}
	public static class Fragment2 extends Fragment
	{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment2, container, false );
			return view;
					
		}
		
	}
	
	
}
