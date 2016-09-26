/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;

//class CenusDevice

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CenusDevice{
	Database database = new Database();
	
	public void addEntry(Data data){
		database.add(data);
	}
	
	public void print(int index){
		Data data = database.get(index);
		data.printData();
	}
	
	public void printAll(){
		for(int i = 0; i <= database.length(); i++){
			print(i);
		}
	}
	
	public void printReport(){
		System.out.println("<<----------------    STATISTICAL REPORT  ----------------->>");
		System.out.println(database.getReport());
	}
	
	public void saveDatabase(){
		try{
			FileOutputStream fos = new FileOutputStream("cenusDatabase.db");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(database);
			oos.close();
			fos.close();
			System.out.println("Database is saved successfully.");
		}
		catch(IOException ex){
			System.out.println("Error occured while saving the database");
		}
	}
	
	public void loadDatabase(){
		try{
			FileInputStream fis = new FileInputStream("cenusDatabase.db");
			ObjectInputStream ois = new ObjectInputStream(fis);
			database = (Database)ois.readObject();
			ois.close();
			fis.close();
			System.out.println("Database is loaded successfully.");
		}
		catch(Exception ex){
			System.out.println("Error occured while loading the database");
		}
	}
}
