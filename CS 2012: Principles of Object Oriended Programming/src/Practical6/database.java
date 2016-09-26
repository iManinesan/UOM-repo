/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

/*
* @author 120337H
* Filename CenusDemo.java
*/

import java.io.*;
import java.util.ArrayList;

//class Database
class Database implements Serializable{
	private ArrayList<Data> database = new ArrayList<Data>();
	private int numberOfHouses = 0;
	private	int numberOfVehicles = 0;
	private	int numberOfLands = 0;
	private	int numberOfEntities = 0;
	private	int population = 0;
	private double totalIncome = 0.0;
	private int length = 0;
	
	public void add(Data data){
		if(data instanceof House){
			numberOfHouses++;
		}
		else if(data instanceof Vehicle){
			numberOfVehicles++;
		}
		else if(data instanceof Land){
			numberOfLands++;
		}
		else if(data instanceof Demographic){
			population++;
			totalIncome += ((Demographic)data).getIncome();
		}
		else if(data instanceof Entities){
			numberOfEntities++;
		}
		length++;
		database.add(data);
	}
	
	public Data get(int index){
		Data data = database.get(index);
		return data;
	}
	
	public int length(){
		return length;
	}
	

	public String getReport(){
		String report = "POPULATION: " +	population + "\n" +
							"NUMBER OF HOUSES: " + numberOfHouses + "\n" +
							"NUMBER OF VEHICLES: " + numberOfVehicles + "\n" + 
							"NUMBER OF LANDS: " + numberOfLands + "\n" + 
							"NUMBER OF ENTITIES: " + numberOfEntities + "\n" +
							"AVERAGE INCOME OF PEOPLE: " + (totalIncome / population) + "\n";
		return report;
	}
}





