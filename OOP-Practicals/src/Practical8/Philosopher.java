/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical8;

/**
 *
 * @author Home
 */
public class Philosopher extends Thread {
 
    private static final int EATING_TIME = 500;
	private static final int SERVING_TIME = 10000;
    private static final int THINKING_TIME = 800;
	private static final int PUTTING_DOWN_TIME = 500;
    private final ChopStick leftChopStick;
    private final ChopStick rightChopStick;
    private final String name;
	private final MiddleBowl middleBowl;

    public Philosopher(String name, ChopStick leftChopStick, ChopStick rightChopStick, MiddleBowl middleBowl) {
        System.out.println(name +" Started");
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.name = name;
		this.middleBowl = middleBowl;
    }

	private void getChopSticks()throws InterruptedException{
		synchronized(leftChopStick){
			leftChopStick.setUsed(true);
			
			if(rightChopStick.isUsed()){
				leftChopStick.setUsed(false);
			}
			else{
				synchronized(rightChopStick){
					rightChopStick.setUsed(true);
					checkBowl();
				}
			}
		}
	}
	
	private void putDownChopSticks()throws InterruptedException{
		synchronized(leftChopStick){
			leftChopStick.setUsed(false);
			System.out.println(name + " has put down left chop stick");
		}
		Thread.sleep(PUTTING_DOWN_TIME);
		synchronized(rightChopStick){
			rightChopStick.setUsed(false);
			System.out.println(name + " has put down right chop stick");
		}
	}
	
	private void checkBowl()throws InterruptedException{
		System.out.println(name + " is checking the middle bowl");
		if(middleBowl.isClosed()){
			serveNoodles();
		}
		else{
			this.eat();
		}
		
		putDownChopSticks();
		think();
	}
	
	private void serveNoodles()throws InterruptedException{
		synchronized(middleBowl){
			middleBowl.openLid();
			middleBowl.getNoodles(30);
			System.out.println(name + " is serving noodles...");
			//Thread.sleep(SERVING_TIME);
			middleBowl.closeLid();
		}
	}
	
	private void eat() throws InterruptedException{
		System.out.println(name + " is eating noodles...");
		Thread.sleep(EATING_TIME);
	}
	
    private void think() throws InterruptedException{
		System.out.println(name + " is thinking");
        Thread.sleep(THINKING_TIME);
    }

    public void run(){
        do{
            try{
                getChopSticks();
				Thread.yield();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
		}while(!middleBowl.isEmpty());

        System.out.println("Succesfully finished: " +name);
    }
   
}
