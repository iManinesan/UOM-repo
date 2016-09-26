/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootinggame;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.vecmath.Matrix3d;

/**
 * This is used in the SimpleGame application. It defines a behaviour that
 * allows a 'gun' to be rotated when left and right cursor keys are pressed and
 * then a ball is 'fired' when the space bar is pressed. The 'firing' is
 * achieved by setting the start time of an interpolator to the current time.
 * 
 * @author I.J.Palmer
 * @version 1.0
 */

class GunBehaviour extends Behavior {
  /** The separate criteria that trigger this behaviour */
  protected WakeupCriterion theCriterion;

  /** The alpha that is used to 'fire' the ball */
  protected Alpha theGunAlpha;

  /** Used to animate the ball */
  protected PositionInterpolator theInterpolator;

  /** Used to calculate the current direction of the gun */
  protected int aim = 0;

  /** This is used to rotate the gun */
  protected TransformGroup aimXfmGrp;

  /** Used to aim the ball */
  protected Matrix3d aimShotMat = new Matrix3d();

  /** Used to aim the gun */
  protected Matrix3d aimGunMat = new Matrix3d();

  /** Used to define the ball's direction */
  protected Transform3D aimShotXfm = new Transform3D();

  /** Used to define the gun's direction */
  protected Transform3D aimGunXfm = new Transform3D();

  /**
   * Set up the data for the behaviour.
   * 
   * @param a1
   *            Alpha that drives the ball's animation.
   * @param pi
   *            PositionInterpolator used for the ball.
   * @param gunRotGrp
   *            TransformGroup that is used to rotate the gun.
   * @param theBounds
   *            Bounds that define the active region for this behaviour.
   */
  public GunBehaviour(Alpha a1, PositionInterpolator pi,
      TransformGroup gunRotGrp, Bounds theBounds) {
    theGunAlpha = a1;
    theInterpolator = pi;
    setSchedulingBounds(theBounds);
    aimXfmGrp = gunRotGrp;
  }

  /**
   * This sets up the criteria for triggering the behaviour. We simple want to
   * wait for a key to be pressed.
   */
  public void initialize() {
    theCriterion = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
    wakeupOn(theCriterion);
  }

  /**
   * This is where the work is done. This identifies which key has been
   * pressed and acts accordingly: left key cursor rotate left, right cursor
   * key rotate right, spacebar fire.
   * 
   * @criteria Enumeration that represents the trigger conditions.
   */
  public void processStimulus(Enumeration criteria) {
    while (criteria.hasMoreElements()) {
      WakeupCriterion theCriterion = (WakeupCriterion) criteria
          .nextElement();
      if (theCriterion instanceof WakeupOnAWTEvent) {
        AWTEvent[] triggers = ((WakeupOnAWTEvent) theCriterion)
            .getAWTEvent();
        //Check if it's a keyboard event
        if (triggers[0] instanceof KeyEvent) {
          int keyPressed = ((KeyEvent) triggers[0]).getKeyCode();
          if (keyPressed == KeyEvent.VK_LEFT) {
            //It's a left key so move the turret
            //and the aim of the gun left unless
            //we're at our maximum angle
            if (aim < 8)
              aim += 1;
            System.out.println("Left " + aim);
            aimShotMat.rotY(((aim / 32.0) + 0.5) * Math.PI);
            aimGunMat.rotZ(((aim / -32.0)) * Math.PI);
            aimShotXfm.setRotation(aimShotMat);
            aimGunXfm.setRotation(aimGunMat);
            aimXfmGrp.setTransform(aimGunXfm);
            theInterpolator.setAxisOfTranslation(aimShotXfm);
          } else if (keyPressed == KeyEvent.VK_RIGHT) {
            //It's the right key so do the same but rotate right
            if (aim > -8)
              aim -= 1;
            System.out.println("Right " + aim);
            aimShotMat.rotY(((aim / 32.0) + 0.5) * Math.PI);
            aimGunMat.rotZ(((aim / -32.0)) * Math.PI);
            aimGunXfm.setRotation(aimGunMat);
            aimShotXfm.setRotation(aimShotMat);
            aimXfmGrp.setTransform(aimGunXfm);
            theInterpolator.setAxisOfTranslation(aimShotXfm);
          } else if (keyPressed == KeyEvent.VK_SPACE) {
            //It's the spacebar so reset the start time
            //of the ball's animation
            theGunAlpha.setStartTime(System.currentTimeMillis());
          }
        }
      }
    }
    wakeupOn(theCriterion);
  }
}