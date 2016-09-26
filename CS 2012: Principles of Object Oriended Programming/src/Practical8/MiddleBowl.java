/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical8;

/**
 *
 * @author Home
 */
public class MiddleBowl {
 	private boolean isEmpty = false;
	private boolean isClosed = true;
	private double weightOfNoodles;
	
	public MiddleBowl(double amount){
		weightOfNoodles = amount;
	}
	
	public boolean isEmpty(){
		return isEmpty;
	}
	
	public boolean isClosed(){
		return isClosed;
	}
	
	public void closeLid(){
		isClosed = true;
	}
	
	public void openLid(){
		isClosed = false;
	}
	
	public void getNoodles(double amount){
		double newAmount = weightOfNoodles - amount;
		if(newAmount <=0){
			weightOfNoodles = 0;
			isEmpty = true;
		}
		else{
			weightOfNoodles = newAmount;
		}
	}
   
}
