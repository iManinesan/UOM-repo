import java.util.Scanner;
/**
 *
 * @author Linganesan
 */
public class ClassTest {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String name;
        int grade;
        School school=new School("ABC");
        int choice=0;
        
        while(choice!=3){
            System.out.println("\n***********************************************\n");
        System.out.print("Enter your choice\nEnter-1:New addmision\nEnter-2:Get timetable\nEnter-3:Exit\nChoice is:\n");
        choice=scan.nextInt();
        System.out.println("***********************************************");    
            switch(choice){
                case 1:
                    
                    System.out.print("Enter grade(1,2,3,4,5):\n");
                    grade=scan.nextInt();
                    name=scan.nextLine();
                    System.out.print("Enter student name:\n");
                    name=scan.nextLine();
                    school.addStudent(name,grade);
                    school.findTable(name);
                    break;
                
               case 2:
                    name=scan.nextLine();
                    System.out.println("Enter student name:\n");
                    name=scan.nextLine();
                    school.findTable(name);
                    break;     
            
               case 3:
                   System.exit(0);
                   break;
            }
        }
            
             
            
    }
}
