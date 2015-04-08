package de.fhws.mobileapps.vorlesung5.backend;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath( "api" )
public class RestService extends Application 
{
	public RestService( ) {}
	 
	@Override
	public Set<Class<?>> getClasses( )
	{
		final Set<Class<?>> returnValue = new HashSet<Class<?>>( );
		returnValue.add( Resource.class );
		return returnValue;
	}
}
