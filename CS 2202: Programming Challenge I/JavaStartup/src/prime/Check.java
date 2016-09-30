/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prime;

/**
 *
 * @author Home
 */
public class Check {
    Check(int s,int e){
        if(e<10){
        System.out.println("invalid input");
        System.exit(0);
        }
    for(int i=s;i<=e;i++){
        for(int j=2;j<i;j++)
            while(i%j>0){
            }    
    }
    
    }
}
