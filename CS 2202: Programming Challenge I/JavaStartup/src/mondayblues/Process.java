/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mondayblues;
import java.util.*;

import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class Process {
    
    
    ArrayList<Calendar> calendar=new ArrayList<Calendar>();
    Calendar temp;
    Process(){
        int j=1;
            
    for(int i=0;i<242;i++){
        if(i<37){
        temp=new Calendar(1,i+1,j);
        calendar.add(temp);
        }else if(i<79){
        temp=new Calendar(2,i+1-37,j);
        calendar.add(temp);
        }else if(i<108){
        temp=new Calendar(3,i+1-79,j);
        calendar.add(temp);
        }else if(i<142){
        temp=new Calendar(4,i+1-108,j);
        calendar.add(temp);
        }else if(i<193){
        temp=new Calendar(5,i+1-142,j);
        calendar.add(temp);
        }else if(i<242){
        temp=new Calendar(6,i+1-193,j);
        calendar.add(temp);
        }
        if(j<6){
        j++;
        } else{
        j=1;
      } 
    }
    }
    
    public void check(int date,int month){
    Iterator<Calendar> i = calendar.iterator(); 
    while (i.hasNext()) { 
       if(month== i.next().month){
        while (i.hasNext()){
            temp=i.next();
        if(date==temp.date){
            System.out.println(temp.day);
            
            }
         }
       } 

} 

    }
}
