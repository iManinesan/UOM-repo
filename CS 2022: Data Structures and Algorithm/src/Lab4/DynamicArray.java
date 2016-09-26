

package Lab4;

import java.util.Arrays;


/**
 *
 * @author Home
 */
public class DynamicArray {
    static int size;
    public int[] array;
    private int count;
    
    DynamicArray(int size){
        this.size=size;
        array=new int[size];
        count=0;
    }
 public void addElement(int element){
  if(!isFull()){
    try{  array[count++]=element;
    }catch(ArrayIndexOutOfBoundsException ex){}
    }else{
     int[] temparray;  
     temparray=createNewArray();
     array=temparray;
     array[count++]=element;
     }
    
 }   

    private boolean isFull() {
        return (count==size-1);
    }

    private int[] createNewArray(){
        int[] newarray=new int[size*2];
        for(int i=0;i<size;i++){
            newarray[i]=array[i];
        }
        return newarray;
    }
    
  public void display(){
  System.out.println("DynamicArray: " + Arrays.toString(array));
  }
  public void getElementSize(){
      System.out.println("No of elements in this DynamicArray: "+count);
  }
}
