package de.fhws.mobapps.vorl8.demodatabase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class DatabaseAdapter extends CursorAdapter
{
	protected LayoutInflater inflater;

	public DatabaseAdapter( Context context, Cursor c, boolean autoRequery )
	{
		super( context, c, autoRequery );
		this.inflater = LayoutInflater.from( context );
	}

	@Override
	public void bindView( View view, Context context, Cursor cursor )
	{
		TextView text = ( TextView ) view.findViewById( android.R.id.text1 );
		text.setText( cursor.getString( 1 ) );
	}

	@Override
	public View newView( Context context, Cursor cursor, ViewGroup parent )
	{
		return this.inflater.inflate( android.R.layout.simple_list_item_1, null );
	}

}
