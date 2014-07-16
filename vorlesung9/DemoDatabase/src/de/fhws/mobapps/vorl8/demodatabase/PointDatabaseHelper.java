package de.fhws.mobapps.vorl8.demodatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PointDatabaseHelper extends SQLiteOpenHelper
{
	public static final String TABLE_NAME = "points";
	public static final int DATABASE_VERSION = 1;
	public static final String FIELD_ID = "_id";
	public static final String FIELD_NAME = "playerName";
	public static final String FIELD_ROUND = "round";
	public static final String FIELD_POINTS = "points";

	PointDatabaseHelper( Context context )
	{
		super( context, TABLE_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate( SQLiteDatabase db )
	{
		db.execSQL( createTableSQL( ) );
	}

	@Override
	public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion )
	{
		db.execSQL( dropTableSQL( ) );
		onCreate( db );
	}

	private String createTableSQL( )
	{
		StringBuffer returnValue = new StringBuffer( );

		returnValue.append( "CREATE TABLE " ).append( TABLE_NAME );
		returnValue.append( "(" );
		returnValue.append( FIELD_ID ).append( " INTEGER PRIMARY KEY AUTOINCREMENT" ).append( "," );
		returnValue.append( FIELD_NAME ).append( " VARCHAR(20)" ).append( "," );
		returnValue.append( FIELD_ROUND ).append( " INTEGER" ).append( "," );
		returnValue.append( FIELD_POINTS ).append( " INTEGER" );
		returnValue.append( ")" );

		return returnValue.toString( );
	}

	private String dropTableSQL( )
	{
		StringBuffer returnValue = new StringBuffer( );
		returnValue.append( "DROP TABLE IF EXISTS " ).append( TABLE_NAME );
		return returnValue.toString( );
	}

}
