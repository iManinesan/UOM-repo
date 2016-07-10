/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical8;

/**
 *
 * @author Home
 */
public class DiningTable {
    private final ChopStick[] chopSticks = new ChopStick[5];
	private MiddleBowl middleBowl;
	
    public DiningTable(){
        putChopsticksOnTheTable();
		middleBowl = new MiddleBowl(100.00);
        Thread t1 = new Thread(new Philosopher("First",this.chopSticks[4],this.chopSticks[0],middleBowl));
        Thread t2 = new Thread(new Philosopher("Second",this.chopSticks[0],this.chopSticks[1],middleBowl));
        Thread t3 = new Thread(new Philosopher("Third",this.chopSticks[1],this.chopSticks[2],middleBowl));
        Thread t4 = new Thread(new Philosopher("Fourth",this.chopSticks[2],this.chopSticks[3],middleBowl));
        Thread t5 = new Thread(new Philosopher("Fifth",this.chopSticks[3],this.chopSticks[4],middleBowl));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
		//System.out.println("Starting...");
    }

    private void putChopsticksOnTheTable(){
        for(int i = 0;i < chopSticks.length;i++)
        chopSticks[i]= new ChopStick(); 
    }
}
