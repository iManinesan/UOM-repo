public class Counter{

	private int count;

	public Counter (int initialValue){
		count = initialValue;
	}

	public void setCount (int setValue){
		count = setValue;
	}

	public int getCount(){
		return count;
	}

	public void incrementCount(){
		count++;
	}

	public void decrementCount(){
		count--;
	}
}
