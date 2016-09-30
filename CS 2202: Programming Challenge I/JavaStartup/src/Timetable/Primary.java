

public class Primary extends Class{
   private String index,tname;
    
   private int k=0;
    
    Student[] stu = new Student[40];
    
   TimeTable ctable =new TimeTable();
   
   Subject psub[]= new Subject[8];
    
   Teacher classteacher;
  
   Primary(String tindex,String tname){
        
        this.index=tindex;
        this.tname=tname;
        
       //classteacher = new Teacher("tname");
        
        
      psub[0]=new Subject("Tamil");
      psub[1]=new Subject("Maths");
      psub[2]=new Subject("Science");
      psub[3]=new Subject("Art");
      psub[4]=new Subject("English");
      psub[5]=new Subject("Religion");
      psub[6]=new Subject("Music");
      psub[7]=new Subject("Sinhala");
      
     
      for(int i=0;i<8;i++){
             if(tindex=="grade4")
             ctable.setTable(1,psub[i].subname,tname);
             else 
              ctable.setTable(1,psub[i].subname,tname);    
              }
            
    } 
   
    public void setTable(){
        for(int x=2;x<8;x++)
        {
        //int idx = new Random().nextInt(teacher.length);
        //String random = (fruits[idx]);
        
        
        
        }
    
    
    
    }
    
    
   
    public void setStudent(String sname){
    
        if(k<=40)
               stu[k]=new Student(sname);
         else
            System.out.println("Admission is closed!");
    
        }
    
   
    public void getStudenttable(){
         System.out.println("TimeTable of"+index+"is:");
        ctable.getTable();
    
    
    }
    
    
    
}
