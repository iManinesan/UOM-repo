/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MapDetails;

/**
 *
 * @author DELL
 */
public class Brick extends Tile{
    private int damageLevel;
    
    /*
    0 (no damage) 
    1 (25% damage)
    2 (50% damage) 
    3 (75% Damage) 4 
    4 (100% Damage).*/

    public Brick(int x, int y){
        super(x,y);
        damageLevel = 0;
        
    }
    
    public int getDamage(){
        return damageLevel;
    }
    
    public void setDamage(int damage){
        damageLevel = damage;
    }

    public boolean isDestroyed(){
        if (damageLevel == 4)
            return true;
        else
            return false;
    }
}
