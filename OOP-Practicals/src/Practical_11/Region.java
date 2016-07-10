package Practical_11;



/**
 *
 * @author Home
 */
public class Region {
    final int COLUMN_MAX_SIZE=5;
    /* Region class inialize  with two Attributes 
     * An array of Columns and RegionId(String)
    */
    private Column[] column;
    private int regionID;
    
    /*Constractor of Region
     * for inializing array size of Column
     *assign a parameter id to regionID 
    */
    
    Region(int id){
       regionID=id;
       this.column = new Column[COLUMN_MAX_SIZE];
       for(int i=0;i<10;i++){
          try{ column[i]=new Column(String.valueOf((char)(i+65)),this,regionID);
          }catch(ArrayIndexOutOfBoundsException a){}
          }
      }
   public Column getColumn(String columnID){
        int i; 
       for(i=0;i<column.length;i++){
               try{ 
                   if(column[i].getId().equals(columnID))
                    break;
                 }catch(Exception ex){}   
                }
        try{
            return column[i];
            }catch(ArrayIndexOutOfBoundsException ex){
         return null;}
            }
  
   public void setCell(String id,String value){
       for(int i=0;i<column.length;i++){
           if(column[i].getId().equals(String.valueOf(id.charAt(0)))){
                  column[i].setCell(id, value);
                  break;
                }
        }
   }
     
     public void setCell(String id,double value){
         for(int i=0;i<column.length;i++){
           if(column[i].getId().equals(String.valueOf(id.charAt(0)))){
                    column[i].setCell(id, value);
                     break;
                }
            }
        }
        
   public Object getCell(String id){
         for(int i=0;i<column.length;i++){
           if(column[i].getId().equals(String.valueOf(id.charAt(0)))){
                 return column[i].getCell(id);
                  }
               }
        return null;
     }  
   public void displayCell(String id){
       for(int i=0;i<column.length;i++){
           if(column[i].getId().equals(String.valueOf(id.charAt(0)))){
                  column[i].displayCell(id);
                  break;
                }
               }
        }
   
    public void displayRegion(){
       System.out.println("\nSheet: "+regionID);
        for(int i=0;i<column.length;i++){
            String leftAlignFormat = "| %-25s  |%n";

            System.out.format("+----------------------------+%n");
            System.out.printf("|     Column "+column[i].getId()+"               |%n");
            System.out.format("+----------------------------+%n");
        for (int k = 0; k < 10; k++) {
            String cellID = column[i].getId().concat(Integer.toString(k+1));
            System.out.format(leftAlignFormat, "Cell:"+cellID +"="+ String.valueOf(column[i].getCell(cellID)));
            }
            System.out.format("+----------------------------+%n");
           }
   }
   
}
    

