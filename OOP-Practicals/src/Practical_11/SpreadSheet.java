
package Practical_11;

import java.util.ArrayList;



/**
 *
 * @author 120337H
 */
public class SpreadSheet {
    private ArrayList<Region> program = new ArrayList<Region>();
    int sheetNo=0;
   
    
    public void createNewSheet(){
	program.add(new Region(sheetNo++));
    }
	
	
	
    public Region copy(int sheetNumber){
	Region reg = program.get(sheetNumber);
	return reg;
    }
	
    public Region cut(int sheetNumber){
	Region reg = program.get(sheetNumber);
	program.add(sheetNumber, new Region(sheetNumber));
	return reg;
    }
	
    public void paste(int sheetNumber, Region source){
		program.add(sheetNumber, source);
    }
	
	
	
	public void displayRegion(int sheetnumber){
        Region reg = program.get(sheetnumber-1);
        reg.displayRegion();
        }
	
	public void setValue(int sheetNumber, String address, String value){
		program.get(sheetNumber-1).setCell(address,value);
	}
	
	public void setValue(int sheetNumber, String address, double value){
		program.get(sheetNumber-1).setCell(address, value);
	}
	
	public void getValue(int sheetNumber, String address){
		program.get(sheetNumber-1).displayCell(address);
	}
	
	
 
      
}
