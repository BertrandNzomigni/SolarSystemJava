package interfaces;

import java.awt.Point;

import javax.swing.JComponent;

public interface CelestialObjectName extends Displayable {
    /** Returns true if the point lies on the text. Otherwise, return false. */
    public boolean isClicked(Point point,JComponent panel);
    /** Returns the celestial object which is associated with this name. The name is associated with the object during the construction of the first. */
    public CelestialObject namedObject();

    public void click();
}