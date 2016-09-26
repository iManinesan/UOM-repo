/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author Home
 */
public interface Stack {
    public void push(Object element);
    public Object pop();
    public Object peek();
    public boolean isEmpty();
    public int size();
}

