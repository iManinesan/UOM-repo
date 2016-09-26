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
public class StoreCounterReader extends RfidReader
{
	private String readerId;
	private double price;
	
	StoreCounterReader(String theName, String theReaderId, double thePrice)
	{
		name = theName;
		readerId = theReaderId;
		price = thePrice;
	}
	
	public void performRead(Garment theGarment)
	{
		System.out.println("The storecounter reader is reading the tag.......");
		System.out.println("It is a " + theGarment.getName());
		price = theGarment.getPrice();
		performOperation();
	}
	
	public void performOperation()
	{
		System.out.println("Price of the garment is " + price);
		System.out.println("Printing the caash receipt...\n");
	}
}
