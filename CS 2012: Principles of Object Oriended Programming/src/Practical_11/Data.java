
package Practical_11;


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
    private String columnID;
    private Region region;
    
    /*
     * Constractor of Data class
     * assign  Column Identity
     * and Column reference
     */
    Data(Region region,String columnID){
    this.columnID=columnID;
    this.region=region;
    }
    
    //Abstract method
    public abstract Object getOutput();
    
    //Method to get Current Column reference
    public Region getRegion(){
        return region;
        }
    
}
    
    

