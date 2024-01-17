package views.gui;

import java.awt.event.*;
import javax.swing.*;

public abstract class DraggableComponent
    extends JComponent {

  protected volatile int screenX = 0;
  protected volatile int screenY = 0;
  protected volatile int myX = 0;
  protected volatile int myY = 0;

  public DraggableComponent() {

    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) { 
        onMouseCliecked();
      }

      @Override
      public void mousePressed(MouseEvent e) {
        screenX = e.getXOnScreen();
        screenY = e.getYOnScreen();

        myX = getX();
        myY = getY();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        onMouseReleased();
       }

      @Override
      public void mouseEntered(MouseEvent e) {
        onMouseEntered();
      }

      @Override
      public void mouseExited(MouseEvent e) { 
        // BP: Par convention, quand une méthode est vide, on met un commentaire qui dit pourquoi 
      }

    });
    
    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - screenX;
        int deltaY = e.getYOnScreen() - screenY;

        deltaX = ajustX(myX + deltaX);
        deltaY = ajustY(myY + deltaY);

        afterMouseDragged(deltaX, deltaY);

        setLocation(deltaX, deltaY);
        getParent().repaint();
        repaint();
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        // BP: Par convention, quand une méthode est vide, on met un commentaire qui dit pourquoi 
       }

    });

    
  }

  public abstract int ajustX(int x);

  public abstract int ajustY(int Y); // BP: la variable doit être en minuscule
  public abstract void afterMouseDragged(int x, int y);
  public abstract void onMouseReleased();
  public abstract void onMouseCliecked();
  public abstract void onMouseEntered();

}

