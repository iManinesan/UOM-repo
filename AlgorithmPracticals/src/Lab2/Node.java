/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

/**
 *
 * @author Home
 */
public class Node<E> {
    private E element;
    private Node<E> next;
    
public void setElement(E element) {
    this.element = element;
}

public E getElement() {
    return element;
}

public void setNext(Node<E> next) {
    this.next = next;
}

public Node<E> getNext() {
    return next;
}   
}
