/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author Home
 */
    /*
DEVELOPING GAME IN JAVA 

Caracteristiques

Editeur : NEW RIDERS 
Auteur : BRACKEEN 
Parution : 09 2003 
Pages : 972 
Isbn : 1-59273-005-1 
Reliure : Paperback 
Disponibilite : Disponible a la librairie 
*/


import java.awt.AWTException;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A simple mouselook test. Using mouselook, the user can virtually move the
 * mouse in any direction indefinitly. Without mouselook, the mouse stops when
 * it hits the edge of the screen.
 * <p>
 * Mouselook works by recentering the mouse whenever it is moved, so it can
 * always measure the relative mouse movement, and the mouse never hits the edge
 * of the screen.
 */
public class MouseLookTest extends GameCore implements MouseMotionListener,
    KeyListener {

  public static void main(String[] args) {
    new MouseLookTest().run();
  }

  private Image bgImage;

  private Robot robot;

  private Point mouseLocation;

  private Point centerLocation;

  private Point imageLocation;

  private boolean relativeMouseMode;

  private boolean isRecentering;

  public void init() {
    super.init();
    mouseLocation = new Point();
    centerLocation = new Point();
    imageLocation = new Point();

    relativeMouseMode = true;
    isRecentering = false;

    try {
      robot = new Robot();
      recenterMouse();
      mouseLocation.x = centerLocation.x;
      mouseLocation.y = centerLocation.y;
    } catch (AWTException ex) {
      System.out.println("Couldn't create Robot!");
    }
    Window window = screen.getFullScreenWindow();
    window.addMouseMotionListener(this);
    window.addKeyListener(this);
    bgImage = loadImage("../images/background.jpg");
  }

  public synchronized void draw(Graphics2D g) {

    int w = screen.getWidth();
    int h = screen.getHeight();

    // make sure position is correct
    imageLocation.x %= w;
    imageLocation.y %= screen.getHeight();
    if (imageLocation.x < 0) {
      imageLocation.x += w;
    }
    if (imageLocation.y < 0) {
      imageLocation.y += screen.getHeight();
    }

    // draw the image in four places to cover the screen
    int x = imageLocation.x;
    int y = imageLocation.y;
    g.drawImage(bgImage, x, y, null);
    g.drawImage(bgImage, x - w, y, null);
    g.drawImage(bgImage, x, y - h, null);
    g.drawImage(bgImage, x - w, y - h, null);

    // draw instructions
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g.drawString("Press Space to change mouse modes.", 5, FONT_SIZE);
    g.drawString("Press Escape to exit.", 5, FONT_SIZE * 2);
  }

  /**
   * Uses the Robot class to try to postion the mouse in the center of the
   * screen.
   * <p>
   * Note that use of the Robot class may not be available on all platforms.
   */
  private synchronized void recenterMouse() {
    Window window = screen.getFullScreenWindow();
    if (robot != null && window.isShowing()) {
      centerLocation.x = window.getWidth() / 2;
      centerLocation.y = window.getHeight() / 2;
      SwingUtilities.convertPointToScreen(centerLocation, window);
      isRecentering = true;
      robot.mouseMove(centerLocation.x, centerLocation.y);
    }
  }

  // from the MouseMotionListener interface
  public void mouseDragged(MouseEvent e) {
    mouseMoved(e);
  }

  // from the MouseMotionListener interface
  public synchronized void mouseMoved(MouseEvent e) {
    // this event is from re-centering the mouse - ignore it
    if (isRecentering && centerLocation.x == e.getX()
        && centerLocation.y == e.getY()) {
      isRecentering = false;
    } else {
      int dx = e.getX() - mouseLocation.x;
      int dy = e.getY() - mouseLocation.y;
      imageLocation.x += dx;
      imageLocation.y += dy;
      // recenter the mouse
      if (relativeMouseMode) {
        recenterMouse();
      }

    }

    mouseLocation.x = e.getX();
    mouseLocation.y = e.getY();

  }

  // from the KeyListener interface
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      // exit the program
      stop();
    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      // change relative mouse mode
      relativeMouseMode = !relativeMouseMode;
    }
  }

  // from the KeyListener interface
  public void keyReleased(KeyEvent e) {
    // do nothing
  }

  // from the KeyListener interface
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

}

/**
 * Simple abstract class used for testing. Subclasses should implement the
 * draw() method.
 */

abstract class GameCore {

  protected static final int FONT_SIZE = 24;

  private static final DisplayMode POSSIBLE_MODES[] = {
      new DisplayMode(800, 600, 32, 0), new DisplayMode(800, 600, 24, 0),
      new DisplayMode(800, 600, 16, 0), new DisplayMode(640, 480, 32, 0),
      new DisplayMode(640, 480, 24, 0), new DisplayMode(640, 480, 16, 0) };

  private boolean isRunning;

  protected ScreenManager screen;

  /**
   * Signals the game loop that it's time to quit
   */
  public void stop() {
    isRunning = false;
  }

  /**
   * Calls init() and gameLoop()
   */
  public void run() {
    try {
      init();
      gameLoop();
    } finally {
      screen.restoreScreen();
    }
  }

  /**
   * Sets full screen mode and initiates and objects.
   */
  public void init() {
    screen = new ScreenManager();
    DisplayMode displayMode = screen
        .findFirstCompatibleMode(POSSIBLE_MODES);
    screen.setFullScreen(displayMode);

    Window window = screen.getFullScreenWindow();
    window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
    window.setBackground(Color.blue);
    window.setForeground(Color.white);

    isRunning = true;
  }

  public Image loadImage(String fileName) {
    return new ImageIcon(fileName).getImage();
  }

