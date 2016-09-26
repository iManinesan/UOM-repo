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
public class CentralSystem
{
	public static void main(String[] args)
	{
		Garment cotton_blouse = new Garment("Cotton_blouse", "01010101",2500.00);
		Garment silk_saree = new Garment("Silk_saree", "00001111", 3000.00);
		Garment semicotton_pants = new Garment("semicotton_pants", "000I 01111", 2000.00);
		
		/* I should create objects for all the garments which are embedded with RIFT tags.
		I assume that I have created all the objects as above */
		
		Customer Mala = new Customer("Mala", silk_saree, "0779986788");
		Customer Sheela = new Customer("Sheela", cotton_blouse, "07715665587");
		Customer Ramesh = new Customer("Ramesh", semicotton_pants , "07715656894");
		/* I should create objects for all the customers who have bought garments with embedded RIFT tags.For demonstrating purposes
		I have created only 3 customers. I assume that I have created all the objects as above. */
		
		IronReader ir1 = new IronReader("ir1", "0001", "off");
		WashingReader wr1 = new WashingReader("wr1", "1001","normal");
		StoreCounterReader sr1 = new StoreCounterReader("sr1", "1101", 0.0);
		DoorwayReader dr1 = new DoorwayReader("dr1", "1111");
		/* There will be more than one iron_reader, washing_reader, storecounter_reader and the doorway_reader. I assume that I have created all of them*/
		
		sr1.performRead(cotton_blouse);
		wr1.performRead(cotton_blouse);
		ir1.performRead(cotton_blouse);
		dr1.performRead(cotton_blouse);
		dr1.sendSms(Sheela);
		
		sr1.performRead(silk_saree);
		wr1.performRead(silk_saree);
		ir1.performRead(silk_saree);
		dr1.performRead(silk_saree);
		dr1.sendSms(Mala);
		
	}
}
