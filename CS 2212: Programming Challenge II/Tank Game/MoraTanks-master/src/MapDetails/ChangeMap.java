/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MapDetails;

import States.*;
import org.newdawn.slick.Game;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author DELL
 */
public class ChangeMap {
    private StateBasedGame game;
    private GameOpening gameOpening;
    private GamePlaying gamePlaying;
    private GameOver gameOver;
    private ConnectionError connectionError;
   
    ChangeMap(Game game){
        this.game = (StateBasedGame) game;
        this.gameOpening = (GameOpening) this.game.getState(0);
        this.gamePlaying = (GamePlaying) this.game.getState(1);
        this.gameOver = (GameOver) this.game.getState(2);
        this.connectionError = (ConnectionError) this.game.getState(3);
    }
    
}
