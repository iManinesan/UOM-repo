package Antcolony;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home
 */
public class AntHill {
    private boolean pathOpen;
AntHill() {
  pathOpen = true;
  }
 
public synchronized boolean acquire() {
       if(!pathOpen)
        return false;
        else {
        pathOpen = false;
        return true;
        }
}

public void release() {
if(!pathOpen){
pathOpen = true;
    
}else
System.out.println("I'm a lousy programmer!");
}

}

