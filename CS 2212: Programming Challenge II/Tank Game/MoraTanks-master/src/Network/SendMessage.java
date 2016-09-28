/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class SendMessage {
     private BufferedWriter writer;
     private Socket clientOutputSocket;
     private String serverip;
     private int clientOutputPort;
     private long time = 1200;
     private long startTime = 0;
     
     public SendMessage(String serverip, int clientport){
         this.serverip = serverip;
         this.clientOutputPort = clientport;
         startTime = System.currentTimeMillis();
     }
     
     public boolean sendMessage(String message){
        // char[] a = message.toCharArray();
            try
            {
                clientOutputSocket = new Socket(serverip, clientOutputPort);

                writer = new BufferedWriter(new OutputStreamWriter(clientOutputSocket.getOutputStream()));
                writer.write(message);
                writer.flush();
                writer.close();
      
                clientOutputSocket.close();
                System.out.println(message);
               
 
                return true;
            } 
            catch (IOException ex) {
                Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        return false;
    }
}
