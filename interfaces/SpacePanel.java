package interfaces;

import java.util.List;

public interface SpacePanel{
    /** This method add a new displayable to the panel.
     * @param displayable The added displayable
     * @param level The level where the displayable is painted. Displayables are painted above displayables which have a lower level than them.
     */
    public void addNewDisplayable(Displayable displayable, Long level);

    /** This method add multiples displayables to the panel.
     * @param displayable The added displayables
     * @param level The level where the displayables are painted. Displayables are painted above displayables which have a lower level than them.
     */
    public void addNewDisplayables(List<Displayable> displayables, Long level);
}
