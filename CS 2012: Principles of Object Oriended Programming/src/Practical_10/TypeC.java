package Practical_10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Home
 */
public class TypeC extends TrackingDevice {
    TypeC(String name){
        super(name);
       }
    
    static int x = 0;
    // To save the path without redundant . To notify after journey
	private SortedMap l = new TreeMap();			
	
    // Path with redundant sections. To notify path during the journey 
	public void addPath(String m)	{
		locations.put(String.valueOf(x), m);			
		x++;
		
		Iterator i = l.keySet().iterator();	
		while (i.hasNext())	{
			Object key = i.next();
			Object value = l.get(key);
			if (value == m)
			{
				while(i.hasNext())	{
					l.remove(i.next());
				}
				return;
			}
		}
		l.put(String.valueOf(x), m);
		x++;	
	}
// Once the destination is reached, the device will tell the path withount redundant sections 
	public void path(){						
		Iterator iterator = l.keySet().iterator();
		System.out.print("You have gone through ");
		while(iterator.hasNext()){
			Object key   = iterator.next();
			Object value = l.get(key);
			System.out.print(value +", ");
		}
		System.out.print(" and reached destination finally....\n\n");
	}
        
    
}
