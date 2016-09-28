/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States;

import MapDetails.Brick;
import MapDetails.Coins;
import MapDetails.LifePack;
import MapDetails.Map;
import MapDetails.Player;
import MapDetails.Stone;
import MapDetails.Water;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
/**
 *
 * @author DELL
 */
public class GamePlaying extends BasicGameState {
    //ID of the state
    private int ID;
    //Image of the Map
    private TiledMap Map;
    private Image score;
    //Image of the brick, stone and water obstacle
    private Image BrickFull;
    private Image BrickQuarter;
    private Image BrickHalf;
    private Image Brick3Quarter;
    private Image Stone;
    private Image Water;
    //Image for the coin and lifepack
    private Image coin;
    private Image lifePack;
    //Image for our Player
    private Image ourPlayer;
    private Image ourPlayer_E;
    private Image ourPlayer_S;
    private Image ourPlayer_W;
    // Images for four enemies
    private Image[] enemy;
    private Image[] enemy_E;
    private Image[] enemy_S;
    private Image[] enemy_W;
    
    // Size of a square in grid
    private int size = 35;
    private Input input;
    private Map map;
    private Font bFont;
    private TrueTypeFont tFont;
    /* GameOpening 0
      GamePlaying 1
      GameOver 2
      ConnectionError 3
    */
    
    /*
    0 (no damage) 
    1 (25% damage)
    2 (50% damage) 
    3 (75% Damage) 
    4 (100% Damage).*/
    
    public GamePlaying(Map map){
        ID = 1;
        this.map = map;
        enemy = new Image[4];
        enemy_E = new Image[4];
        enemy_S = new Image[4];
        enemy_W = new Image[4];
        
    }
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        /* Initalizing the maps brick and stones, players, coins and lifepacks*/
        Map = new TiledMap("Images/Map.tmx");
        score = new Image("Images/score1.jpg");
        BrickFull = new Image("Images/Brick.jpg");
        BrickQuarter = new Image("Images/Brickquarter.jpg");
        BrickHalf = new Image("Images/Brickhalf.jpg");
        Brick3Quarter = new Image("Images/Brick3quarter.jpg");
        Stone = new Image("Images/Stone.png");
        Water = new Image("Images/Water.jpg");
        ourPlayer = new Image("Images/redTank.png");
        ourPlayer_E = new Image("Images/redTankE.png");
        ourPlayer_S = new Image("Images/redTankS.png");
        ourPlayer_W = new Image("Images/redTankW.png");
        
        enemy[0] = new Image("Images/greenTank.png");
        enemy[1] = new Image("Images/blueTank.png");
        enemy[2] = new Image("Images/yellowTank.png");
        enemy[3] = new Image("Images/vTank.png");
        
        enemy_E[0] = new Image("Images/greenTankE.png");
        enemy_E[1] = new Image("Images/blueTankE.png");
        enemy_E[2] = new Image("Images/yellowTankE.png");
        enemy_E[3] = new Image("Images/vTankE.png");
        
        enemy_S[0] = new Image("Images/greenTankS.png");
        enemy_S[1] = new Image("Images/blueTankS.png");
        enemy_S[2] = new Image("Images/yellowTankS.png");
        enemy_S[3] = new Image("Images/vTankS.png");
        
        enemy_W[0] = new Image("Images/greenTankW.png");
        enemy_W[1] = new Image("Images/blueTankW.png");
        enemy_W[2] = new Image("Images/yellowTankW.png");
        enemy_W[3] = new Image("Images/vTankW.png");
        
        coin = new Image("Images/Coins.jpg");
        lifePack = new Image("Images/lifePack.png");   
        
