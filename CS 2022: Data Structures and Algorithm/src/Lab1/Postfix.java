package Lab1;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Postfix {

	private int [] stack;
	private int top;

	public Postfix(int n){
		this.stack = new int[n];
		this.top = 0;
	}

	public void push(int o) throws Exception{
		if(top == stack.length){
			throw new Exception ("stack overflow");
		}
		else{
			stack[top] = o;
			top++;
		}
	}

	public int pop() throws Exception{
		if(top == 0){
			throw new Exception("stack is empty");
		}
		else{
			int obj = stack[top - 1];
			top--;
			return obj;

		}


	}


	public static void main(String[] args) throws Exception {
		//java.util.Lab1.Stack<Integer>p=new java.util.Lab1.Stack<Integer>();
		FileReader r=new FileReader("C://Users//Home//Desktop//Test.txt");
		BufferedReader reader = new BufferedReader(r);
		String string = new String();
		String s = new String();
		s=reader.toString();
		while((string = reader.readLine()) != null){
			s += string;
		}

		Postfix p = new Postfix(s.length());
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);

			if(isDigit(c)){
				p.push(new Integer(Character.digit(c,10)));
			}
			if (isOperator(c)){
				int a=p.pop();
				int b=p.pop();
				int result=calculate(b,a,c);
				p.push(result);
			}
		}

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C://Users//Home//Desktop//Test.txt", true)));
			out.println("= "+p.pop());
			out.close();
		} catch (IOException e) {
			//exception handling left as an exercise for the reader
		}
	}
	static int calculate (int a,int b,char op) throws Exception{
		int result=0;
		switch(op){

			case '+':
				result = a + b;
				break;
			case '-':
				result = a - b;
				break;
			case '*':
				result = a * b;
				break;
			case '/':
				result = a / b;
				break;
		}
		return result;
	}

	static boolean isDigit(char c){
		return (c>= '0' && c<='9');
	}
	static boolean isOperator(char c){
		return (c== '+'||c=='-'||c=='/'||c=='*');
	}

}