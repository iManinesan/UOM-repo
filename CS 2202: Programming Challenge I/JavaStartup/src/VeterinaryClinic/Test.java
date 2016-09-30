
package VeterinaryClinic;

public class Test {
    public static void main(String[] args) {
        Doctor gobi=new Doctor("Gobi",22);
        
        Animal horse=new Horse();
        Animal dog=new Dog();
        Animal cat=new Cat();
        
        horse.setIll(true);
        gobi.doTreatment(horse);
        
        gobi.doTreatment(cat);
        gobi.doTreatment(dog);
        
        
    }
}
