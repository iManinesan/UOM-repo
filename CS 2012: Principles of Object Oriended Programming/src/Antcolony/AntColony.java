package Antcolony;

/**
 *
 * @author Home
 */
public class AntColony {
    private static final int COLONY_SIZE = 10;
    private Ant [] colony;
    private AntHill mound;
    Thread t[]; 
    
public AntColony(){
colony = new Ant[COLONY_SIZE];
mound = new AntHill();
}
 
public void antLife(){
Thread t1 = new Thread(new Ant(1,mound));
Thread t2 = new Thread(new Ant(2,mound));
Thread t3 = new Thread(new Ant(3,mound));
Thread t4 = new Thread(new Ant(4,mound));
Thread t5 = new Thread(new Ant(5,mound));
Thread t6 = new Thread(new Ant(6,mound));
t1.start();
t2.start();
t3.start();
t4.start();
t5.start();
t6.start();

    /*for(int i=0; i<COLONY_SIZE; i++){
   try{
       t[i] = new Thread(new Ant(i,mound));
     }catch(NullPointerException ex){}
   }
for(int j=0; j<COLONY_SIZE;j++)
    try{ 
    t[j].start();
        System.out.println("hgfgh");
     }catch(NullPointerException ex){}
  */
   }

public static void main (String args[]) {
AntColony backyard = new AntColony();
backyard.antLife();
}
}
