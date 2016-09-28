/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIII;

import AI.AStar;
import AI.Node;
import AI.Path;
import MapDetails.BonusElement;
import MapDetails.Coins;
import MapDetails.LifePack;
import MessageInterpretations.SendInterpretation;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pranavan
 */
public class Commander implements Runnable {

    private SendInterpretation si;
    private BarricadeMap map;
    private States currentStates;
    private boolean stop;
    private AStar pathFinder;
    private PriorityList orderedList;
    private PriorityList goals;

    public Commander(BarricadeMap map) {

        this.map = map;
        this.stop = false;
        pathFinder = new AStar();

    }

    public void run() {

        while (!stop) {
            //logic to send the commands
            if (currentStates.isPlayerReset()) {

                if (currentStates.getMode() == 2) {

                    try {
                        pythagorianDistCoins(currentStates.getCoinPiles());
                    } catch (Exception ex) {
                        Logger.getLogger(Commander.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    calculatePath();
                    issueCommand();

                } else if (currentStates.getMode() == 3) {

                    try {
                        pythagorianDistHPacks(currentStates.getHealthPacks());
                    } catch (Exception ex) {
                        Logger.getLogger(Commander.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    calculatePath();
                    issueCommand();

                } else if (currentStates.getMode() == 1) {
                    shootOtherBots();
                    //System.out.println("mode shooting");
                } else {
                    System.out.println("Idling");
                }

                currentStates.setPlayerReset(false);
            } else {

                int i = 0;
                while (i < 700) {
                    i += i;
                    i++;
                }

            }

        }

    }

    private void shootOtherBots() {

        int x = currentStates.getMe().getPlayerX();
        int y = currentStates.getMe().getPlayerY();
        int dir = currentStates.getMe().getPlayerDir();

        int temp = x + 1;
        while (temp < currentStates.getMap().getSize()) {

            if ((((currentStates.getMap().getMap()))[temp][y]).getWeight() == 1200) {
                break;
            } else if ((((currentStates.getMap().getMap()))[temp][y]).getWeight() == 1000) {

                issueShootCommand(temp, y, dir);
                break;
            }
            temp++;
        }
        temp = x - 1;
        while (temp >= 0) {

            if ((((currentStates.getMap().getMap()))[temp][y]).getWeight() == 1200) {
                break;
            } else if ((((currentStates.getMap().getMap()))[temp][y]).getWeight() == 1000) {

                issueShootCommand(temp, y, dir);
                break;
            }
            temp--;
        }

        temp = y + 1;
        while (temp < currentStates.getMap().getSize()) {

            if ((((currentStates.getMap().getMap()))[x][temp]).getWeight() == 1200) {
                break;
            } else if ((((currentStates.getMap().getMap()))[x][temp]).getWeight() == 1000) {

                issueShootCommand(x, temp, dir);
                break;
            }
            temp++;

        }
        temp = y - 1;
        while (temp >= 0) {

            if ((((currentStates.getMap().getMap()))[x][temp]).getWeight() == 1200) {
                break;
            } else if ((((currentStates.getMap().getMap()))[x][temp]).getWeight() == 1000) {

                issueShootCommand(x, temp, dir);
                break;
            }
            temp--;

        }

        si.shoot();                               //don't need

    }

    //plan and shoot
    private void issueShootCommand(int x, int y, int PlayerDir) {

        int dir = shootingDirection(x, y);
        if (PlayerDir != dir) {
            System.out.println("issue command");
            if (dir == 0) {
                si.goUp();
                
            } //north
            else if (dir == 2) {
                si.goDown();
            } //South
            else if (dir == 3) {
                si.goLeft();
            } //west
            else if (dir == 1) {
                si.goRight();
            }                      //east

        } else {
            si.shoot();
        }

    }

    //set shooting direction
    private int shootingDirection(int xCor, int yCor) {

        int x = sign(xCor - currentStates.getMe().getPlayerX());
        int y = sign(yCor - currentStates.getMe().getPlayerX());

        if (x == 0 && y == -1) {
            return 0;
        } //north
        else if (x == 0 && y == 1) {
            return 2;
        } //South
        else if (x == -1 && y == 0) {
            return 3;
        } //west
        else if (x == 1 && y == 0) {
            return 1;
        } //east
        else {
            return -1;
        }

    }

    private int sign(int num) {

        if (num < 0) {
            return -1;
        } else if (num == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    //plan the direction to move
    private void issueCommand() {

        if (!goals.isEmpty()) {
            Path p = (Path) goals.getFirst();
            LinkedList<Node> n = (p).getPath();

            Node next = n.getFirst();
            int nextDir = direction(next);

            if (next.getWeight() > 1) {                                                                                     //fire brickwalls
                if (currentStates.getMe().getPlayerDir() == nextDir) {
                    si.shoot();

                } else {
                    giveDirection(nextDir);
                }
            } else {
                giveDirection(nextDir);
            }

        } else {

            si.shoot();
        }

    }

    private void giveDirection(int nextDir) {

        if (nextDir == 0) {
            si.goUp();
            System.out.println("UP");
        } else if (nextDir == 1) {
            si.goRight();
            System.out.println("Right");
        } else if (nextDir == 2) {
            si.goDown();
            System.out.println("Down");
        } else if (nextDir == 3) {
            si.goLeft();
            System.out.println("Left");
        }

    }

    private void calculatePath() {

        int i = 0;
        goals = new PriorityList();
        while (i < 5 && (!orderedList.isEmpty())) {
            Object ob = orderedList.removeFirst();

            Path path = pathFinder.findPath(map.getMap()[currentStates.getMe().getPlayerX()][currentStates.getMe().getPlayerY()], map.getMap()[(((BonusElement) ((PythagorianNode) (ob)).getElement()).getX())][(((BonusElement) ((PythagorianNode) (ob)).getElement()).getY())]);
            if (!((currentStates.getMe().getPlayerX() == (((BonusElement) ((PythagorianNode) (ob)).getElement()).getX())) && (currentStates.getMe().getPlayerY() == (((BonusElement) ((PythagorianNode) (ob)).getElement()).getY()))) && path.getCost() < 1000) {

                path.addCost(addDirCost(currentStates.getMe().getPlayerDir(), direction((Node) ((path.getPath()).getFirst()))));
                goals.add(path);
                i++;
            }

        }

        System.out.println("path calculated");

    }

    private int addDirCost(int dir1, int dir2) {

        if (dir1 != dir2) {
            return 1;
        } else {
            return 0;
        }

    }

    public void setSGenerator(SendInterpretation si) {
        this.si = si;
    }

    public void commanderStop() {
        stop = true;
    }

    /**
     * @param currentStates the currentStates to set
     */
    public void setCurrentStates(States currentStates) {
        this.currentStates = currentStates;
    }

    //calculate the trignometric distance to the life packs
    private void pythagorianDistHPacks(LinkedList<LifePack> cPack)throws Exception {
        Iterator<LifePack> i = cPack.iterator();
        int tempX;
        int tempY;
        int tempDist;
        orderedList = new PriorityList();
        while (i.hasNext()) {
            LifePack hPile = i.next();
            tempX = (hPile.getX() - currentStates.getMe().getPlayerX());
            tempY = (hPile.getY() - currentStates.getMe().getPlayerY());
            tempDist = (int) Math.round(Math.sqrt((tempX * tempX) + (tempY * tempY)));
            PythagorianNode nNode = new PythagorianNode(tempDist, hPile);
            orderedList.add(nNode);

        }

    }

    //calculate the trignometric distance to the coin packs
    private void pythagorianDistCoins(LinkedList<Coins> cPack) throws Exception {
        Iterator<Coins> i = cPack.iterator();
        int tempX;
        int tempY;
        int tempDist;
        orderedList = new PriorityList();
        while (i.hasNext()) {
            Coins cPile = i.next();
            tempX = (cPile.getX() - currentStates.getMe().getPlayerX());
            tempY = (cPile.getY() - currentStates.getMe().getPlayerY());
            tempDist = (int) Math.round(Math.sqrt((tempX * tempX) + (tempY * tempY)));
            PythagorianNode nNode = new PythagorianNode(tempDist, cPile);
            orderedList.add(nNode);

        }

    }

    private int direction(Node next) {

        int x = next.getX() - currentStates.getMe().getPlayerX();
        int y = next.getY() - currentStates.getMe().getPlayerY();

        if (x == 0 && y == -1) {
            return 0;
        } //north
        else if (x == 0 && y == 1) {
            return 2;
        } //South
        else if (x == -1 && y == 0) {
            return 3;
        } //west
        else if (x == 1 && y == 0) {
            return 1;
        } //east
        else {
            return -1;
        }

    }

    private class PriorityList extends LinkedList {

        public void add(Comparable object) {
            for (int i = 0; i < size(); i++) {
                if (object.compareTo(get(i)) <= 0) {
                    add(i, object);
                    return;
                }
            }
            addLast(object);
        }
    }

    private class PythagorianNode implements Comparable {

        private int pythagorianDist;
        private BonusElement bElement;

        public PythagorianNode(int pythagorianDist, BonusElement bElement) {

            this.pythagorianDist = pythagorianDist;
            this.bElement = bElement;

        }

        public BonusElement getElement() {

            return bElement;
        }

        public int compareTo(Object other) {

            int otherValue = ((PythagorianNode) other).getPythagorianDist();
            int thisValue = this.getPythagorianDist();

            return sign(thisValue - otherValue);

        }

        private int sign(int num) {

            if (num < 0) {
                return -1;
            } else if (num == 0) {
                return 0;
            } else {
                return 1;
            }

        }

        public int getPythagorianDist() {

            return pythagorianDist;
        }

    }
}
