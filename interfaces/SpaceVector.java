package interfaces;

public interface SpaceVector extends copyable {
    public double getX();
    public double getY();
    /** Return the sum of the vector with another vector. */
    public SpaceVector summation(SpaceVector vector2);
    /** Return the product of the vector with a number. */
    public SpaceVector multiplication(Double factor);
}
