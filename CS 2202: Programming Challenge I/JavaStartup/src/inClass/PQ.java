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
class PQ { 
public static void main(String[] args) {
int[] ia = {1,5,3,7,6,9,8 }; // unordered data 
PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>();
for(int x : ia) // load queue 
	pq1.offer(x); 
for(int x : ia) // review queue 
	System.out.print(pq1.poll() + " "); 

System.out.println(); 

PQsort pqs = new PQsort(); // get a Comparator PriorityQueue<Integer> 
pq2 = new PriorityQueue<Integer>(10,pqs);

for(int x : ia) // load queue 
	pq2.offer(x);

System.out.println("size " + pq2.size()); 
System.out.println("peek " + pq2.peek()); 
System.out.println("size " + pq2.size()); 
System.out.println("poll " + pq2.poll()); 
System.out.println("size " + pq2.size()); 

for(int x : ia) // review queue
	System.out.print(pq2.poll() + " ");
}

static class PQsort implements Comparator<Integer> { 

 public int compare(Integer one, Integer two) { 
return two - one; 
} 
} 
}
