
package Practical_11;


public class Column {
    final int CELL_MAX_SIZE=10;
    /*
    /* Column class inialize  with four Attributes 
     * An array of Cells 
     * ColumnId(String)
     * regionId(String)
     * and Reference of Region
    */
    private Cell[] cell;
    private String columnID=null;
    private int regionID=0;
    private Region region=null;
    
    /*Constractor of Column
     * for inializing array size of Cell
     *assign a parameter columnID to ColumnID
     * assign a parameter regionID to this regionID
    */
    Column(String columnID,Region region,int r_id){
       this.cell = new Cell[CELL_MAX_SIZE];
       this.columnID=columnID;
       regionID=r_id;
       this.region=region;
     try{  
       for(int i=0;i<10;i++){
          String t_id = columnID.concat(String.valueOf(i+1));
           this.cell[i]=new Cell(this.region,columnID,t_id);
      }
      }catch(NullPointerException ex){}
       
       
    }
  //  public void createCell(Region region,String c_id ,String id,int i){
   //   this.cell[i]=new Cell(region,c_id,id);
   // }
    public void setCell(String id,String value){
       for(int i=0;i<cell.length;i++)
         try{ if(cell[i].getId().equals(id))
                 cell[i].setValue(value);
          }catch(Exception ex){}
         }
     
      public void setCell(String id,double value){
        for(int i=0;i<cell.length;i++)
                try{ if(cell[i].getId().equals(id))
                         cell[i].setValue(value);
                }catch(Exception ex){}
     }
     
       public Object getCell(String id){
        int i;
        for(i=0;i<cell.length;i++){
                if(cell[i].getId().equals(id)){
                    break;
                   }
        }
        return cell[i].getCell();
     }
    
    public void displayCell(String id){
        for(int i=0;i<cell.length;i++)
              try{  if(cell[i].getId().equals(id))
                        cell[i].displayValue();
               }catch(Exception ex){}
     }
    
    public String getId(){
          return columnID;
        }
     
}
