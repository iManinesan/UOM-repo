
package practical5;

public class Text extends Data{
    	private String data;

	
    public Text(String value) {
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
