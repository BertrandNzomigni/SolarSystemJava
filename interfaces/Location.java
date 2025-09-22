package interfaces;
/** Represent a location in the 2-D space. Beware : It doesn't represent a location on the screen. */
public interface Location extends Copyable {
    /** Change the location on the X-axis. */
    public double getX();
    public double getY();
    public double distance(Location loc2);
    public Location applyVector(SpaceVector vector);
    public double angle(Location loc2);
}
