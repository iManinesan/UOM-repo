
public class TimeTable {
   private String table[][]=new String[8][3] ;
   
   public TimeTable(){
               for(int i=0;i<8;i++)
                    for(int n=0;n<3;n++)
                         table[i][n]="NONE";
                }
   public void setTable(int i,String sub,String tname){
     switch(i){
       
       case 1: 
               table[0][0]="8.00";
               table[0][1]=sub; 
               table[0][2]=tname; 
               break;
        case 2: 
               table[1][0]="8.40";
               table[1][1]=sub; 
               table[1][2]=tname; 
               break;
        case 3: 
               table[2][0]="9.20";
               table[2][1]=sub; 
               table[2][2]=tname; 
               break;
        case 4: 
               table[3][0]="10.00";
               table[3][1]=sub; 
               table[3][2]=tname; 
               break;
        case 5: 
               table[4][0]="10.40";
               table[4][1]=sub; 
               table[4][2]=tname; 
               break;
        case 6: 
               table[5][0]="11.20";
               table[5][1]=sub; 
               table[5][2]=tname; 
               break;
        case 7: 
               table[6][0]="12.00";
               table[6][1]=sub; 
               table[6][2]=tname; 
               break;
        case 8: 
               table[7][0]="12.40";
               table[7][1]=sub; 
               table[7][2]=tname; 
               break;
        
            }
    }
public void getTable(){
        System.out.println("Time\t\tSubject\t\tTeacher");
             for(int j=0;j<=7;j++){
         System.out.println(table[j][0]+"\t\t"+table[j][1]+"\t\t"+table[j][2]);
                              }
        }
   
    
}

   
