
package Lab2;

/**
 *
 * @author 120337H
 */
public class CS2022LinkedQueue<E> extends CS2022LinkedList<E>{
    
    int size;
   Node<E> front,rear;
 
//initialize the stack to an empty queue.
    public void init_queue(){
        init_list();
        size=0;
        front=rear=super.getHead();    
    }
    /**
     * check and see if the queue is empty.
     * It will return true if the list is empty
     * and false otherwise.
     *  @return
     */
    @Override
    public boolean is_empty(){
return (front==null);
}

/**
 * add the item at the end of the queue.
 * It will return true 
 * if the insertion is successful 
 * and false otherwise.
*/
public boolean enqueue(E element){
/*
 Node<E> var=new Node<E>();
var.setElement(element);

if(front==null){       //queue is empty; insert first element 
    front=rear=var;
    rear.setNext(null);
}else if(front==rear){  //queue contains one element;insert second element
    rear=var;
    front.setNext(rear);
    rear.setNext(null);
}else{      //queue  contains 2 or more elements
rear.setNext(var);
rear=var;
rear.setNext(null);
}  
size++;     //increment queue size
return true;
*/
super.insert(element);
size++;
return true;
    
}


/**
 * remove the first element in the queue
 * and return it as element 
 * provided that the queue is not empty.
 */
public E dequeue(){
 /*   if(is_empty()){
        System.out.println("Queue is empty!");
        return null;             
}
E data=new E(front.getElement().getNum());
front=front.getNext();
size--;
return data;
*/
 E element=super.deleteNodeAt(1);
 return element;
 
}

public void displayQueue(){
Node<E> var=super.getHead();
    System.out.println("**********Content Of Queue************");
    if(var==null)
        System.out.println("Empty");
    while(var!=null){
        System.out.print(var.getElement() +" | ");
        var=var.getNext();
        }
    System.out.println("\n****************************************\n");
}

}
