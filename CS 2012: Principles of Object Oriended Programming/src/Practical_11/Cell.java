
package Practical_11;



 class Cell  {
    private Data data;
    private String cellID;
    private String columnID;
    //private Column col;
    private Region region;

	public Cell(Region region,String tcolumn,String cellID) {
            //this.col=col;
            this.region=region;
            this.columnID=tcolumn;
            this.cellID = cellID;
		
	}

	public void setValue(double value) {
                    data = new Numeric(region,columnID,value);
	}

	public void setValue(String value) {
		if(value.charAt(0)=='=') {
			data = new Functional(region,columnID,value);
		} else {
			data = new Text(region,columnID,value);
		}
	}

	public void displayValue() {
		System.out.println(this.getId()+"="+data.getOutput());
	}
        
        public Object getCell(){
          try{ return data.getOutput();
          }catch(NullPointerException ex){
          return null;}
        }
        
        public String getId(){
          return cellID;
        }
}
