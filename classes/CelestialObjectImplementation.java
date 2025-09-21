package classes;

import java.awt.Color;

import interfaces.CelestialObject;
import interfaces.Location;
import interfaces.SpaceVector;
import interfaces.copyable;

public class CelestialObjectImplementation implements CelestialObject{
    
    private String name;
    private Double radius;
    private Double mass;
       
    private Location location;
    private SpaceVector force_vector;
    private SpaceVector velocity_vector;

    private Color color;

    public CelestialObjectImplementation(String _name,Double _radius,Double _mass,Location _location,Color _color,SpaceVector _force_vector,SpaceVector _velocity){
        name = _name;
        radius = _radius;
        mass = _mass;
        location = _location;
        force_vector = _force_vector;
        velocity_vector = _velocity;
        color = _color;

    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Double getRadius() {
        return radius;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getMass() {
        return mass;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void resetForce() {
        force_vector = new SpaceVector1();
    }

    @Override
    public void applyGravitationalForce(CelestialObject source) {
        Double magnitude = (mass * source.getMass()) / (Math.pow(distance(source),2)) * 6.6743  * Math.pow(10,-11);
        SpaceVector force = new SpaceVector1(magnitude,location.angle(source.getLocation()));
        force_vector = force_vector.summation(force);
    }  

    @Override
    public void accelerate(double deltaTime) {
        velocity_vector = velocity_vector.summation(force_vector.multiplication(1/mass * deltaTime));
    }

    @Override
    public void move(double deltaTime) {
        location = location.applyVector(velocity_vector.multiplication(deltaTime));
    }

    private Double distance(CelestialObject object2) {
        return location.distance(object2.getLocation());
    }

    @Override
    public void orbitalSpeed(CelestialObject center) {
        Double magnitude = Math.sqrt(((mass + center.getMass()) * 6.6743  * Math.pow(10,-11) * 0.3) / distance(center));
        Double angle = location.angle(center.getLocation()) + Math.PI/2;
        velocity_vector = new SpaceVector1(magnitude,angle);
    }

    @Override
    public copyable copy() {
        return new CelestialObjectImplementation(name, radius, mass, (Location)location.copy(), color, (SpaceVector)force_vector.copy(), (SpaceVector)velocity_vector.copy());
    }
}
