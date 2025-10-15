package interfaces;

import java.util.List;

public interface NavigationMenu extends Displayable{
    /**If the point lies on the name of a object, the method returns a reference towards this object. Otherwise, return None. */
    public CelestialObject isClicked();

    public List<CelestialObjectName> get_names();
    
}
