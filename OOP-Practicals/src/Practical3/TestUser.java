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
public  class TestUser
{
	public static void main(String args[])
	{
		//Assume we add all person details who are included in this ITag
		String location[] = new String[4];//Create a location array of each person
		ITag person[] = new ITag[4];//Create a ITag array
		
		//create an object for each person of the array
		person[0] = new ITag( "Annet", "Busy","Red");
		person[1] = new ITag( "Thivya", "Hide","Red");
		person[2] = new ITag( "Venuka", "Busy","Blue");
		person[3] = new ITag( "Priya", "Hide","Red");
		
		// create a location object for each person of the array
		location[0] = person[0].transmitLocation("DehiwalaLK001");
		location[1]=  person[1].transmitLocation("WellawataLK002");
		location[2]=  person[2].transmitLocation("MountlaviniaLK003");
		location[3] = person[3].transmitLocation("bambalapityLK004");
		
		CentralServer centralServer = new CentralServer();//Create a centralServer object
		
		for( int i = 0 ;i < 4 ;i++)
		{
			person[i].show();//call show method to display the details of each person one by one
			centralServer.getLocation(location[i]);//call getLocation method to store the location of each person in centralServer
			centralServer.addITag( person[i]);//add the details to centralServer
		}
	
		ITag.getData( centralServer.sentData());
		
		//search the target person by another person who needs him
		person[0].search("Thivya"); 
		person[1].search("Annet"); 
		
		//remove the person from centralServer list
		centralServer.removeITag( person[3], person[3].getName());
		
	}
}
