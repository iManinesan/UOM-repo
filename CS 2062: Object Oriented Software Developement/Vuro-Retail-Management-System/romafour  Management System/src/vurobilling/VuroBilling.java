/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vurobilling;

/**
 *
 * @author hp
 */
public class VuroBilling {
 
    public VuroBilling(){
     final SalesHandler sh = new SalesHandler();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerLogin(sh).setVisible(true);
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    
    
}
