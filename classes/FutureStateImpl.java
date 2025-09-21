package classes;

import java.util.ArrayList;
import java.util.List;

import interfaces.CelestialObject;
import interfaces.FutureState;

public class FutureStateImpl implements FutureState {

    private static final double DELTA_TIME = 1.0; // one simulation cycle

    @Override
    public List<CelestialObject> futureState(List<CelestialObject> baseState) {
        List<CelestialObject> copies = new ArrayList<>();

        // Step 1: deep copy all objects
        for (CelestialObject obj : baseState) {
            copies.add((CelestialObject)obj.copy());
        }

        // Step 2: compute gravitational forces among copies
        for (CelestialObject obj : copies) {
            obj.resetForce();
            for (CelestialObject other : copies) {
                if (obj != other) obj.applyGravitationalForce(other);
            }
        }

        // Step 3: advance each copy by one cycle
        for (CelestialObject obj : copies) {
            obj.accelerate(DELTA_TIME);
            obj.move(DELTA_TIME);
        }

        return copies;
    }
}
