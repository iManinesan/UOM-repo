
package Practical7;

public class Numeric extends Data{
    private Double data;

	public Numeric(Column col,String tcolumn,Double value) {
             super(tcolumn,col);
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
