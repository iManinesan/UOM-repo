
package data_structures;

/**
 *
 * @author 120337H
 */
public class LinkedQueue implements Queue{

    
    class Node
    {
        Object data;
        Node next;
        Node(Object item){
        data=item;
        }
    }
    int count;
   Node front,rear;
 
 @Override
    public boolean isEmpty(){
return (front==null);
}

/**
 * add the item at the end of the queue.
 * It will return true 
 * if the insertion is successful 
 * and false otherwise.
*/
public void enqueue(Object element){
 Node var=new Node(element);
if(front==null){       //queue is empty; insert first element 
    front=rear=var;
    rear.next=null;
}else if(front==rear){  //queue contains one element;insert second element
    rear=var;
    front.next=rear;
    rear.next=null;
}else{      //queue  contains 2 or more elements
rear.next=var;
rear=var;
rear.next=null;
}  
count++;     //increment queue size
}


/**
 * remove the first element in the queue
 * and return it as element 
 * provided that the queue is not empty.
 */
public Object dequeue(){
   if(isEmpty()){
        System.out.println("Queue is empty!");
        return null;             
}
Object data=front.data;
front=front.next;
count--;
return data;

}
@Override
    public Object peek() {
        return front.data;
    }

    @Override
    public int size() {
        return count;
    }
    
public void displayQueue(){
Node var=front;
    System.out.println("**********Content Of Queue************");
    if(var==null)
        System.out.println("Empty");
    while(var!=null){
        System.out.print(var.data+" | ");
        var=var.next;
        }
    System.out.println("\n****************************************\n");
}

}
