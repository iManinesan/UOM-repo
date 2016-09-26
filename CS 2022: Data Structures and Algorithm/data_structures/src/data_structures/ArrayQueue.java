/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_structures;

/**
 *
 * @author Home
 */
public class ArrayQueue implements Queue {

    private int maxSize; //maximum queue size
    private Object[] que; //que is an array
    private int head,tail,count;
    
    public ArrayQueue(int s)
    {
        maxSize=s;
        que=new Object[maxSize];
        head=tail=-1;
        count=0;
    }
    @Override
    public void enqueue(Object obj) {  //add items at tail of queue
   if(count==maxSize){
            System.out.println("Queue is full");
           return;
        }
    if(tail==maxSize-1||tail==-1){
            que[0]=obj;
           tail=0; 
           if(head==-1)  //if firsttime to enqueue
               head=0;
        }   
    else
         que[++tail]=obj;
    count++;  //update queue size
    }
    

    @Override
    public Object dequeue() {
        if(isEmpty()){
            System.out.println("Queue is Empty");
            return null;
        }
        
        Object tmp=que[head]; //save item to be deleted
        que[head]=null;
        if(head==tail)
            head=tail=-1;
        else if(head==maxSize-1)
            head=0;
        else
            head++;
    count--;
    return tmp;
    }
    

    @Override
    public Object peek() {
    return que[head];
    }

    @Override
    public boolean isEmpty() {
    return(count==0);   
    }

    @Override
    public int size() {
    return count;    
    }
    
}
