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
public class WashingReader extends RfidReader
{
	private String readerId;
	private String washType;
	
	WashingReader(String theName, String theReaderId, String theWashType)
	{
		name = theName;
		readerId = theReaderId;
		washType = theWashType;
	}
	
	public void performRead(Garment theGarment)
	{
		System.out.println("The washing reader is reading the tag");
		System.out.println("It is a " + theGarment.getName());
		washType = theGarment.properWashingCondition();
		performOperation();
	}
		
	public void performOperation()
	{
		System.out.println("Washing type is changed to " + washType + "\n");
	}
}
