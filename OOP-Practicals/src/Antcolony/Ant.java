package Antcolony;


public class Ant extends Thread{
   private int id;
   private AntHill mound;
   boolean outside;
   
public Ant(int id, AntHill amound){
   this.id =id;
   this.mound=amound;
   this.outside=true;
    System.out.println("Ant "+id+" Starts");
 }
    
public void run(){
while (outside){
    System.out.println("Ant "+id+" try to enter:");
    tryToEnter();
}

}
 private void tryToEnter() {
if(mound.acquire()) {
    outside = false;
    System.out.println("Ant no "+id+" is in");
    mound.release();
}
 }

 private void tryToOut(){


}

    
 
}

