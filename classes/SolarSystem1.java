package classes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import interfaces.CelestialObject;
import interfaces.SolarSystem;

public class SolarSystem1 implements SolarSystem{
    List<CelestialObject> objects;
    public SolarSystem1(){
        objects = new ArrayList<CelestialObject>();
        Double mass = 5.972 * Math.pow(10,24);
        Double radius = 6378.0 * 1000;
        objects.add( new CelestialObjectImplementation("Terre",radius,mass,new Location1(0.0,0.0),new Color(0,255,0),new SpaceVector1(), new SpaceVector1(Double.valueOf(100),Double.valueOf(20))));
        
        mass = 8.1 * Math.pow(10,19);
        radius = 1734.0 * 1000;
        Double moonEarthDistance = 384400.0 * 1000;
        CelestialObject moon = new CelestialObjectImplementation("Lune",radius,mass,new Location1(moonEarthDistance,0.0),new Color(100,100,100),new SpaceVector1(), new SpaceVector1());
        moon.orbitalSpeed(objects.get(0));
        objects.add(moon);
    }
    @Override
    public void apply_forces() {
        List<CelestialObject> list2;
        for (CelestialObject object : objects){
            object.resetForce();
            list2 = new ArrayList<CelestialObject>(objects);
            list2.remove(object);
            for (CelestialObject object2 : list2){
                object.applyGravitationalForce(object2);
            }
        }
    }
    @Override
    public void accelerate() {
        for (CelestialObject object : objects){
            object.accelerate(200);
        }
    }
    @Override
    public void move() {
        for (CelestialObject object : objects){
            object.move(200);
        }
    }

    public List<CelestialObject> getObjects(){
        return objects;
    }
}
