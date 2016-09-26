/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//Abstract class VisibleAssets
abstract class VisibleAssets extends Property{
	private Double value;
	
	public void setValue(Double value){
		this.value = value;
	}
	
	public Double getValue(){
		return value;
	}
	
	public String toString(){
		String details = super.toString() + "\nOther Details\n~~~~~~~~~~~~~"  +
						"\nVALUE: " + value;
		return details;
	}
}
