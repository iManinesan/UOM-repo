/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//class Demographic
class Demographic extends Person implements Data{
	private String employmentStatus;
	private Double income = 0.0;
	
	public void setEmploymentStatus(String status){
		this.employmentStatus = status;
	}
	
	public void setIncome(Double income){
		this.income = income;
	}
	
	public String getEmploymentStatus(){
		return employmentStatus;
	}
	
	public Double getIncome(){
		return income;
	}
	
	public void printData(){
		System.out.println("<<---------------- DEMOGRAPHIC ---------------->>");
		System.out.println(toString());
		System.out.println("<<--------------------------------------------->>\n");
	}
	
	public String toString(){
		String details = super.toString() +
						"\nEMPLOYMENT STATUS: " + employmentStatus +
						"\nINCOME: " + income;
		return details;
	}
}