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
public class EmptyTile extends Tile{
    private boolean status;
    
    public EmptyTile(int x, int y) {
        super(x, y);
        status = true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
