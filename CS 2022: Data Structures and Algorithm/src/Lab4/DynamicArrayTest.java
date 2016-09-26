/*
 * Author 120337H
 */

package Lab4;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class DynamicArrayTest
{
    public static void main(String args[]) throws IOException
    {   Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int choice=-1;
        while(choice!=0){
            System.out.println("**************************************************");
            System.out.println("Enter your choice:");
            System.out.println("Check insertion time of DynamicArray: Enter-1");
            System.out.println("Exit: Enter-0");
            choice = scanner.nextInt();
            switch(choice){
		case 1:
			System.out.println("Enter Array Size : ");
                         int i = scanner.nextInt();
                         DynamicArray da = new DynamicArray(25);
       
                        FileWriter filewriter = new FileWriter("write.txt");
                        long start = System.currentTimeMillis();
                        for(int j = 0; j < i; j++)
                         {
                       da.addElement(random.nextInt(10));
                       filewriter.write((new StringBuilder()).append(random.nextInt()).append("").toString());
                       }
                         da.display();
                      da.getElementSize();
                         long finish = System.currentTimeMillis();
                         long time = finish - start;
                         filewriter.write((new StringBuilder()).append("\n").append(time).append("").toString());
                          filewriter.close();
                         System.out.println("Time taken for this process: "+time);
        
			break;
		case 2:
                        System.exit(0);
		}
        }
        
        
        
        }
    }

