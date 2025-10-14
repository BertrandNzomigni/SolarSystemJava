package classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import interfaces.CelestialObject;
import interfaces.CircleFactory;
import interfaces.Camera;
import interfaces.SolarSystem;

public class CircleFactory1 implements CircleFactory {

    SolarSystem system;
    Camera camera;

    public CircleFactory1(SolarSystem _system,Camera _camera){
        system = _system;
        camera = _camera;
    }

    @Override
    public List<Circle> showPlanets() {
        List<Circle> entities = new ArrayList<>();
        
        for (CelestialObject obj : system.getObjects()) {
            Point screenPos = camera.worldToScreen(obj.get_location());
            int diameter = (int)(obj.get_radius() * 2 * camera.get_zoom_rate());

            entities.add(new Circle(screenPos.x, screenPos.y, diameter, obj.get_color()));
        }

        return entities;
    }
}
