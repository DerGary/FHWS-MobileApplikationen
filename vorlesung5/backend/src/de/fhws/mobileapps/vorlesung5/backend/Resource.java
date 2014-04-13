package de.fhws.mobileapps.vorlesung5.backend;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;


@Path("persons")
public class Resource 
{
	@GET
	@Produces( "application/json" )
	public Collection<Person> printHelloWorld( )
	{
		return PersonContainer.getInstance().values();
	}
	
	@GET
	@Path( "/{personid}" )
	@Produces( "application/json" )
	public Person loadPerson( @DefaultValue( "-1" ) @PathParam( "personid" ) long personId )
	{
		return PersonContainer.getInstance().load(personId);
	}
	
	@POST
	@Consumes( "application/json" )
	public Response createPerson( Person person, @Context HttpServletRequest httpRequest )
	{
		long id = PersonContainer.getInstance().save(person);
		String url = httpRequest.getRequestURL().toString();
		
		return Response.status(HttpServletResponse.SC_CREATED).header("Location", url + "/" + id ).build();
	}

	
}
