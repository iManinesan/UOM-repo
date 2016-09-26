
package Practical_10;

/**
 *
 * @author Home
 */
public class Traveler {
    private static String name;
   public static void main(String[] args)	{
                
                TrackingDevice ta = new TypeA(name);
		System.out.println("Travelling with a Tracking Device of type A");
		ta.setLocations(0, 5);
		ta.changeLocation(1);
		ta.changeLocation(2);
		ta.getPath();
		ta.changeLocation(3);
		ta.changeLocation(5);

		TrackingDevice tb = new TypeB(name);
		System.out.println("Travelling with a Tracking Device of type B");
		tb.setLocations(0, 5);
		ta.changeLocation(1);
                tb.changeLocation(2);
		tb.changeLocation(3);
                tb.changeLocation(2);
		tb.changeLocation(4);
                tb.changeLocation(3);
		tb.getPath();
		tb.changeLocation(5);

		TrackingDevice tc = new TypeC(name);
		System.out.println("Travelling with a Tracking Device of type C");
		tc.setLocations(0, 5);
		tc.changeLocation(1);
		tc.changeLocation(2);
		tc.changeLocation(3);
                tc.changeLocation(2);
		tc.getPath();
		tc.changeLocation(5);		
	}
    
}
