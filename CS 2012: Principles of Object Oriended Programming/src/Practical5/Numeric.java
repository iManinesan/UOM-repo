
package practical5;

public class Numeric extends Data{
    private Double data;

	public Numeric(Double value) {
             
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
