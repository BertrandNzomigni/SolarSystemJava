package interfaces;

import java.util.List;

public interface SolarSystem {
    /** Assigns each celestial body a net force based on gravitational forces. */
    public void apply_forces();
    /** Change each celestial body velocity based on the current net force. */
    public void accelerate();
    /** Chang each celestial body location based on the velocity. */
    public void move();
    
    public List<CelestialObject> getObjects();
}
