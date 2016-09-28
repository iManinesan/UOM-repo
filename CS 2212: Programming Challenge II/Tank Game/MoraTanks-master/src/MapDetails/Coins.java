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
public class Coins extends BonusElement {
    private final long lifeTime;
    private final int amount;
    private final long currentTime;
    private boolean available;
    
    public Coins(int x, int y, long lifeTime, int amount){
        super(x,y,lifeTime);
        this.amount = amount;
        this.lifeTime = lifeTime;
        this.currentTime = System.currentTimeMillis();
        available = true;
    }
    
    public boolean take(){
        if(currentTime+lifeTime > System.currentTimeMillis() && available){
            available = false;
            return true;
        }
        return false;
    }
    
    public boolean isAvailable(){
        if(currentTime+lifeTime > System.currentTimeMillis() && available)
            return true;
        else
            return false;
    }
    
    public int getAmount(){
        return amount;
    }

    public long getLifeTime() {
        return lifeTime;
    }
    
   
}

