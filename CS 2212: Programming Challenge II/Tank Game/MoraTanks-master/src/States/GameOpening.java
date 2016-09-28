/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States;

import MessageInterpretations.SendInterpretation;
import Network.ConnectServer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author DELL
 */
public class GameOpening extends BasicGameState
{   
    private int ID;
    private Image background;
    private Sound backgroundMusic;
    private Input input;
    private ConnectServer cs;
    private Graphics g;
    private SendInterpretation si;
   /* GameOpening 0
      GamePlaying 1
      GameOver 2
      ConnectionError 3
    */
    public GameOpening(ConnectServer cs, SendInterpretation si) {
        ID = 0;
        this.cs = cs;
        this.si = si;
        
    }
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg){
        try{
           background = new Image("Images/Tanks.jpg");
           backgroundMusic = new Sound("Sound/music.wav");
        
           
        }
        catch(SlickException e){
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) {
        try{
            background.draw();
             backgroundMusic.play();
             backgroundMusic.loop();
            grphcs.drawString("Configuration Details", 50, 400);
            grphcs.drawString("Server ip : " + cs.getServerIP(), 50, 450);
            grphcs.drawString("Server port : " + cs.getServerPort(), 50, 500);
            grphcs.drawString("Client port : " + cs.getClientPort(), 50, 550);
            grphcs.drawString("Press Enter to join the game", 50, 600);
            grphcs.setColor(org.newdawn.slick.Color.blue);
            this.g = grphcs;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    //@SuppressWarnings("empty-statement")
    public void update(GameContainer gc, StateBasedGame sbg, int i){
  
    }
    
    public void indicateSituation(String message) {
       Font bFont = new Font("Times New Roman", Font.BOLD, 20);
       TrueTypeFont tFont = new TrueTypeFont(bFont, false);
       tFont.drawString(50, 100, message);
    }
    
     public void keyReleased(int key, char c) {
         if (key == Input.KEY_ENTER) {

            si.join();
        }
    }

}
    

