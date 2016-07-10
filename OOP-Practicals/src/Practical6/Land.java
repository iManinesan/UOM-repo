/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//class Land
class Land extends VisibleAssets{
	public Double area;
	
	public void setArea(Double area){
		this.area = area;
	}
	
	public Double getArea(){
		return area;
	}
	
	public void printData(){
		System.out.println("<<----------------      LAND     ---------------->>");
		System.out.println(toString());
		System.out.println("<<--------------------------------------------->>\n");
	}
	
	public String toString(){
		String details = super.toString() +
						"\nAREA: " + area +" acres";
		return details;
	}
}
