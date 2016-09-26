
package Practical9;

public class Text extends Data{
    	private String data;

	
    public Text(Region region,String columnID,String value) {
                 super(region,columnID);
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
