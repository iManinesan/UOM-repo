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
public class Garment
{
	private String name, rfid_tag;
	double price;
	
	Garment(String theName, String theRfid_tag, double thePrice)
	{
		name = theName;
		rfid_tag = theRfid_tag;
		price = thePrice;
	}
	
	public String properIronCondition()
	{
		if (name.equalsIgnoreCase("Cotton_blouse"))
		{
			return "Strong";
		}
		else if (name.equalsIgnoreCase("Silk_saree"))
		{
			return "weak";
		}
		else
		{
			return "medium";
		}
	}
	
	public String properWashingCondition()
	{
		if (rfid_tag.equalsIgnoreCase("01010101"))
		{
			return "dry wash";
		}
		else if (rfid_tag.equalsIgnoreCase("00001111"))
		{
			return "full wash";
		}
		else
		{
			return "normal wash";
		}
	}
		
	public double getPrice()
	{
		return price;
	}
		
	public String getTag()
	{
		return rfid_tag;
	}
	
	public String getName()
	{
		return name;
	}
}
