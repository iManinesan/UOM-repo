import java.util.concurrent.Semaphore;
import java.util.Scanner;
public class Simulation {

  //Initialize a mutex for prevent the waiting riders count
	private static Semaphore mutex = new Semaphore(1);
  //A Semaphore for riders to invoke boardBus
  private static Semaphore busBoarding = new Semaphore(0);
  //A Semaphore for keeps track of how many riders are waiting
	private static Semaphore riderBoarded = new Semaphore(0);
	//A Semaphore for makes sure there are no more than 50 riders in the boarding area
	private static Semaphore waitForBus = new Semaphore(50);
  //A counter for get the number of waiting riders
	private static Counter counterOfWaitingRiders = new Counter(0);
	//A counter for get the number of remaining riders
	private static Counter counterOfRemainingRiders = new Counter(0);
	//Initialize the parameters for user inputs
	private static int numberOfRiders,busInterval, riderInterval;

	public static void main(String[] args){
		int count =0;
		//get user inputs
		System.out.println("**************************************");
		System.out.println("CS4532 Concurrent Programming: Lab 3");
		System.out.println("**************************************");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter number of initial Riders: ");
		numberOfRiders = scanner.nextInt();
		System.out.print("Enter the bus inter-arrival time(seconds): ");
		busInterval = scanner.nextInt();
		System.out.print("Enter the rider inter-arrival time(seconds): ");
		riderInterval = scanner.nextInt();

		counterOfRemainingRiders.setCount(numberOfRiders);

		System.out.println('\n'+"The Senate Bus problem simulation is begin"+'\n');
		//Initialize the intial riders
		for (int i=0; i<numberOfRiders; i++){
			new Rider (counterOfWaitingRiders,counterOfRemainingRiders,mutex,busBoarding,riderBoarded,waitForBus,i).start();
		}
		//Start the bus thread
		Bus bus = new Bus (counterOfWaitingRiders,counterOfRemainingRiders,mutex,busBoarding,riderBoarded,busInterval);
		bus.start();

		count = numberOfRiders;

		//Create new rider per rider interval
		while(true){
		try{
			Thread.sleep(riderInterval*1000);
		}catch (InterruptedException exception){}
		finally{
			new Rider (counterOfWaitingRiders,counterOfRemainingRiders,mutex,busBoarding,riderBoarded,waitForBus,count).start();
			count+= 1;
			counterOfRemainingRiders.incrementCount();
		}
	}

	}
}
