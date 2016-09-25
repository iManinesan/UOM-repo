
import java.util.concurrent.Semaphore;

public class Bus extends Thread{


	private Semaphore mutex;
	private Semaphore busBoarding;
	private Semaphore riderBoarded;
	private Semaphore waitForBus;
	private Counter counterOfWaitingRiders, counterOfRemainingRiders;
	private int busInterval;

	public Bus (Counter counterOfWaitingRiders, Counter counterOfRemainingRiders, Semaphore mutex, Semaphore busBoarding, Semaphore riderBoarded,int busInterval){
		this.counterOfWaitingRiders = counterOfWaitingRiders;
		this.counterOfRemainingRiders = counterOfRemainingRiders;
		this.mutex = mutex;
		this.busBoarding = busBoarding;
		this.riderBoarded = riderBoarded;
		this.busInterval = busInterval;
	}

	private void depart(){
		System.out.println("Bus departed with " + counterOfWaitingRiders.getCount() + " riders. Remaining: " + counterOfRemainingRiders.getCount());
	}

	@Override
	public void run(){

		while(true){
				try{
					//mutex for lock the waiting riders counter
					mutex.acquire();
					System.out.println("Bus is boarding Riders");

					for (int i=0; i < counterOfWaitingRiders.getCount();i++){
					//signal the riders who are eligible to ride
						busBoarding.release();
					//Allowed riders who are wating, riderBoarded Semaphore takecare of it
						riderBoarded.acquire();
					}
				}catch (InterruptedException exception){

				}finally{
					//Bus departed, print message
					depart();
					counterOfWaitingRiders.setCount(0);
					//releaset the lock and allow the latecomers to ride next bus
					mutex.release();
						try{
							// next bus will creates in limited time interval
							Thread.sleep(busInterval*1000);
						}catch (InterruptedException exception){}
				}
		}
	}




}
