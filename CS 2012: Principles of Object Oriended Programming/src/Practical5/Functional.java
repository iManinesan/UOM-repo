package practical5;

public class Functional extends Data {
    private String data;
    
	public Functional(String value) {
                 
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
                            
                            return operand1 + operand2;
                        }
		else{
                        return newdata;
                     }
		
	
		num1 = Double.parseDouble(operand1);
               	num2 = Double.parseDouble(operand2);
		
		
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
