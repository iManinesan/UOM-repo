/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

/**
 *
 * @author Home
 */
public class LabTest {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        
         //CS2022LinkedList l1=new CS2022LinkedList();
          
            
        /*  l1.init_list();
         
         l1.insert(e1);
         l1.insert(e2);
         l1.insert(e3);
         l1.insert(e4);
         l1.insert(e5);
         
                
        l1.deleteNodeAt(5);
         
        l1.delete(e5);
        l1.delete(e4);
        
        
        l1.insertAt(e4,3);
        l1.insert(e5);
        //l1.insert(e4);
        //l1.insert(e4);
        l1.insertAt(e5, 5);
        l1.deleteNodeAt(5);
        l1.insertAt(e1, 5);
        l1.delete(e1);
        */
       
         //creat stack object 
      
      /*CS2022LinkedStack stk=new CS2022LinkedStack();
        
      stk.init_stack();
      stk.push(e1);
      stk.push(e2);
      //stk.pop();
      stk.displayStack();
    */
      CS2022LinkedQueue<Integer> queue=new CS2022LinkedQueue();     
      queue.displayQueue();
      queue.init_queue();
      queue.enqueue(1);
      queue.enqueue(2);
      queue.enqueue(2);
      queue.enqueue(3);
      queue.displayQueue();
      
      queue.dequeue();
      queue.displayQueue();
      
      queue.dequeue();
      queue.displayQueue();
      
      
      
    }
    
}
