package classes;



import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.Location;
import interfaces.Copyable;

import java.awt.Point;

public class CameraImpl implements Camera {
    private double zoomRate;
    private int offsetX;
    private int offsetY;
    private int screenWidth;
    private int screenHeight;

    private CelestialObject followed; // object currently followed, can be null

    public CameraImpl(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.zoomRate = 0.000001;
        this.offsetX = 0;
        this.offsetY = 0;
        this.followed = null;
    }

    @Override
    public void follow(CelestialObject object) {
        this.followed = object;
    }

    @Override
    public void move_on_x_axis(int change) {
        offsetX += change;
    }

    @Override
    public void move_on_y_axis(int change) {
        offsetY += change;
    }

    @Override
    public void change_zoom_rate_by_factor_multiplication(double factor) {
        zoomRate *= factor;
    }

    @Override
    public Point worldToScreen(Location worldLocation) {
        double worldX = worldLocation.getX();
        double worldY = worldLocation.getY();

        // If following an object, center on it
        if (followed != null) {
            Location center = followed.get_location();
            worldX -= center.getX();
            worldY -= center.getY();
        }

        int screenX = (int) (screenWidth / 2 + worldX * zoomRate + offsetX);
        int screenY = (int) (screenHeight / 2 - worldY * zoomRate + offsetY); // y flipped
        return new Point(screenX, screenY);
    }

    public double get_zoom_rate(){
        return zoomRate;
    }

    @Override
    public CelestialObject get_followed_planet() {
        return followed;
    }

    @Override
    public Copyable copy() {
        CameraImpl newCamera = new CameraImpl(screenWidth, screenHeight);
        newCamera.zoomRate = zoomRate;
        newCamera.offsetX = offsetX;
        newCamera.offsetY = offsetY;
        return newCamera;
        
    }
}


