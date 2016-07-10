package Lab2;

/**
 *
 * @author 120337H
 */
public class CS2022LinkedStack<E> extends CS2022LinkedList<E> {
   int topId;
   Node<E> top,var;
    
    public void init_stack(){
    init_list();
    topId=0;
    top=super.getHead();
    
    }
//public boolean is_empty(){
//}
public boolean push(E element){
    insert(element);
    topId++;
    return true;
}
public E pop(){
      return (super.deleteNodeAt(topId));
}
public void displayStack(){
 var=super.getHead();
    System.out.println("**********Content Of Lab1.Stack************");
    System.out.print("[ ");
    while(var!=null){
        System.out.print(var.getElement()+" ");
        var=var.getNext();
        }
    System.out.print("]\n");
    //System.out.println(var);
    System.out.println("****************************************\n");
}

}
