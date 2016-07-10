
package Practical7;

public class Column {
    
    /* Column class inialize  with two Attributes 
     * An array of Cells and ColumnId(String)
    */
    private Cell[] cell;
    private String columnID;
    /*Constractor of Column
     * for inializing array size of Cell
     *assign a parameter id to ColumnID 
    */
    Column(String id){
       this.cell = new Cell[20];
       for(int i=0;i<20;i++)
           cell[i]=new Cell(null,id,"");
      
       columnID=id;
    }
    
    /*Create a cell by 
     * column reference,Cell array id 
     * and Cell Identity
     */
    public void createCell(Column col,int i,String id){
      this.cell[i]=new Cell(col,columnID,id);
    }
    
    //
     public void setCell(String id,String value){
       for(int i=0;i<cell.length;i++)
          if(cell[i].getId().equals(id))
                 cell[i].setValue(value);
     }
     
      public void setCell(String id,double value){
        for(int i=0;i<cell.length;i++)
                if(cell[i].getId().equals(id))
                        cell[i].setValue(value);
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
                if(cell[i].getId().equals(id))
                        cell[i].displayValue();
     }
     
}
