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
public class Player {
    private int index;
    private int x;
    private int y;
    private int direction;
    private int isShot;
    private int health;
    private int coins;
    private int points;
    private int Alive;

    public Player(int x, int y, int direction,int index) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.health = 100;
        this.coins = 0;
        this.points = 0;
        this.index = index;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public int getIsShot() {
        return isShot;
    }

    public int getHealth() {
        return health;
    }

    public int getCoins() {
        return coins;
    }

    public int getPoints() {
        return points;
    }

    public boolean getAlive() {
        return health < 4;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setIsShot(int isShot) {
        this.isShot = isShot;
    }

    public void setHealth(int health) {
        this.health = health;
    }
   
    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setAlive(int Alive) {
        this.Alive = Alive;
    }
    
    
    
}
