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
public class IronReader extends RfidReader
{
	private String readerId;
	private String position;
	
	IronReader(String theName, String theReaderId, String thePosition)
	{
		name = theName;
		readerId = theReaderId;
		position = thePosition;
	}
	
	public void performRead(Garment theGarment)
	{
		System.out.println("The iron reader is reading the tag......");
		System.out.println("It is a " + theGarment.getName());
		position = theGarment.properIronCondition();
		performOperation();
	}
		
	public void performOperation()
	{
		System.out.println("Iron mode is changed to " + position +"\n");
	}
}
