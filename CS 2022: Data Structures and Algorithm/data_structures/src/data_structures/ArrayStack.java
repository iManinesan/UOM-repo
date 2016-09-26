/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author Home
 */
public class ArrayStack implements Stack {
    private E a[];
    private int top;
    
    //add an element on the top of satck
    public void push(E element){
    if(top==a.length-1){
     System.out.println("Stack is overflow");
     }
    top++;
    a[top]=element;
    }
public E pop(){
if(isEmpty()){
System.out.println("Stack is underflow");
return null;
}
E element=a[top];
top--;
return element;

}

public E peek(){
if(isEmpty()){
return null;
}
return a[top];
}

public boolean isEmpty(){
return (top==-1); }

public int size(){
return top+1;
}

}

