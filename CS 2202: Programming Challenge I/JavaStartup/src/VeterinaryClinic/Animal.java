/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VeterinaryClinic;

/**
 *
 * @author Home
 */
 public abstract class Animal {
    String name;
    int age;
    boolean Isill;
    
    Animal(){
    this.Isill=false;
    }
    public boolean isIll(){
    return this.Isill;
    }
    
    public void setIll(boolean string){
     this.Isill=string;
    }
    
    public void treatment(){
        System.out.println("Doing treatment....");
        this.Isill=false;
     }
    
}
