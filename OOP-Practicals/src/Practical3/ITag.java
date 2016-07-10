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
public class ITag
{
	//create attributes of ITag class
	String color;
	String status;
	String name;
	String location;
	static ITag myperson[] = new ITag[4];
	
	
	ITag( String name, String status, String color)//Create constructor
	{
		this.name = name;
		this.status = status;
		this.color = color;
	}
	
	public void setName( String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setColor( String color)
	{
		this.color = color;
		
	}
	
	public String getColor()
	{
		if ( color.equals("Red"))
		return " is getting away from u....";
		else
		return " is getting close with u....";
	}
	
	public void setStatus( String status)
	{
		this.status = status;
	}
	
	public String getStatus()
	{
		if ( status.equals("Busy")) 
		return " is present in the city but  busy at the moment u cann't find him";
		else
		return " is not in the city";
		
	}
	
	public String transmitLocation(String location)
	{
		this.location = location;
		return location;
	}
	
	public void show()
	{
		System.out.println("Name :" + name + "\n" + name + getStatus() + "\n" + name + getColor() );
	}
	
	public static void  getData(ITag myitag[] )
	{
		for( int m = 0; m < 4; m++)
		{
			myperson[m] = myitag[m];
		}
	}
	
	public void search(String name)
	{
		for ( int i =0;i < myperson.length; i++)
		{
			if ( (myperson[i].getName()).equals(name))
			{
				System.out.println("Name :" + myperson[i].getName()+ "\n" +  myperson[i].getStatus() + "\n" + myperson[i].getColor() +"\n" );
			}
		}
	}
}
