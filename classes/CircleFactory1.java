package classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import interfaces.CelestialObject;
import interfaces.CircleFactory;
import interfaces.Camera;
import interfaces.Displayable;
import interfaces.SolarSystem;

public class CircleFactory1 implements CircleFactory {

    SolarSystem system;
    Camera camera;

    public CircleFactory1(SolarSystem _system,Camera _camera){
        system = _system;
        camera = _camera;
    }

    @Override
    public List<Displayable> createDisplayables() {
        List<Displayable> entities = new ArrayList<>();
        
        for (CelestialObject obj : system.getObjects()) {
            Point screenPos = camera.worldToScreen(obj.getLocation());
            int diameter = (int)(obj.getRadius() * 2 * camera.getZoom());

            entities.add(new Circle(screenPos.x, screenPos.y, diameter, obj.getColor()));
        }

        return entities;
    }
}
