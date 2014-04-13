package de.fhws.mobileapps.vorlesung5.backend;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPBinding;

import com.owlike.genson.Genson;

/**
 * Servlet implementation class PersonContainer
 */
@WebServlet("/persons")
public class Persons extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Persons() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idAsString = request.getParameter("id");

		if (idAsString == null) {
			Collection<Person> allPersons = PersonContainer.getInstance()
					.values();
			
			
			for (Person p : allPersons) {
				response.getWriter().println(p.toString());
			}
		} else {
			try {
				long id = Long.parseLong(idAsString);
				Person p = PersonContainer.getInstance().load(id);

				if( p != null )
				{
					response.getWriter().println(p.toString());
				}
				else
				{
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (Exception e) {
				// ignore
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String url = request.getRequestURL().toString();

		Person p = new Person(firstName, lastName);
		long newId = PersonContainer.getInstance().save(p);

		response.setStatus(HttpServletResponse.SC_CREATED);
		response.setHeader("Location", url + "?id=" + newId);
	}

}
