package interfaces;

public interface NameManager {
    /**This method enable or the disable the name for the celestial object. If the name is enabled, it will be shown on the screen. */
    public void toggleName(CelestialObject object);

    /** This method add the names to the label. */
    public void addNames();
}
