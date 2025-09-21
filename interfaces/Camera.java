package interfaces;

import java.awt.Point;

public interface Camera {
    Point worldToScreen(Location worldLocation);
    double getZoom();
    void zoom(double factor);
    void moveX(int change);
    void moveY(int change);
    void follow(CelestialObject object);
}

