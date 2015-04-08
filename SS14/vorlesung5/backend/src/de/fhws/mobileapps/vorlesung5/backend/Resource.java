package de.fhws.mobileapps.vorlesung5.backend;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path( "persons" )
public class Resource
{
	private final static String SIZE_HEADER = "X-persons-length";

	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response loadPersons(
		@DefaultValue( "2147483647" ) @QueryParam( "size" ) int size,
		@DefaultValue( "0" ) @QueryParam( "offset" ) int offset )
	{
		final List<Person> result = new ArrayList<Person>( PersonContainer.getInstance( ).values( ) );
		final int sizeOfResult = result.size( );
		final int toIndex = Math.min( sizeOfResult, offset + size );
		return Response.ok( result.subList( offset, toIndex ) ).header( SIZE_HEADER, sizeOfResult ).build( );
	}

	@GET
	@Path( "/{personid}" )
	@Produces( MediaType.APPLICATION_JSON )
	public Person loadPerson( @DefaultValue( "-1" ) @PathParam( "personid" ) long personId )
	{
		return PersonContainer.getInstance( ).load( personId );
	}

	@POST
	@Consumes( MediaType.APPLICATION_JSON )
	public Response createPerson( Person person, @Context UriInfo uriInfo )
	{
		final long id = PersonContainer.getInstance( ).save( person );
		final URI loc = uriInfo.getAbsolutePathBuilder( ).path( Long.toString( id ) ).build( );
		return Response.created( loc ).build( );
	}

}
