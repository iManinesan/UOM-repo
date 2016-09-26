/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CS3062;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Linganesan
 */
public class DFA {
    DataInputStream dis=new DataInputStream(System.in);
    String[] checkString;
    String[] result;
    State[] s;
    Transition t1=new Transition();
    int number;
    
    public void calculate(){
        State startState=new State();
        State currentState=new State();
    }
    
    
    public void getInput() throws IOException{
        calculate();
        
        t1.initializeStates();
        System.out.println("Enter the number of String do you want check?");
        number=Integer.parseInt(dis.readLine());
        checkString=new String[number];
        result=new String[number];
        for(int i=0;i<number;i++)
            checkString[i]=dis.readLine();
        checkStringState();
    }
    
   
    
    public void checkStringState(){
        
        for(int i=0;i<number;i++){
            State currentState=t1.s[0];
            t1.s[19].setFinal(true);
            
            int j=0;
            while(currentState!=null &&j<checkString[i].length()){
                char next = checkString[i].charAt(j);
                currentState = currentState.next(next);
                j++;
            }
            
            if(currentState!=null && currentState.isFinal()){
                result[i]="accepted";
            }
            else{
                result[i]="rejected";
            }
            
        }
        System.out.println();
        System.out.println("***Accepted String***");
        int k=0;
        for(int i=0;i<number;i++){
            if(result[i].equalsIgnoreCase("accepted")){
                System.out.println(checkString[i]);
                k++;
            }
        }
        if(k==0)
            System.out.println("NO STRING ACCEPTED");
        System.out.println();
        int l=0;
        System.out.println("***Rejected String***");
        for(int i=0;i<number;i++){
            if(result[i].equalsIgnoreCase("rejected")){
                System.out.println(checkString[i]);
                l++;
                
            }
           
        }
        if(l==0)
           System.out.println("NO STRING Rejected"); 
    }
    
}
