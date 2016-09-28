/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;

import MessageInterpretations.ReceiveInterpretation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.newdawn.slick.Game;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author DELL
 */
public class ConnectServer {
   
    private Socket clientOutputSocket, clientInputSocket;
    private ServerSocket serverSocket;
    private ReceiveMessage receive;
    private StateBasedGame game;
    private String serverip;
    private int serverPort;
    private int clientPort;
    private PrintWriter writer;
    private ReceiveInterpretation ri;
    private ReceiveMessage rm;
    private SendMessage sm;;
    
   public ConnectServer(Game game, ReceiveInterpretation ri){
       this.game = (StateBasedGame) game;
       serverip = "127.0.0.1";
       serverPort = 6000;
       clientPort = 7000;
       this.ri = ri;
      intializeSend();
       intializeReceive();
       
   }
    
   public void intializeSend(){
       sm = new SendMessage(serverip,serverPort);
       
   }
   
   public void intializeReceive(){
       rm = new ReceiveMessage(clientPort, ri);
       Thread t = new Thread(rm);
       t.start();
   }
   
   public boolean sendMessage(String message){
       boolean reply = sm.sendMessage(message);
       return reply;
   }
  
   public String getServerIP(){
       return serverip;
   }
   
   public int getServerPort(){
       return serverPort;
   }
   
   public int getClientPort(){
       return clientPort;
   }
   
   
}

