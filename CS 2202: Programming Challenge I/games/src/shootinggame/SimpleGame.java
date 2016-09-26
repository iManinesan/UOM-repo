/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shootinggame;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;



/**
 * This application demonstrates a number of things in the implementation of a
 * simple shooting game. The object of the the game is to shoot a duck that
 * repeatedly moves across the screen from left to right. There are two duck
 * models, one for the 'live' duck and one for the 'dead' one. These are loaded
 * from 'duck.obj' and 'deadduck.obj' files. The 'gun' is built from primitives.
 * The duck and the ball that is used to shoot the duck use interpolators for
 * their animation. The gun uses key board input to aim and fire it, and
 * collision detection is used to 'kill' the duck.
 * 
 * @author I.J.Palmer
 * @version 1.0
 */
public class SimpleGame extends Frame implements ActionListener {
  protected Canvas3D myCanvas3D = new Canvas3D(null);
  
  protected Button exitButton = new Button("Exit");

  protected BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0,
      0.0), 100.0);

  /** Switch that is used to swap the duck models */
  Switch duckSwitch;

  /** Alpha used to drive the duck animation */
  Alpha duckAlpha;

  /** Used to drive the ball animation */
  Alpha ballAlpha;

  /** Used to move the ball */
  PositionInterpolator moveBall;

  /** Used to rotate the gun */
  TransformGroup gunXfmGrp = new TransformGroup();

  /**
   * This builds the view branch of the scene graph.
   * 
   * @return BranchGroup with viewing objects attached.
   */
  protected BranchGroup buildViewBranch(Canvas3D c) {
    BranchGroup viewBranch = new BranchGroup();
    Transform3D viewXfm = new Transform3D();
    Matrix3d viewTilt = new Matrix3d();
    viewTilt.rotX(Math.PI / -6);
    viewXfm.set(viewTilt, new Vector3d(0.0, 10.0, 10.0), 1.0);
    TransformGroup viewXfmGroup = new TransformGroup(viewXfm);
    ViewPlatform myViewPlatform = new ViewPlatform();
    PhysicalBody myBody = new PhysicalBody();
    PhysicalEnvironment myEnvironment = new PhysicalEnvironment();
    viewXfmGroup.addChild(myViewPlatform);
    viewBranch.addChild(viewXfmGroup);
    View myView = new View();
    myView.addCanvas3D(c);
    myView.attachViewPlatform(myViewPlatform);
    myView.setPhysicalBody(myBody);
    myView.setPhysicalEnvironment(myEnvironment);
    return viewBranch;
  }

  /**
   * This adds some lights to the content branch of the scene graph.
   * 
   * @param b
   *            The BranchGroup to add the lights to.
   */
  protected void addLights(BranchGroup b) {
    Color3f ambLightColour = new Color3f(0.5f, 0.5f, 0.5f);
    AmbientLight ambLight = new AmbientLight(ambLightColour);
    ambLight.setInfluencingBounds(bounds);
    Color3f dirLightColour = new Color3f(1.0f, 1.0f, 1.0f);
    Vector3f dirLightDir = new Vector3f(-1.0f, -1.0f, -1.0f);
    DirectionalLight dirLight = new DirectionalLight(dirLightColour,
        dirLightDir);
    dirLight.setInfluencingBounds(bounds);
    b.addChild(ambLight);
    b.addChild(dirLight);
  }

  /**
   * This builds the gun geometry. It uses box and cylinder primitives and
   * sets up a transform group so that we can rotate the gun.
   */
  protected BranchGroup buildGun() {
    BranchGroup theGun = new BranchGroup();
    Appearance gunApp = new Appearance();
    Color3f ambientColour = new Color3f(0.5f, 0.5f, 0.5f);
    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
    Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f diffuseColour = new Color3f(0.5f, 0.5f, 0.5f);
    float shininess = 20.0f;
    gunApp.setMaterial(new Material(ambientColour, emissiveColour,
        diffuseColour, specularColour, shininess));
    TransformGroup init = new TransformGroup();
    TransformGroup barrel = new TransformGroup();
    Transform3D gunXfm = new Transform3D();
    Transform3D barrelXfm = new Transform3D();
    barrelXfm.set(new Vector3d(0.0, -2.0, 0.0));
    barrel.setTransform(barrelXfm);
    Matrix3d gunXfmMat = new Matrix3d();
    gunXfmMat.rotX(Math.PI / 2);
    gunXfm.set(gunXfmMat, new Vector3d(0.0, 0.0, 0.0), 1.0);
    init.setTransform(gunXfm);
    gunXfmGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    gunXfmGrp.addChild(new Box(1.0f, 1.0f, 0.5f, gunApp));
    barrel.addChild(new Cylinder(0.3f, 4.0f, gunApp));
    gunXfmGrp.addChild(barrel);
    theGun.addChild(init);
    init.addChild(gunXfmGrp);
    return theGun;
  }

  /**
   * Creates the duck. This loads the two duck geometries from the files
   * 'duck.obj' and 'deadduck.obj' and loads these into a switch. The access
   * rights to the switch are then set so we can write to this switch to swap
   * between the two duck models. It also creates a transform group and an
   * interpolator to move the duck.
   * 
   * @return BranchGroup with content attached.
   */
  protected BranchGroup buildDuck() {
    BranchGroup theDuck = new BranchGroup();
    duckSwitch = new Switch(0);
    duckSwitch.setCapability(Switch.ALLOW_SWITCH_WRITE);

    ObjectFile f1 = new ObjectFile();
    ObjectFile f2 = new ObjectFile();
    Scene s1 = null;
    Scene s2 = null;
    try {
      s1 = f1.load("duck.obj");
      s2 = f2.load("deadduck.obj");
    } catch (Exception e) {
      System.exit(1);
    }

    TransformGroup duckRotXfmGrp = new TransformGroup();
    Transform3D duckRotXfm = new Transform3D();
    Matrix3d duckRotMat = new Matrix3d();
    duckRotMat.rotY(Math.PI / 2);
    duckRotXfm.set(duckRotMat, new Vector3d(0.0, 0.0, -30.0), 1.0);
    duckRotXfmGrp.setTransform(duckRotXfm);
    duckRotXfmGrp.addChild(duckSwitch);

    duckSwitch.addChild(s1.getSceneGroup());
    duckSwitch.addChild(s2.getSceneGroup());

    TransformGroup duckMovXfmGrp = new TransformGroup();
    duckMovXfmGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    duckMovXfmGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    duckMovXfmGrp.addChild(duckRotXfmGrp);

    duckAlpha = new Alpha(-1, 0, 0, 3000, 0, 0);
    Transform3D axis = new Transform3D();
    PositionInterpolator moveDuck = new PositionInterpolator(duckAlpha,
        duckMovXfmGrp, axis, -30.0f, 30.0f);
    moveDuck.setSchedulingBounds(bounds);
    theDuck.addChild(moveDuck);
    theDuck.addChild(duckMovXfmGrp);
    return theDuck;
  }

  /**
   * This builds the ball that acts as the bullet for our gun. The ball is
   * created from a sphere primitive, and a transform group and interpolator
   * are added so that we can 'fire' the bullet.
   * 
   * @return BranchGroup that is the root of the ball branch.
   */
  protected BranchGroup buildBall() {
    BranchGroup theBall = new BranchGroup();

    Appearance ballApp = new Appearance();
    Color3f ambientColour = new Color3f(1.0f, 0.0f, 0.0f);
    Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
    Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
    Color3f diffuseColour = new Color3f(1.0f, 0.0f, 0.0f);
    float shininess = 20.0f;
    ballApp.setMaterial(new Material(ambientColour, emissiveColour,
        diffuseColour, specularColour, shininess));

    Sphere ball = new Sphere(0.2f, ballApp);

    TransformGroup ballMovXfmGrp = new TransformGroup();
    ballMovXfmGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    ballMovXfmGrp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    ballMovXfmGrp.addChild(ball);
    theBall.addChild(ballMovXfmGrp);

    ballAlpha = new Alpha(1, 0, 0, 500, 0, 0);
    Transform3D axis = new Transform3D();
    axis.rotY(Math.PI / 2);
    moveBall = new PositionInterpolator(ballAlpha, ballMovXfmGrp, axis,
        0.0f, 50.0f);
    moveBall.setSchedulingBounds(bounds);
    theBall.addChild(moveBall);

    return theBall;

  }

  /**
   * This puts all the content togther. It used the three 'build' functions to
   * create the duck, the gun and the ball. It also creates the two behaviours
   * from the DuckBehaviour and GunBehaviour classes. It then puts all this
   * together.
   * 
   * @return BranchGroup that is the root of the content.
   */
  protected BranchGroup buildContentBranch() {
    BranchGroup contentBranch = new BranchGroup();
    Node theDuck = buildDuck();
    contentBranch.addChild(theDuck);
    Node theBall = buildBall();
    contentBranch.addChild(theBall);
    DuckBehaviour hitTheDuck = new DuckBehaviour(theDuck, duckSwitch,
        duckAlpha, bounds);
    GunBehaviour shootTheGun = new GunBehaviour(ballAlpha, moveBall,
        gunXfmGrp, bounds);
    contentBranch.addChild(hitTheDuck);
    contentBranch.addChild(shootTheGun);
    contentBranch.addChild(buildGun());
    addLights(contentBranch);
    return contentBranch;
  }

  /** Exit the application */
  public void actionPerformed(ActionEvent e) {
    dispose();
    System.exit(0);
  }

  public SimpleGame() {
    VirtualUniverse myUniverse = new VirtualUniverse();
    Locale myLocale = new Locale(myUniverse);
    myLocale.addBranchGraph(buildViewBranch(myCanvas3D));
    myLocale.addBranchGraph(buildContentBranch());
    setTitle("Duck Shoot!");
    setSize(400, 400);
    setLayout(new BorderLayout());
    add("Center", myCanvas3D);
    exitButton.addActionListener(this);
    add("South", exitButton);
    setVisible(true);

  }

  public static void main(String[] args) {
    SimpleGame sg = new SimpleGame();
  }

}