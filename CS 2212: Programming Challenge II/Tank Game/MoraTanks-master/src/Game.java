
import AIII.BotInterface;
import MapDetails.Map;
import MessageInterpretations.ReceiveInterpretation;
import MessageInterpretations.SendInterpretation;
import Network.ConnectServer;
import Network.Testing;
import States.ConnectionError;
import States.GameOpening;
import States.GameOver;
import States.GamePlaying;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
//import 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class Game extends StateBasedGame{
    public static void main(String[] args) {
        //Initalizing window size and putting the icon 
        try{
            AppGameContainer agc = new AppGameContainer(new Game());
            agc.setDisplayMode(1300, 700, false);
            agc.setIcon("Images/tankIcon.png");
            agc.setShowFPS(false);
            agc.setSoundOn(true);
            
            agc.start();
        }
        catch(SlickException e){
            e.printStackTrace();
        }
    }

    public Game(String name) {
        super(name);
    }

    public Game() {
        super("Mora Tanks");
    }

    @Override
    public void initStatesList(GameContainer gc) {
        try{
            Map map= new Map();
          
            BotInterface bi=new BotInterface();
            
            ReceiveInterpretation ri = new ReceiveInterpretation(this, map, bi);
            ConnectServer cs = new ConnectServer(this, ri);
            System.out.println("Connected to server");
            SendInterpretation si = new SendInterpretation(cs, this);
            bi.setStringGenerator(si);
            addState(new GameOpening(cs, si));
            addState(new GamePlaying(map));
            addState(new GameOver());
            addState(new ConnectionError());
           
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
