package Practical6;


/**
 *
 * @author Home
 */
//File name should be CenusDemo.java
public class CenusDemo{
	private static CenusDevice device = new CenusDevice();
	
	public static void main(String args[]){
		device.loadDatabase();
		//createEntries();
		//device.saveDatabase();
		device.printReport();
	}
	
	private static void createEntries(){
		//Demographic
		Demographic d1 = new Demographic();
		d1.setName("Ravi");
		d1.setSex("Male");
		d1.setAge(19);
		d1.setEmploymentStatus("Student");
		d1.setIncome(0.0);
		
		device.addEntry(d1);	//Add to database
		
		//Demographic
		Demographic d2 = new Demographic();
		d1.setName("Mala");
		d1.setSex("Female");
		d1.setAge(40);
		d1.setEmploymentStatus("Shop Keeper");
		d1.setIncome(60000.00);
		
		device.addEntry(d1);	//Add to database
		
		
		//Entities
		//Create the owner
		Person o1 = new Person();
		o1.setName("Raja");
		o1.setSex("Male");
		o1.setAge(30);
		//......................
		Entities e1 = new Entities();
		e1.setOwner(o1);
		e1.setType("Garments");
		e1.setSize("Small");
		device.addEntry(e1);	//Add to database
		
		
		//House
		//Create the owner
		Person o2 = new Person();
		o2.setName("Malar");
		o2.setSex("Female");
		o2.setAge(28);
		//......................
		House h1 = new House();
		h1.setOwner(o2);
		h1.setValue(80000.00);
		h1.setMembers(5);
		device.addEntry(h1);	//Add to database
		
		
		
		//Vehicle
		//Create the owner
		Person o3 = new Person();
		o3.setName("Nathan");
		o3.setSex("Male");
		o3.setAge(56);
		//......................
		Vehicle v1 = new Vehicle();
		v1.setOwner(o3);
		v1.setValue(12000.00);
		v1.setVechicleNumber("110176D");
		device.addEntry(v1);	//Add to database
		
		//Land
		//Create the owner
		Person o4 = new Person();
		o4.setName("Ragu");
		o4.setSex("Male");
		o4.setAge(42);
		//......................
		Land l1 = new Land();
		l1.setOwner(o4);
		l1.setValue(98000.00);
		l1.setArea(4.2);
		device.addEntry(l1);	//Add to database
	}
}