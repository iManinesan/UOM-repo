/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package States;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author DELL
 */
public class GameOver extends BasicGameState{
    private int ID = 2;
    private Image cover;
    private Input input;
    private Font bFont;
    private TrueTypeFont tFont;
    /* GameOpening 0
      GamePlaying 1
      GameOver 2
      ConnectionError 3
    */
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cover = new Image("Images/Game_over.jpg");
        bFont = new Font("Times New Roman", Font.BOLD, 30);
        tFont = new TrueTypeFont(bFont, false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        cover.draw();
        tFont.drawString(450, 500, "Press Enter to restart the game", Color.lightGray);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER))
            sbg.enterState(3);
    }
    
}
