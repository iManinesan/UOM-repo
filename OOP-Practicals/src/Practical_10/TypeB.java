/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practical_10;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Home
 */
public class TypeB extends TrackingDevice {
    TypeB(String name){
        super(name);
       }
    
    static int x = 0;
	
    // To note the path without the redundant sections
    public void addPath(String l)	{  
        // To iterate through the map 
	Iterator i = locations.keySet().iterator();	
            while (i.hasNext()){
		Object key = i.next();
		Object value = locations.get(key);
		if (value == l)			
			{
				while(i.hasNext())	
                                {// If a redundant path found, remove it during the journey
					locations.remove(i.next()); 		
				}
				return;
			}
		}
		locations.put(String.valueOf(x), l);
		x++;	
	}
    
}
