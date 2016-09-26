
package Practical9;

/**
 *
 * @author 120337H
 */
public class SpreadSheetTest {

    public static void main(String[] args) {
       
       //Create a Region  
      Region sheet1= new Region(1);
    
       sheet1.setCell("A1", 4);
       sheet1.setCell("A2", 2);
       sheet1.setCell("A3","Linganesan");
       sheet1.setCell("A4","120337H");
       sheet1.setCell("B1","=A1");
       sheet1.setCell("A6","=B1+A2");
       sheet1.setCell("B3","=CONCAT A3,A4");
       sheet1.setCell("B2", 2);
       sheet1.setCell("A8","=A1+B1");
       sheet1.displayCell("A8");
       sheet1.displayCell("A6");
       sheet1.displayCell("B3");
             
       sheet1.displayRegion();
                      
     } 
      
}
