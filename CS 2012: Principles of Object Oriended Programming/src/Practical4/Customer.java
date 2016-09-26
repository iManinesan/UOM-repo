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
public class Customer
{
	String name;
	Garment boughtItem;
	String telephoneno;
	
	Customer (String theName, Garment theBoughtItem, String theTelephoneNo)
	{
		name = theName;
		boughtItem = theBoughtItem;
		telephoneno = theTelephoneNo;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getBoughtItem()
	{
		return boughtItem.getTag();
	}
}