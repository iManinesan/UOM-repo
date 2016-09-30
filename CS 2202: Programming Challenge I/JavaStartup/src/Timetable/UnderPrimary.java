
public class UnderPrimary extends Class {
    private String index,tname;
    TimeTable ctable =new TimeTable();
   
    UnderPrimary(String tindex,String tname){
     this.index=tindex;
     this.tname=tname;
    // Teacher classteacher = new Teacher("tname");
    
     Subject usub[]= new Subject[5];
        
        usub[0]=new Subject("Tamil");
        usub[1]=new Subject("Maths");
        usub[2]=new Subject("Science");
        usub[3]=new Subject("Art");
        usub[4]=new Subject("English"); 
        
       
        
        for(int i=0;i<4;i++){
                if(tindex=="grade1")
                ctable.setTable(1,usub[i].subname,tname);
                else if(tindex=="grade2")
                 ctable.setTable(1,usub[i].subname,tname); 
                else 
                 ctable.setTable(1,usub[i].subname,tname); 
                }
    } 
    Student[] stu = new Student[40];
    int k=0;
    
    public void setStudent(String sname){
    
        if(k<=40)
               stu[k++]=new Student(sname);
         else
            System.out.println("Admission is closed!");
    
        }
    
    public void getStudenttable(){
         
        ctable.getTable();
    
    
    }
    
    
    
}
