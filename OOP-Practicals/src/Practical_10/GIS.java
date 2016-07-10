
package Practical_10;

import java.util.HashMap;

/**
 *
 * @author 120337H
 */
public class GIS {
    private static HashMap mapTable = new HashMap();	
    private static MapStorage  storage = new MapStorage();
    private String traveler;
    
        GIS(String name){
            traveler=name;
        }
	
	
	// Method to add Locations to the map structure 
	public static void addLocations(String position) {	
         try{   double key=storage.getKey(position);
            mapTable.put(key, position);
            }catch(NullPointerException ex){}
         }
       
        //To get locations when key is given 
	public static Object getLocations(double key) {	
		return mapTable.get(key);
	}
}
