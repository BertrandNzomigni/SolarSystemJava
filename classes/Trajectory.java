package classes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.util.List;

import javax.swing.JPanel;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.Displayable;
import interfaces.Location;

public class Trajectory implements Displayable {
    List<Location> locations;
    List<CelestialObject> cameraSpots;
    Camera camera;
    public Trajectory(List<Location> _locations,List<CelestialObject> _cameraSpots,Camera _camera){
        locations = _locations;
        camera = _camera;
        cameraSpots = _cameraSpots;
    }

    @Override
    public void display(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g;
        GeneralPath path = new GeneralPath();
        Point first = getPoint(0);
        path.moveTo(first.x, first.y);
        for (int i = 1; i < locations.size(); i++) {
            path.lineTo(getPoint(i).x, getPoint(i).y);
        }
        g2d.draw(path);
    }

    private Point getPoint(int indice){
        Camera newCamera = (Camera)camera.copy();
        newCamera.follow(cameraSpots.get(indice));
        return newCamera.worldToScreen(locations.get(indice));
    }
    
}
