/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inClass;

/**
 *
 * @author Home
 */
import java.util.*; 
public class SetTest { 
public static void main(String[] args) { 
boolean[] ba = new boolean[5]; 
LinkedHashSet s = new LinkedHashSet();

Student s1 =new Student();
s1.index=100;
Student s2 =new Student();
s2.index=100;

ba[0] = s.add("a"); 
ba[1] = s.add(new Integer(42)); 
ba[2] = s.add("b"); 
ba[3] = s.add(s1); 
ba[4] = s.add(s2); 

for(int x=0; x<ba.length; x++) 
	System.out.print(ba[x] + " "); 
System.out.println("\n"); 
for(Object o : s) 
	System.out.print(o + " "); 
} 
}

   class Student{
       public int index;

    public boolean equals(Object obj){
      Student h = (Student) obj; 
    // Don't try without instanceof 
        return this.index==h.index;
        }
    public int hashCode(){
    return index;
    
    }
}

    


