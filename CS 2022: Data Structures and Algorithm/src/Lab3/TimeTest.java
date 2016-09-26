/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3;

import java.util.Scanner;

/**
 *
 * @author 120337H
 */
public class TimeTest {
public static void main(String args[]){
    int choice=0;
	while(choice!=-1){
            System.out.println("Select Sort");
            System.out.println("Merge-Sort:Enter-1\nInsertion-Sort:Enter-2\nExit:Enter-3\n");
		Scanner num=new Scanner(System.in);
		choice=num.nextInt();
	switch(choice)
	{
		case 1:
			MergeSort m =new MergeSort();
			break;
		case 2:
			InsertionSort i=new InsertionSort();
			break;
		case 3:
		System.exit(0);
		}	
	}
		
    }
}
       
 
/***************************************************************************************/

/***************************************************************************************/
 