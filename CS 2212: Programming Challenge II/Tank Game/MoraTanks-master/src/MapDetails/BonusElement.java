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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class BonusElement {

    //time that the element should disaapear at
    private long endTime;
    private int amount;
    private boolean available;
    private boolean taken;
    private int x, y;

    public BonusElement(int x, int y, long lifeTime) {
        this.x = x;
        this.y = y;
        this.endTime = lifeTime + System.currentTimeMillis();
        this.amount = amount;
        taken = false;


    }

    /**
     * returns the value of the item
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * returns the time left for the element to live
     * @return
     */
    public long timeLeft() {
        return endTime - System.currentTimeMillis();

    }

    /**
     * check whether the element is still living
     */
    public boolean isAvailable() {

        if (timeLeft() > 0 && !taken) {
            available = true;
        } else {
            available = false;
        }

        return available;

    }

    /**
     * Mark as unavailable .
     *
     * Useful when a player gets the element.
     */
    public void markAsUnavailable() {
        taken = true;

    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