  /**
   * Runs through the game loop until stop() is called.
   */
  public void gameLoop() {
    long startTime = System.currentTimeMillis();
    long currTime = startTime;

    while (isRunning) {
      long elapsedTime = System.currentTimeMillis() - currTime;
      currTime += elapsedTime;

      // update
      update(elapsedTime);

      // draw the screen
      Graphics2D g = screen.getGraphics();
      draw(g);
      g.dispose();
      screen.update();

      // take a nap
      try {
        Thread.sleep(20);
      } catch (InterruptedException ex) {
      }
    }
  }

  /**
   * Updates the state of the game/animation based on the amount of elapsed
   * time that has passed.
   */
  public void update(long elapsedTime) {
    // do nothing
  }

  /**
   * Draws to the screen. Subclasses must override this method.
   */
  public abstract void draw(Graphics2D g);
}

/**
 * The ScreenManager class manages initializing and displaying full screen
 * graphics modes.
 */

class ScreenManager {

  private GraphicsDevice device;

  /**
   * Creates a new ScreenManager object.
   */
  public ScreenManager() {
    GraphicsEnvironment environment = GraphicsEnvironment
        .getLocalGraphicsEnvironment();
    device = environment.getDefaultScreenDevice();
  }

  /**
   * Returns a list of compatible display modes for the default device on the
   * system.
   */
  public DisplayMode[] getCompatibleDisplayModes() {
    return device.getDisplayModes();
  }

  /**
   * Returns the first compatible mode in a list of modes. Returns null if no
   * modes are compatible.
   */
  public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
    DisplayMode goodModes[] = device.getDisplayModes();
    for (int i = 0; i < modes.length; i++) {
      for (int j = 0; j < goodModes.length; j++) {
        if (displayModesMatch(modes[i], goodModes[j])) {
          return modes[i];
        }
      }

    }

    return null;
  }

  /**
   * Returns the current display mode.
   */
  public DisplayMode getCurrentDisplayMode() {
    return device.getDisplayMode();
  }

  /**
   * Determines if two display modes "match". Two display modes match if they
   * have the same resolution, bit depth, and refresh rate. The bit depth is
   * ignored if one of the modes has a bit depth of
   * DisplayMode.BIT_DEPTH_MULTI. Likewise, the refresh rate is ignored if one
   * of the modes has a refresh rate of DisplayMode.REFRESH_RATE_UNKNOWN.
   */
  public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2)

  {
    if (mode1.getWidth() != mode2.getWidth()
        || mode1.getHeight() != mode2.getHeight()) {
      return false;
    }

    if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
        && mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
        && mode1.getBitDepth() != mode2.getBitDepth()) {
      return false;
    }

    if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
        && mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
        && mode1.getRefreshRate() != mode2.getRefreshRate()) {
      return false;
    }

    return true;
  }

  /**
   * Enters full screen mode and changes the display mode. If the specified
   * display mode is null or not compatible with this device, or if the
   * display mode cannot be changed on this system, the current display mode
   * is used.
   * <p>
   * The display uses a BufferStrategy with 2 buffers.
   */
  public void setFullScreen(DisplayMode displayMode) {
    final JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setUndecorated(true);
    frame.setIgnoreRepaint(true);
    frame.setResizable(false);

    device.setFullScreenWindow(frame);

    if (displayMode != null && device.isDisplayChangeSupported()) {
      try {
        device.setDisplayMode(displayMode);
      } catch (IllegalArgumentException ex) {
      }
      // fix for mac os x
      frame.setSize(displayMode.getWidth(), displayMode.getHeight());
    }
    // avoid potential deadlock in 1.4.1_02
    try {
      EventQueue.invokeAndWait(new Runnable() {
        public void run() {
          frame.createBufferStrategy(2);
        }
      });
    } catch (InterruptedException ex) {
      // ignore
    } catch (InvocationTargetException ex) {
      // ignore
    }

  }

  /**
   * Gets the graphics context for the display. The ScreenManager uses double
   * buffering, so applications must call update() to show any graphics drawn.
   * <p>
   * The application must dispose of the graphics object.
   */
  public Graphics2D getGraphics() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      BufferStrategy strategy = window.getBufferStrategy();
      return (Graphics2D) strategy.getDrawGraphics();
    } else {
      return null;
    }
  }

  /**
   * Updates the display.
   */
  public void update() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      BufferStrategy strategy = window.getBufferStrategy();
      if (!strategy.contentsLost()) {
        strategy.show();
      }
    }
    // Sync the display on some systems.
    // (on Linux, this fixes event queue problems)
    Toolkit.getDefaultToolkit().sync();
  }

  /**
   * Returns the window currently used in full screen mode. Returns null if
   * the device is not in full screen mode.
   */
  public JFrame getFullScreenWindow() {
    return (JFrame) device.getFullScreenWindow();
  }

  /**
   * Returns the width of the window currently used in full screen mode.
   * Returns 0 if the device is not in full screen mode.
   */
  public int getWidth() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      return window.getWidth();
    } else {
      return 0;
    }
  }

  /**
   * Returns the height of the window currently used in full screen mode.
   * Returns 0 if the device is not in full screen mode.
   */
  public int getHeight() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      return window.getHeight();
    } else {
      return 0;
    }
  }

  /**
   * Restores the screen's display mode.
   */
  public void restoreScreen() {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      window.dispose();
    }
    device.setFullScreenWindow(null);
  }

  /**
   * Creates an image compatible with the current display.
   */
  public BufferedImage createCompatibleImage(int w, int h, int transparancy) {
    Window window = device.getFullScreenWindow();
    if (window != null) {
      GraphicsConfiguration gc = window.getGraphicsConfiguration();
      return gc.createCompatibleImage(w, h, transparancy);
    }
    return null;
  }
}
