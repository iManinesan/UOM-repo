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
abstract class RfidReader
{
	public String name;
	
	abstract public void performRead(Garment theGarment);
	abstract public void performOperation();
}
