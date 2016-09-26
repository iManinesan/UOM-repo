/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical6;



import java.io.Serializable;
//class Person
class Person implements Serializable{
	private String name;
	private String sex;
	private Integer age;
	
	public String getName(){
		return name;
	}
	
	public String getSex(){
		return sex;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setSex(String sex){
		this.sex = sex;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	public String toString(){
		String details = "NAME: " + name + 
						"\nSEX: " + sex +
						"\nAGE: " + age + " years";
		return details;
	}
}



