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
public interface Queue {
    public void enqueue(Object obj); 
    public Object dequeue(); 
    public Object peek(); 
    public boolean isEmpty(); 
    public int size(); 
}
