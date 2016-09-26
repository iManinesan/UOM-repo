/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootinggame;

import java.util.Enumeration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.Node;
import javax.media.j3d.Switch;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.media.j3d.WakeupOr;

  
/**
 * This is used in the SimpleGame application. It defines the behaviour for the
 * duck, which is the target in the shooting game. If something collides with
 * the duck, it swaps a switch value to 'kill' the duck The duck is revived when
 * it's alpha value passes through zero.
 * 
 * @author I.J.Palmer
 * @version 1.0
 */

class DuckBehaviour extends Behavior {
  /** The shape that is being watched for collisions. */
  protected Node collidingShape;

  /** The separate criteria that trigger this behaviour */
  protected WakeupCriterion[] theCriteria;

  /** The result of the 'OR' of the separate criteria */
  protected WakeupOr oredCriteria;

  /** The switch that is used to swap the duck shapes */
  protected Switch theSwitch;

  /** The alpha generator that drives the animation */
  protected Alpha theTargetAlpha;

  /** Defines whether the duck is dead or alive */
  protected boolean dead = false;


    /**
   * This sets up the data for the behaviour.
   * 
   * @param theShape
   *            Node that is to be watched for collisions.
   * @param sw
   *            Switch that is used to swap shapes.
   * @param a1
   *            Alpha that drives the duck's animation.
   * @param theBounds
   *            Bounds that define the active region for this behaviour.
   */
  public DuckBehaviour(Node theShape, Switch sw, Alpha a1, Bounds theBounds) {
    collidingShape = theShape;
    theSwitch = sw;
    theTargetAlpha = a1;
    setSchedulingBounds(theBounds);
  }

  /**
   * This sets up the criteria for triggering the behaviour. It creates an
   * collision crtiterion and a time elapsed criterion, OR's these together
   * and then sets the OR'ed criterion as the wake up condition.
   */
  public void initialize() {
    theCriteria = new WakeupCriterion[2];
    theCriteria[0] = new WakeupOnCollisionEntry(collidingShape);
    theCriteria[1] = new WakeupOnElapsedTime(1);
    oredCriteria = new WakeupOr(theCriteria);
    wakeupOn(oredCriteria);
  }

  /**
   * This is where the work is done. If there is a collision, then if the duck
   * is alive we switch to the dead duck. If the duck was already dead then we
   * take no action. The other case we need to check for is when the alpha
   * value is zero, when we need to set the duck back to the live one for its
   * next traversal of the screen. Finally, the wake up condition is set to be
   * the OR'ed criterion again.
   */
  public void processStimulus(Enumeration criteria) {
    while (criteria.hasMoreElements()) {
      WakeupCriterion theCriterion = (WakeupCriterion) criteria
          .nextElement();
      if (theCriterion instanceof WakeupOnCollisionEntry) {
        //There'sa collision so if the duck is alive swap
        //it to the dead one
        if (dead == false) {
          theSwitch.setWhichChild(1);
          dead = true;
        }
      } else if (theCriterion instanceof WakeupOnElapsedTime) {
        //If there isn't a collision, then check the alpha
        //value and if it's zero, revive the duck
        if (theTargetAlpha.value() < 0.1) {
          theSwitch.setWhichChild(0);
          dead = false;
        }
      }

    }
    wakeupOn(oredCriteria);
  }
}

