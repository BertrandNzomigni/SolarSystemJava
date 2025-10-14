package interfaces;

import java.awt.Point;

public interface Camera extends Copyable{
    /** Return the image of a location from the camera perspective. */
    Point worldToScreen(Location worldLocation);
    /** Returns the current zoom rate of the camera. */
    double get_zoom_rate();
    /** Change the zoom rate by multiplying by the factor. */
    void change_zoom_rate_by_factor_multiplication(double factor);
    /** Shifts the camera on the X axis. If it follows an object, it will keep following it. */
    void move_on_x_axis(int change);
    /** Shifts the camera on the Y axis. If it follows an object, it will keep following it. */
    void move_on_y_axis(int change);
    /** Follow the movement of a object. If object = null, it won't follow any object.*/
    void follow(CelestialObject object);
    /** Return the followed object. */
    CelestialObject get_followed_planet();
}

