/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;

import com.pubnub.api.*;
import java.util.logging.Level;
import org.json.*;

public class Connect {
    private Pubnub pubnub;
    
    public Connect(){
        pubnub = new Pubnub("sub-c-fe1e4ca4-dbdb-11e3-a226-02ee2ddab7fe", "sub-c-fe1e4ca4-dbdb-11e3-a226-02ee2ddab7fe");
        try {
            pubnub.subscribe("hello world", new Callback() {
                
                @Override
                public void successCallback(String string, Object o) {
                    System.out.println(o);
                }
                
                @Override
                public void connectCallback(String string, Object o){
                    pubnub.publish("hello_world", "Hello World !", new Callback() {

                        @Override
                        public void successCallback(String string, Object o) {
                            System.out.println(o);
                        }
                    });
                }
            });
        } catch (PubnubException ex) {
            java.util.logging.Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
