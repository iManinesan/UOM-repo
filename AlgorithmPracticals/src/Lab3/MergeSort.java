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
public class MergeSort {
 public static int elements[];
   
   MergeSort(){
         Random r=new Random();
         System.out.println("Enter the no of elements:");
	 Scanner sc=new Scanner(System.in);
	int x=sc.nextInt();									//scanning the number of elements
	elements=new int[x];
	for(int i=0;i<x;i++){
            elements[i]=r.nextInt(1000);							//scanning to the array list
	}
        System.out.println("before: " + Arrays.toString(elements));
	long startTime = System.currentTimeMillis();

        MERGE_SORT(elements,0,x-1);								//calling merge sort method
	long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time:"+elapsedTime);
       System.out.println("after:  " + Arrays.toString(elements));
  
  }
  
  public  void MERGE_SORT(int A[],int p,int r){
      int q;
      if(p<r){
          q=(p+r)/2;
          MERGE_SORT(A,p,q);
          MERGE_SORT(A,q+1,r);
          MERGE(A,p,q,r);
      }          
      }
      
  public void MERGE(int A[],int p,int q,int r){
      Integer inf = Integer.MAX_VALUE;
      int i,j,k;
      
      int n1 = q-p+1;
      int n2 = r-q;
      
      int L[]=new int[n1+1];
      int R[]=new int[n2+1];
      
      for(i=0;i<n1;i++){
          L[i]=A[p+i];
      }
      
      for(j=0;j<n2;j++){
          R[j]=A[q+j+1];
      }
      
      L[n1]=inf;
      R[n2]=inf;
      
      i=0;
      j=0;
      
      for(k=p;k<=r;k++){
          if(L[i]<=R[j]){
              A[k]=L[i];
              i=i+1;              
          }
          else{
              A[k]=R[j];
              j=j+1;
          }
      }
  }
  }
      

