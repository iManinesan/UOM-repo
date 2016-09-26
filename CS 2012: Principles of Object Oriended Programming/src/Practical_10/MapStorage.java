
package Practical_10;

/**
 *
 * @author Home
 */
public class MapStorage {
   //MapStorage should have all the locations of keys.but for demonstation I have only created 5 locations 
    private String[] locations = {"Jaffna", "Anuradhapura","Kandy", "Colombo", "Moratuwa", "UOM"};
	
	public MapStorage(){
		
			
        }
        public String[] getMap(){
          return locations;
        }
       
        public double getKey(String position){
            for(int i=0;i<=locations.length;i++)
                if(position==locations[i])
                        return i;
            return 0;
        }
}
