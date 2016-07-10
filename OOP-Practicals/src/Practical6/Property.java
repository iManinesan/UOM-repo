/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//Abstract class Property
abstract class Property implements Data{
	private Person owner;
	
	public Person getOwner(){
		return owner;
	}
	
	public void setOwner(Person owner){
		this.owner = owner;
	}
	
	public String toString(){
		return "Details of Owner\n~~~~~~~~~~~~~~~~\n" + owner.toString() + "\n";
	}
}


