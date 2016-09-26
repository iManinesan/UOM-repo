package Lab1;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class Stack<E> {
    private E a[];
    private int top;

    //add an element on the top of satck
    public void push(E element){
        if(top==a.length-1){
            System.out.println("Lab1.Stack is overflow");
        }
        top++;
        a[top]=element;
    }
    public E pop(){
        if(isEmpty()){
            System.out.println("Lab1.Stack is underflow");
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

