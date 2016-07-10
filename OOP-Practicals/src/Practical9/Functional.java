package Practical9;

public class Functional extends Data {
    private String data;
    
	public Functional(Region region,String columnID,String value) {
                 super(region,columnID);
		 data = value;
	}

  
	public String getOutput() {
		String newdata = data.substring(1);
                String operand1 = "",operand2 = "";
		double num1 = 0.0;
		double num2 = 0.0;
		double answer = 0.0;
		
                if(newdata.indexOf("+") >= 0){
			//Addition
			operand1 =newdata.substring(0,newdata.indexOf("+"));
			operand2 = newdata.substring(newdata.indexOf("+")+1,newdata.length());
		}
		else if(newdata.indexOf("-") >= 0){
			//Substraction
			operand1 = newdata.substring(0,newdata.indexOf("-"));
			operand2 = newdata.substring(newdata.indexOf("-")+1,newdata.length());
		}
		else if(newdata.indexOf("*") >= 0){
			//Multiplication
			operand1 = newdata.substring(0,newdata.indexOf("*"));
			operand2 = newdata.substring(newdata.indexOf("*")+1,newdata.length());
		}
		else if(newdata.indexOf("/") >= 0){
			//Division
			operand1 = newdata.substring(0,newdata.indexOf("/"));
			operand2 = newdata.substring(newdata.indexOf("/")+1,newdata.length());
		}
		else if(newdata.indexOf("CONCAT")>=0){
			operand1 = newdata.substring(7,newdata.indexOf(","));
			operand2 = newdata.substring(newdata.indexOf(",")+1,newdata.length());
                            try{
                                operand1 = super.getRegion().getCell(operand1).toString();
                        	}
                            catch(Exception e){}
                            try{
                            operand2 = super.getRegion().getCell(operand2).toString();
                        	}
                            catch(Exception e){}
                            return operand1 + operand2;
                        }
		else{
                       try{ return super.getRegion().getCell(newdata).toString();
                     }catch(Exception e){}
                }
		
		try{
			num1 = Double.parseDouble(operand1);
                    }
		catch(NumberFormatException ex){
			 try{
                             num1 = Double.parseDouble(super.getRegion().getCell(operand1).toString());
                        }catch(Exception e){}
                }
		
		try{
			num2 = Double.parseDouble(operand2);
		}
		catch(NumberFormatException ex){
			try{
                            num2 =  Double.parseDouble(super.getRegion().getCell(operand2).toString());
                            }catch(Exception e){}
                    }
		
		if(newdata.indexOf("+") >= 0){
			//Addition
			answer = num1 + num2;
		}
		else if(newdata.indexOf("-") >= 0){
			//Substraction
			answer = num1 - num2;
		}
		else if(newdata.indexOf("*") >= 0){
			//Multiplication
			answer = num1 * num2;
		}
		else if(newdata.indexOf("/") >= 0){
			//Division
			answer = num1 / num2;
		}
		
		
		return Double.toString(answer);
	}
    
}
