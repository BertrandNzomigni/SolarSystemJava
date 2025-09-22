package interfaces;

import java.awt.Color;
/** Represents a celestial object in a 2D space. */
public interface CelestialObject extends Copyable{
    /** Return the location of the celestial object (in meters). */
    public Location getLocation();
    /** Returns the radius of the celestial object (in meters) */
    public Double getRadius();
    /** Returns the name of the celestial object. */
    public String getName();
    /** Returns the mass of the celestial object (in Kilograms) */
    public Double getMass();
    /** Returns the color of the celestial object. */
    public Color getColor();

    /** Set the net force of the celestial object at zero. */
    public void resetForce();
    /** Apply a gravitational force from the source.
     * @param source Another celestial object.
    */
    public void applyGravitationalForce(CelestialObject source);

    /** Change the velocity based on the current net force.*/
    public void accelerate(double delta_time);
    /** Change the location based on the current velocity.*/
    public void move(double delta_time);
    /** Change the velocity such that the object have a circular orbit around the center.
     * @param center Another celestial object.
     */
    public void orbitalSpeed(CelestialObject center);

    /** Returns the velocity of the celestial object. */
    public SpaceVector getVelocity();
}