
public class School {
    private String schoolname;
    static Student student[]=new Student[200];
    static Teacher teacher[]=new Teacher[8];
    private int s1,s2,s3,s4,s5;
            
            
    private UnderPrimary[] u =new  UnderPrimary[3];  
    private Primary[] p= new Primary[2];
    
    School(String name){
          schoolname=name;
      
    
            u[0]=new UnderPrimary("grade1","BATMAN");
           
            u[1]=new UnderPrimary("grade2","SUPERMAN");
               
            u[2]=new UnderPrimary("grade3","SPIDERMAN");
               
            p[0]=new Primary("Grade4","HULK"); 
               
            p[1]=new Primary("grade5","GOBI");
               
                 teacher[0]=new Teacher("BATMAN");
                 teacher[1]=new Teacher("SUPERMAN");
                 teacher[2]=new Teacher("SPIDERMAN");
                 teacher[3]=new Teacher("HULK");
                 teacher[4]=new Teacher("GOBI");
                 teacher[5]=new Teacher("PETER");
                 teacher[6]=new Teacher("MANI");
                 teacher[7]=new Teacher("SAM");
               
               
   
            s1=0;
            s2=s1+40;
            s3=s2+40;
            s4=s3+40;
            s5=s4+40;
            
            for(int j=0;j<200;j++)
               student[j]=new Student("VACANT");
    }
   
    public void addStudent(String name,int grade){
            
        switch(grade){
        
             case 1:
                 u[0].setStudent("name");
                 student[s1]=new Student("name");
                 s1++;
                 break;
            
             case 2:
                u[1].setStudent("name");
                student[s2++]=new Student("name");
                
                 break;
             case 3:
               u[2].setStudent("name");
               student[s3]=new Student("name");
                 s3++;
                 break;
             case 4:
                 p[0].setStudent("name");
                 student[s4]=new Student("name");
                 s4++;
                 break;
             case 5:    
                p[1].setStudent("name");
                student[s5]=new Student("name");
                 s5++;
                 break;
          
        }
    
    
    }
    public void findTable(String name){
      
        int k=201;
          for(int i=0;i<200;i++){
            if(student[i].stuname==name){
              k=i;
              break;
            }
          }
          if(k!=-1){
              if(k<40)
                 u[0].getStudenttable();
              else if(k<80)
                 u[1].getStudenttable();
              else if(k<120)
                  u[2].getStudenttable();          
              else if(k<160)
                  p[0].getStudenttable();                 
               else                           
                  p[1].getStudenttable();
                  }
    
    }
             
             
}
