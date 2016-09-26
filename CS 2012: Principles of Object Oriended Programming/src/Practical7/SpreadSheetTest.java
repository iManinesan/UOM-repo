
package Practical7;

/**
 *
 * @author 120337H
 */
public class SpreadSheetTest {

    public static void main(String[] args) {
       
       //Create a column  
       Column A= new Column("A");
       
       /*create cell with method createcell() 
        * with parameters(Column reference,cell array index,Cell Identity )
        */
       A.createCell(A,0,"A1");
       A.createCell(A,1,"A2");
       A.createCell(A,2,"A3");
       A.createCell(A,3,"A4");
       A.createCell(A,4,"A5");
       A.createCell(A,5,"A6");
       A.createCell(A,6,"A7");
       A.createCell(A,7,"A8");
       
       /*Set cell value by 
        * calling cell Identity 
        * and setdata either numeric,texual or functional
        */
       A.setCell("A1", 4);
       A.setCell("A2", 2);
       A.setCell("A3","Linganesan");
       A.setCell("A4","120337H");
       A.setCell("A5","=A1");
       A.setCell("A6","=A5+A2");
       A.setCell("A7","=CONCAT A3,A4");
       
       /*print out Contained data of a cell
        * by calling of that Cell Identity
        */
       A.displayCell("A5");
       A.displayCell("A6");
       A.displayCell("A7");
     } 
      
}
