/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Home
 */
public class InsertionSort {
 int a[];
int index;

    InsertionSort(){
                Random r=new Random();
		System.out.println("Enter the no of elements:");
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();											//scanning the number of elements
		
		a=new int[x];
		for(int i=0;i<x;i++){
			a[i]=r.nextInt(1000);									//scanning to the array list
		}
        System.out.println("before: " + Arrays.toString(a));
		long startTime = System.currentTimeMillis();
		insertionTest();        											//calling merge sort method
		long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time:"+elapsedTime);
	System.out.println("after:  " + Arrays.toString(a));
         }  
  
  public void insertionTest(){
    for(int j=2;j<a.length;j++){
	index=a[j];
	int i=j-1;
	while(i>=0 && a[i]>index)
		{
		a[i+1]=a[i];
		i=i-1;
		}
	a[i+1]=index;
	}
        
  }
  }