         bFont = new Font("Times New Roman", Font.BOLD, 14);
        tFont = new TrueTypeFont(bFont, false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        Map.render(0, 0);
        score.draw(700, 0);
        
        tFont.drawString(750, 75, "Players", org.newdawn.slick.Color.white);
         tFont.drawString(825, 75, "Points", org.newdawn.slick.Color.white);
          tFont.drawString(900, 75, "Coins", org.newdawn.slick.Color.white);
           tFont.drawString(975, 75, "Health", org.newdawn.slick.Color.white);
        try {
            change();
        } catch (Exception ex) {
            Logger.getLogger(GamePlaying.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
       /* if(input.isKeyPressed(Input.KEY_ENTER))
            sbg.enterState(2);*/
    }
    
    private void change() throws Exception{
        LinkedList<Brick> bricks = map.getBricks();
        LinkedList<Water> water = map.getWater();
        LinkedList<Stone> stone = map.getStone();
        LinkedList<Coins> Coin = map.getCoin();
        LinkedList<LifePack> life = map.getLifePack();
        
        ListIterator iterator = bricks.listIterator();
        
        while(iterator.hasNext()){
            Brick temp = (Brick)iterator.next();
            int damage = temp.getDamage();
            
            switch(damage){
                case 0 : BrickFull.draw(temp.getX()* size, temp.getY()*size); break;
                case 1 : BrickQuarter.draw(temp.getX()* size, temp.getY()*size);break;
                case 2 : BrickHalf.draw(temp.getX()* size, temp.getY()*size);break;
                case 3 : Brick3Quarter.draw(temp.getX()* size, temp.getY()*size);break;
                case 4 : break;
            }
        }    
        
        iterator = water.listIterator();
        
        while(iterator.hasNext()){
            Water temp = (Water)iterator.next();
      
            Water.draw(temp.getX()* size, temp.getY()*size);
        }
        
       iterator = stone.listIterator();
        
        while(iterator.hasNext()){
            Stone temp = (Stone)iterator.next();
      
            Stone.draw(temp.getX()* size, temp.getY()*size);
        }
        
       iterator = Coin.listIterator();
        
        while(iterator.hasNext()){
            Coins temp = (Coins)iterator.next();
      
            if(temp.isAvailable())
                coin.draw(temp.getX()* size, temp.getY()*size);
        }
        
        iterator = life.listIterator();
        
        while(iterator.hasNext()){
            LifePack temp = (LifePack)iterator.next();
      
            if(temp.isAvailable())
                lifePack.draw(temp.getX()* size, temp.getY()*size);
        }
        
        int index = map.getIndex();
        Player[] players = map.getPlayer();
        if(index != -1){
            if(players[index].getHealth() > 0){
                int direction = players[index].getDirection();
               /* 0 North
                1 East,
                2 South 
                3 West */
                tFont.drawString(750, 75+50*(index+1), "*P " + players[index].getIndex(), org.newdawn.slick.Color.white);
                tFont.drawString(825, 75+50*(index+1),String. valueOf(players[index].getPoints()), org.newdawn.slick.Color.white);
                tFont.drawString(900, 75+50*(index+1),String.valueOf(players[index].getCoins()), org.newdawn.slick.Color.white);
                tFont.drawString(975, 75+50*(index+1), String.valueOf(players[index].getHealth()), org.newdawn.slick.Color.white);
                              
                switch(direction){
                    case 0 :    ourPlayer.draw(players[index].getX()* size, players[index].getY()*size); break;
                    case 1 :    ourPlayer_E.draw(players[index].getX()* size, players[index].getY()*size); break;
                    case 2 :    ourPlayer_S.draw(players[index].getX()* size, players[index].getY()*size); break;
                    case 3 :    ourPlayer_W.draw(players[index].getX()* size, players[index].getY()*size); break;
                }   
            }
            
            for(int i = 0; i < players.length;i++ ){
                if(i != index && players[i] != null){
                    int direction = players[i].getDirection();
             /* 0 North
                1 East,
                2 South 
                3 West */
                    int index1 = players[i].getIndex();
                tFont.drawString(750, 75+50*(index1+1), "P " + players[index1].getIndex(), org.newdawn.slick.Color.white);
                tFont.drawString(825, 75+50*(index1+1),String. valueOf(players[index1].getPoints()), org.newdawn.slick.Color.white);
                tFont.drawString(900, 75+50*(index1+1),String.valueOf(players[index1].getCoins()), org.newdawn.slick.Color.white);
                tFont.drawString(975, 75+50*(index1+1), String.valueOf(players[index1].getHealth()), org.newdawn.slick.Color.white);

                    if(i < index) {
                        
                        
                    switch(direction){
                      
                        case 0 :    enemy[index1].draw(players[i].getX()* size, players[i].getY()*size); break;
                        case 1 :    enemy_E[index1].draw(players[i].getX()* size, players[i].getY()*size); break;
                        case 2 :    enemy_S[index1].draw(players[i].getX()* size, players[i].getY()*size); break;
                        case 3 :    enemy_W[index1].draw(players[i].getX()* size, players[i].getY()*size); break;
                      }
                    }
                    
                    else{  
                       switch(direction){
                       
                        case 0 :    enemy[index1-1].draw(players[i].getX()* size, players[i].getY()*size); break;
                        case 1 :    enemy_E[index1-1].draw(players[i].getX()* size, players[i].getY()*size); break;
                        case 2 :    enemy_S[index1-1].draw(players[i].getX()* size, players[i].getY()*size); break;
                        case 3 :    enemy_W[index1-1].draw(players[i].getX()* size, players[i].getY()*size); break;
                    }
                    }
                    }
                }
                    
            }
        }   
}
   
   
