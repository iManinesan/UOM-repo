
import java.util.concurrent.Semaphore;

public class Rider extends Thread{

	private Semaphore mutex;
	private Semaphore busBoarding;
	private Semaphore riderBoarded;
	private Semaphore waitForBus;
	private Counter counterOfWaitingRiders, counterOfRemainingRiders;
	private int riderId;


	public Rider (Counter counterOfWaitingRiders,Counter counterOfRemainingRiders, Semaphore mutex, Semaphore busBoarding, Semaphore riderBoarded, Semaphore waitForBus, int riderId){
		this.counterOfWaitingRiders = counterOfWaitingRiders;
		this.counterOfRemainingRiders = counterOfRemainingRiders;
		this.mutex = mutex;
		this.busBoarding = busBoarding;
		this.riderBoarded = riderBoarded;
		this.waitForBus = waitForBus;
		this.riderId = riderId;
	}

	private void boardBus(){
		System.out.println("Rider number " + riderId + " boarded the bus");
		counterOfRemainingRiders.decrementCount();
	}

	@Override
	public void run(){

		try{
			// Only 50 riders can wait for a bus to take ride
			waitForBus.acquire();

		} catch(InterruptedException exception){
			System.out.println("Rider Interrupted");
		}

		try{
			//prevent the counter of waiting riders
			mutex.acquire();

		} catch(InterruptedException exception){

		} finally{
			//increase the waiting riders count
			counterOfWaitingRiders.incrementCount();
			mutex.release();
		}

		try{
			// get into the bus
			busBoarding.acquire();

		} catch (InterruptedException exception){

		} finally {
			//increase the waitForBus semaphore to 50 for next riders
			waitForBus.release();
			//rider finish his/her ride
			boardBus();
			//increase the semaphore and allow other riders to wait
			riderBoarded.release();
		}
	}
}
