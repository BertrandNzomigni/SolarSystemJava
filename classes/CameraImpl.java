package classes;



import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.Location;
import interfaces.SolarSystem;

import java.awt.Point;

public class CameraImpl implements Camera {
    private double zoom;
    private int offsetX;
    private int offsetY;
    private int screenWidth;
    private int screenHeight;

    private CelestialObject followed; // object currently followed, can be null

    public CameraImpl(SolarSystem solarSystem, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.zoom = 0.000001;
        this.offsetX = 0;
        this.offsetY = 0;
        this.followed = null;
    }

    @Override
    public void follow(CelestialObject object) {
        this.followed = object;
    }

    @Override
    public void moveX(int change) {
        offsetX += change;
    }

    @Override
    public void moveY(int change) {
        offsetY += change;
    }

    @Override
    public void zoom(double factor) {
        zoom *= factor;
    }

    @Override
    public Point worldToScreen(Location worldLocation) {
        double worldX = worldLocation.getX();
        double worldY = worldLocation.getY();

        // If following an object, center on it
        if (followed != null) {
            Location center = followed.getLocation();
            worldX -= center.getX();
            worldY -= center.getY();
        }

        int screenX = (int) (screenWidth / 2 + worldX * zoom + offsetX);
        int screenY = (int) (screenHeight / 2 - worldY * zoom + offsetY); // y flipped
        return new Point(screenX, screenY);
    }

    public double getZoom(){
        return zoom;
    }
}


