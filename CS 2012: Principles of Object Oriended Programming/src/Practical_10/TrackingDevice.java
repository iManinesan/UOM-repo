package Practical_10;


import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Home
 */
public class TrackingDevice {
    MapStorage map=new MapStorage();
    String[] currentmap;
    
    public   TrackingDevice(String name){
        currentmap=map.getMap();
        GIS gis=new GIS(name);
        for(int i=0;i<=currentmap.length;i++){
                  try{  GIS.addLocations(currentmap[i]);
                     }catch(ArrayIndexOutOfBoundsException ex){}
                }

}
    protected double initialPosition, finalPosition, key;
	protected SortedMap locations = new TreeMap(); // To save the paths of a journey 
	
	private static int x = 0;
	protected String place;
        
	// Setting initail and final locations in device
	public void setLocations(double initial, double Final)	{
		initialPosition = initial;
		key = initial;
		place = String.valueOf(GIS.getLocations(key));
		System.out.println("You are currently in " + place);
		addPath(place);
		finalPosition = Final;
	}
	
	public void getLocation()	{								/* When position of device changed.it will inform you where you are */
		String place = String.valueOf(GIS.getLocations(key));
		System.out.println("You are currently in " + place);
		addPath(place);
	}
	
        // Saving paths of a journey in sortedMap 
	public void addPath(String place){
		locations.put(String.valueOf(x), place);
		x++;
	}
        // when the poition is changed 
	public void changeLocation(double key)	{
		this.key = key;
		getLocation();
		
		if (key == finalPosition)	
			path();
	}
	
        // When the traveler wants the inforamtion regarding the path in a middle of journey 
	public void getPath()	{	
		Iterator iterator = locations.keySet().iterator();
		System.out.print("You are on the way....You have gone through ");
		while(iterator.hasNext()){
			Object key   = iterator.next();
			Object value = locations.get(key);
			System.out.print(value + ", ");
		}
		System.out.print("\n\n");
	}
	
        // Once the destination point is reached. Device will tell the complete path
	public void path(){
		Iterator iterator = locations.keySet().iterator();
		System.out.print("You have gone through ");
		while(iterator.hasNext()){
			Object key   = iterator.next();
			Object value = locations.get(key);
			System.out.print(value+ ", ");
		}
		System.out.print(" and reached the destination finally\n\n");
	}
}
