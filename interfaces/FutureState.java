package interfaces;

import java.util.List;

public interface FutureState {
    /** Create a list of celestial object that are ahead of time by one cycle.
     * 
     * @param baseState A list of celestial object which serves as the basis of the projection.
    */
    public List<CelestialObject> futureState(List<CelestialObject> baseState);
}
