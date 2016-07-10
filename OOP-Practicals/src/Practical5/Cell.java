
package practical5;

public class Cell  {
    private Data data;
    private String id;
    
    
	public Cell(String x) {
            
            this.id = x;
		
	}

	public void setValue(double value) {
                    data = new Numeric(value);
	}

	public void setValue(String value) {
		if(value.charAt(0)=='=') {
			data = new Functional(value);
		} else {
			data = new Text(value);
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
         public static void main(String[] args) {
        Cell c=new Cell("3");
        c.setValue("=mana");
        c.displayValue();
             System.out.println(c.getId());
        
       
        
    }
}
