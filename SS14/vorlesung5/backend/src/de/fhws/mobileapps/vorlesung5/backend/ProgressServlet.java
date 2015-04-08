package de.fhws.mobileapps.vorlesung5.backend;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/progress")
public class ProgressServlet extends HttpServlet 
{
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int size = readParameter( request, "size", 1 );
		int pause = readParameter( request, "pause", 0 );
		OutputStream os = response.getOutputStream();
		response.setContentLength(size);
		response.setContentType("text/html");
		
		for( int i=0; i<size; i++ )
		{
			os.write( 'a' );
			waitFor( pause );
		}
		
		os.flush();
	}
	
	private void waitFor( int pause )
	{
		try
		{
			Thread.sleep( pause );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	private int readParameter( HttpServletRequest request, String name, int defaultValue )
	{
		String value = request.getParameter(name);
		int returnValue = defaultValue;
		
		if( value != null )
		{
			try 
			{
				returnValue = Integer.parseInt(value);
			} 
			catch( Exception e )
			{
				// ignore
			}
		}
		
		return returnValue;
	}
	
}
