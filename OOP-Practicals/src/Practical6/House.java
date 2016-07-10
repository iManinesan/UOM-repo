/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//class House
class House extends VisibleAssets{
	private Integer members;
	
	public void setMembers(Integer members){
		this.members = members;
	}
	
	public Integer getMemebers(){
		return members;
	}
	
	public void printData(){
		System.out.println("<<----------------     HOUSE     ---------------->>");
		System.out.println(toString());
		System.out.println("<<--------------------------------------------->>\n");
	}
	
	public String toString(){
		String details = super.toString() +
						"\nNUMBER OF MEMBERS: " + members;
		return details;
	}
}