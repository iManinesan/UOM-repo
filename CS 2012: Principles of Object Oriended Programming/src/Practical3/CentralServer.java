/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practical3;

/**
 *
 * @author Home
 */
public class CentralServer
{
	String location;
	ITag itag[] = new ITag[50];//In this case central server only have 50 details but assume It is lrge enough to store details
	int i = 0; 
	int numberOfPersons =0;
	
	public void getLocation(String location)
	{
		this.location = location;
		System.out.println("Last Accessed at " + location);
		
	}
	
	public void addITag( ITag detailsOfPerson)
	{
		itag[i++] = detailsOfPerson;
		numberOfPersons = i;
		System.out.println("The Details are added in centralServer\n");
	}
	
	public ITag[] sentData()
	{
		return itag;
	}
	
	public void removeITag(ITag details, String name)
	{
		for ( int i = 0 ;i < numberOfPersons; i++)
		{
			if ( itag[i].equals(details))
			{
				for( int k = i ;k < numberOfPersons ;k++)
				{
					itag[i] = itag[i+1];
				}
				numberOfPersons--;
				System.out.println("The Details of " + name + " is deleted from centralServer\n");
			}
		}
	}
	
}
