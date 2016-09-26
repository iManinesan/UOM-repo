package data_structures;

/**
 *
 * @author 120337H
 */
public class LinkedList<E> {
//head refers first node  
    private Node<E> head;
//var refers current node
    private Node<E> var;
//inialize the length of the linkedlist
    private int length;

//inialize the linked list  
public void init_list(){
    this.length=0;
    head=null;
    System.out.println("List was initialized");
}

//
public void setHead(Node<E> head){
    this.head = head;
}

public Node<E> getHead() {
    return head;
}

public void incrementLength() {
    this.length++;
}

public void decrementLength() {
    this.length--;
}

public int getLength() {
    return length;
}

//Check whether list is empty or not!
public boolean is_empty(){
    if(getLength()==0)
        return true;
    else  
        return false;
}

//Search the given element with the list node elements
public Node<E> search(E element){
    var=head;
     while(var!=null){
        if((var.getElement())==element)
            return var;
        var=var.getNext();
      }
    return null;
}


//Delete the first node if the given element i equals with the head node element
public E delete(E element){
    var=head;
    Node<E> temp=var;
    E delelement;
            
    while(var!=null){
        temp=var; 
        if((var.getElement())==element)
             break;
         var=var.getNext();
      }
    if(var==null){
        System.out.println(element +" is not found");
        return null;    
    }else if(head.getElement()==element){
           delelement=head.getElement();
           head=var.getNext();
           decrementLength();
           System.out.println(delelement+" is Succesfully remove");
           return delelement;
       
       }else{
        delelement=var.getElement();
        temp.setNext(temp.getNext().getNext());
        decrementLength();
        System.out.println(delelement+" is Succesfully remove");
        return delelement;
    }
    
}

//Delete the pointed node that indicated by i'th position
public E deleteNodeAt(int i){
       
        Node<E> tmp=head;
    int count=length;
    if(i<getLength()){
            while(i!=(count-1)){
                tmp=tmp.getNext();
                count--;
               }
            
            var=tmp.getNext();
            E element=var.getElement();
            tmp.setNext((tmp.getNext()).getNext());
            decrementLength();
            System.out.println(var.getElement()+" is Succesfully remove at loacation "+i);
            return element;
            
        }else if(i==getLength()){
            head=tmp.getNext();
            decrementLength();
            System.out.println(tmp.getElement()+" is Succesfully remove at loacation "+i);
            return tmp.getElement();
        }else
             return null;
    
}

public void insert(E element){
    var=new Node<E>();
    var.setElement(element);
    var.setNext(head);
    setHead(var);
    System.out.println(var.getElement()+ " is succesfully insert into the list");
    incrementLength();
    }

public void insertAt(E element, int i){
    Node<E> tmp=head;
    int count=length;
    var=new Node<E>(); 
    
    if(i==getLength()){
            insert(element);
                      
      }else if(i<getLength()){
           while(i!=(count-1)){
                tmp=tmp.getNext();
                count--;
               }
            var.setElement(element);
          try{
              var.setNext((tmp.getNext()).getNext());
          }catch(NullPointerException ex){
          }
            
          try{
              tmp.getNext().setNext(var);
          }catch(NullPointerException ex){
          }
          
            incrementLength();
            System.out.println(var.getElement()+" is Succesfully insert at location "+i);
            displayList();
      }else{
         
          try{
                while(tmp.getNext()!=null){
                    tmp=tmp.getNext();
                    }
          }catch(NullPointerException ex){
                    
          }
            var.setElement(element);
            var.setNext(null);
          try{  
            tmp.setNext(var);
          }catch(NullPointerException ex){
          
          }
            incrementLength();
            
            System.out.println("Location "+ i+ " is higher than actual number of elements in the list");
            System.out.println(var.getElement()+" is Succesfully insert at the end of the List");
            
           }
}
 
public void displayList(){
    var=head;
    System.out.println("**********List************");
    while(var!=null){
        System.out.print(var.getElement() +" -> ");
        var=var.getNext();
        }
    System.out.println(var);
    System.out.println("*********************************\n");
    }
}
