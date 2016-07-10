/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//class Vehicle
class Vehicle extends VisibleAssets{
	private String vechicleNumber;
	
	public void setVechicleNumber(String number){
		this.vechicleNumber = number;
	}
	
	public String getVechicleNumber(){
		return vechicleNumber;
	}
	
	public void printData(){
		System.out.println("<<----------------    VEHICLE    ---------------->>");
		System.out.println(toString());
		System.out.println("<<--------------------------------------------->>\n");
	}
	
	public String toString(){
		String details = super.toString() +
						"\nVECHICLE NUMBER: " + vechicleNumber;
		return details;
	}
}
