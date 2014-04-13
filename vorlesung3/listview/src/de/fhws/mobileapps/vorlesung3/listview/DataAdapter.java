package de.fhws.mobileapps.vorlesung3.listview;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter {
	
	private List<String> data = new LinkedList<String>();
	
	private Context context;
	
	public DataAdapter( Context context )
	{
		createData();
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View myview = convertView;
		
		if( convertView == null ) {
			LayoutInflater li = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			myview = li.inflate(R.layout.listrow, null);
		}
		
		TextView tv = (TextView)myview.findViewById(R.id.textView1);
		tv.setText(this.data.get(position));
		
		TextView c = (TextView)myview.findViewById(R.id.textView2);
		c.setText(Integer.toString(position));
		
		return myview;
	}

	private void createData()
	{
		for( int i=0; i<200; i++ )
		{
			data.add("Element " + i );
		}
	}
}
