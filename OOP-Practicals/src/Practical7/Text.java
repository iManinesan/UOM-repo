
package Practical7;

public class Text extends Data{
    	private String data;

	
    public Text(Column col,String tcolumn,String value) {
             super(tcolumn,col);
		data = value;
	}

     /**
     *
     * @return String
     */
    @Override
	public String getOutput() {
		return data;
	}
}
