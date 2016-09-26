
package Practical7;

 class Cell  {
    private Data data;
    private String id;
    private String tcolumn;
    private Column col;

	public Cell(Column col, String tcolumn,String x) {
            this.col=col;
            this.tcolumn=tcolumn;
            this.id = x;
		
	}

	public void setValue(double value) {
                    data = new Numeric(col,tcolumn,value);
	}

	public void setValue(String value) {
		if(value.charAt(0)=='=') {
			data = new Functional(col,tcolumn,value);
		} else {
			data = new Text(col,tcolumn,value);
		}
	}

	public void displayValue() {
		System.out.println(data.getOutput());
	}
        
        public Object getCell(){
           return data.getOutput();
        
        }
        
        public String getId(){
          return id;
        }
}
