package de.fhws.mobileapps.vorlesung5.backend;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class PersonContainer {
	
	private static PersonContainer myInstance = null;
	
	public static PersonContainer getInstance()
	{
		if( myInstance == null )
		{
			myInstance = new PersonContainer();
		}
		
		return myInstance;
	}
	
	protected Map<Long, Person> container;
	
	protected AtomicLong idGen;
	
	private PersonContainer()
	{
		this.container = new HashMap<Long, Person>();
		this.idGen = new AtomicLong();
	}
	
	public long save( Person person )
	{
		final long nextId = this.idGen.getAndIncrement();
		
		person.setId(nextId);
		this.container.put(nextId, person);
		
		return nextId;
	}
	
	public Collection<Person> values()
	{
		return this.container.values();
	}
	
	
	public Person load( long id )
	{
		return this.container.get(id);
	}
}
