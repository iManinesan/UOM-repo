/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Practical_11;

/**
 *
 * @author Home
 */
public class Demo {
      public static void main(String[] args) {
       
       //Create a Region  
      SpreadSheet program= new SpreadSheet();
      program.createNewSheet();
       program.setValue(1,"A1",4);
       program.setValue(1,"A2",2);
       program.setValue(1,"A3","Linganesan");
       program.setValue(1,"A4",120);
       program.setValue(1,"B1","=A1");
       program.setValue(1,"A6","=B1+A2");
         program.setValue(1,"B3","=CONCAT A3,A4");
         program.setValue(1,"B2", 2);
         program.setValue(1,"A8","=A1+B1");
        program.getValue(1,"A8");
       program.getValue(1,"A6");
       program.getValue(1,"B3");
          program.displayRegion(1);
      
                      
     }  
}
