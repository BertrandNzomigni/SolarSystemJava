package interfaces;

public interface NameFactory {
    /** This method create a text containing the name of the object.
     * @param object The object which have its name in the text
     * @param pixels The vertical distance between the graphical representation of the object and the text.
     */
    public Displayable createName(CelestialObject object,int pixels);
}
