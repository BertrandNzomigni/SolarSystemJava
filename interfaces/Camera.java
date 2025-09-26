package interfaces;

import java.awt.Point;

public interface Camera extends Copyable{
    /** Return the image of a location from the camera perspective. */
    Point worldToScreen(Location worldLocation);
    /** Returns the current zoom rate of the camera. */
    double getZoom();
    /** Change the zoom rate by multiplying by the factor. */
    void zoom(double factor);
    /** Shifts the camera on the X axis. If it follows an object, it will keep following it. */
    void moveX(int change);
    /** Shifts the camera on the Y axis. If it follows an object, it will keep following it. */
    void moveY(int change);
    /** Follow the movement of a object. If object = null, it won't follow any object.*/
    void follow(CelestialObject object);
    /** Return the followed object. */
    CelestialObject getFollowedPlanet();
}

