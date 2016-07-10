
package Practical9;

public class Numeric extends Data{
    private Double data;

	public Numeric(Region region,String columnID,double value) {
                 super(region,columnID);
		data = value;
	}

    /**
     *
     * @return Double
     */
    @Override
	public Double getOutput() {
		return data;
	}
}
