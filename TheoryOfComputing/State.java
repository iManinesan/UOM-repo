/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CS3062;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Linganesan
 */
public class State {
    private Map<Character,State> transition=new HashMap<Character, State>();
    
    public void addTransition(char c1,State s1){
        transition.put(c1,s1);
    }

    public State next(char ch){
        return transition.get(ch);
    }

    private boolean fin=false;
    public boolean isFinal(){return fin;}
    public void setFinal(boolean f){fin=f;}

    
}
