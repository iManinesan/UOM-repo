
package Practical7;

/*
 * An abstract class of Data class
 * methods of this class(getOutput)
 * can be overide 
 */
public abstract class Data {
    /*
     *Class Data Attributes are
     * Column Identity(colId:String)
     * and Column reference(col:Column)
     */
    private String colId;
    private Column col;
    
    /*
     * Constractor of Data class
     * assign  Column Identity
     * and Column reference
     */
    Data(String tcolumn,Column col){
    this.colId=tcolumn;
    this.col=col;
    }
    
    //Abstract method
    public abstract Object getOutput();
    
    //Method to get Current Column reference
   public Column getColumn(){
     return col;
        }
    
}
    
    

