package de.fhws.mobileapps.vorlesung5.jsonapp;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter {

	private List<Person> data = new LinkedList<Person>();

	private Context context;

	public DataAdapter(Context context) {
		this.context = context;
	}

	public void addData(Person person) {
		this.data.add(person);
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

		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			myview = li.inflate(R.layout.listrow, null);
		}

		Person p = this.data.get(position);

		TextView tv = (TextView) myview.findViewById(R.id.textView1);
		tv.setText(p.getFirstName() + " " + p.getLastName());

		TextView c = (TextView) myview.findViewById(R.id.textView2);
		c.setText(Long.toString(p.getId()));

		return myview;
	}

}
