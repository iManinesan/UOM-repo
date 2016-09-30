
package VeterinaryClinic;


public class Doctor {
    String name;
    int age;
    Animal animal;
    
    Doctor(String name,int age){
    this.name=name;
    this.age=age;
    }
    
    public void doTreatment(Animal animal){
        if(animal.isIll()){
                System.out.println(animal.name+ " was feeling sick!");
                 animal.treatment();
                 System.out.println(animal.name + " is healthy again");
            }else{
                 System.out.println(animal.name+" is perfectly allright");   
             }
        }
}

