/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CS3062;

/**
 *
 * @author Linganesan
 */
public class Transition {
    State[] s;
    public void initializeStates(){
        s=new State[20];
       
        for(int i=0;i<s.length;i++)
            s[i]=new State();
        
        s[0].addTransition('a', s[1]);
        s[1].addTransition('b', s[2]);
        s[1].addTransition('c', s[3]);
        s[2].addTransition('d', s[4]);
        s[2].addTransition('e', s[5]);
        s[3].addTransition('d', s[6]);
        s[3].addTransition('e', s[7]);
        s[4].addTransition('f', s[8]);
        s[4].addTransition('g', s[9]);
        s[5].addTransition('f', s[10]);
        s[5].addTransition('g', s[11]);
        s[6].addTransition('f', s[12]);
       // s[6].addTransition('g', s[13]);
        s[7].addTransition('f', s[14]);
        s[7].addTransition('g', s[15]);
        
        s[8].addTransition('h', s[16]);
        s[9].addTransition('h', s[16]);
        s[10].addTransition('h', s[16]);
        s[11].addTransition('h', s[16]);
        s[12].addTransition('h', s[16]);
      //  s[13].addTransition('h', s[16]);
        s[14].addTransition('h', s[16]);
        s[15].addTransition('h', s[16]);
        
        s[16].addTransition('i', s[17]);
        s[16].addTransition('j', s[18]);
        s[17].addTransition('k', s[19]);
        s[17].addTransition('l', s[19]);
        s[18].addTransition('k', s[19]);
        s[18].addTransition('l', s[19]);
        
    }
    
}
