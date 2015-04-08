package de.fhws.mobapps.vorl8.demodatabase;

import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.FIELD_ID;
import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.FIELD_NAME;
import static de.fhws.mobapps.vorl8.demodatabase.PointDatabaseHelper.TABLE_NAME;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class NameProvider extends ContentProvider
{
	private static final int ALL_PERSONS = 1;
	private static final int ONE_PERSON = 2;

	private static final String AUTHORITIES = "de.fhws.applab.vorl8.nameprovider";

	public static final Uri CONTENT_URI = Uri.parse( "content://de.fhws.applab.vorl8.nameprovider/persons" );

	public static final String PERSON_TYPE = "application/vnd.persons";

	public static final UriMatcher uriMatcher;

	static
	{
		uriMatcher = new UriMatcher( UriMatcher.NO_MATCH );
		uriMatcher.addURI( AUTHORITIES, "persons", ALL_PERSONS );
		uriMatcher.addURI( AUTHORITIES, "persons/#", ONE_PERSON );
	}

	private PointDatabaseHelper database;

	@Override
	public int delete( Uri uri, String selection, String[ ] selectionArgs )
	{
		long id = ContentUris.parseId( uri );
		SQLiteDatabase db = this.database.getWritableDatabase( );
		return db.delete( TABLE_NAME, "_id = ?", new String[ ] { Long.toString( id ) } );
	}

	@Override
	public String getType( Uri uri )
	{
		return PERSON_TYPE;
	}

	@Override
	public Uri insert( Uri uri, ContentValues values )
	{
		SQLiteDatabase db = this.database.getWritableDatabase( );
		db.insert( TABLE_NAME, null, values );
		String name = values.getAsString( FIELD_NAME );
		return Uri.withAppendedPath( uri, name );
	}

	@Override
	public boolean onCreate( )
	{
		this.database = new PointDatabaseHelper( getContext( ) );
		return true;
	}

	@Override
	public Cursor query( Uri uri, String[ ] projection, String selection, String[ ] selectionArgs, String sortOrder )
	{
		if ( uriMatcher.match( uri ) == ALL_PERSONS )
		{
			SQLiteDatabase db = this.database.getReadableDatabase( );
			return db.query( TABLE_NAME, new String[ ] { FIELD_ID, FIELD_NAME }, null, null,
				null, null, FIELD_NAME, null );
		}
		else if ( uriMatcher.match( uri ) == ONE_PERSON )
		{
			long id = ContentUris.parseId( uri );
			SQLiteDatabase db = this.database.getReadableDatabase( );
			return db.query( TABLE_NAME, new String[ ] { FIELD_ID, FIELD_NAME }, FIELD_ID + " = " + id, null,
				null, null, FIELD_NAME, null );
		}
		else
		{
			throw new IllegalArgumentException( );
		}
	}

	@Override
	public int update( Uri uri, ContentValues values, String selection, String[ ] selectionArgs )
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
