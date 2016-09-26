/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//class Entities
class Entities extends Property{
	private String type;
	private String size;
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setSize(String size){
		this.size = size;
	}
	
	public String getType(){
		return type;
	}
	
	public String getSize(){
		return size;
	}
	
	public void printData(){
		System.out.println("<<----------------    ENTITIES  ----------------->>");
		System.out.println(toString());
		System.out.println("<<--------------------------------------------->>\n");
	}
	
	public String toString(){
		String details = super.toString() + "\nOther Details\n~~~~~~~~~~~~~" +
						"\nENTITIES TYPE: " + type + 
						"\nENTITIES SIZE: " + size;
		return details;
	}
}
