/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practical4;

/**
 *
 * @author Home
 */
public class DoorwayReader extends RfidReader
{
	private String readerId;
	private String tag;
	
	DoorwayReader(String theName, String theReaderId)
	{
		name = theName;
		readerId = theReaderId;
	}
	
	public void performRead(Garment theGarment)
	{
		System.out.println("The doorway reader is reading the tag........\n Gathering the information of similar products.......");
		System.out.println("It is a " + theGarment.getName());
		tag = theGarment.getTag();
	}
	
	public void sendSms(Customer theCustomer)
	{
		System.out.println("Sending sms to " + theCustomer.getName() + "....." );
		performOperation();
	}
	
	public void performOperation()
	{
		if (tag.equalsIgnoreCase("01010101"))
		{
			System.out.println("Two pair of black shoes available for 10% discount");
		}
		else if (tag.equalsIgnoreCase("00001111"))
		{
			System.out.println("Two pair of brown shoes available for 20% discount");
		}
		else
		{
			System.out.println("Two pair of blue shoes available for 30% discount");
		}
	}
	
	
}
