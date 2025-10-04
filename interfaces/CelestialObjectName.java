package interfaces;

import java.awt.Point;

public interface CelestialObjectName extends Displayable {
    /** Returns true if the point lies on the text. Otherwise, return false. */
    public boolean isClicked(Point point);
    /** Returns the celestial object which is associated with this name. The name is associated with the object during the construction of the first. */
    public CelestialObject namedObject();
}