package classes;

import java.util.ArrayList;
import java.util.List;

import interfaces.CelestialObject;
import interfaces.FutureStateGenerator;

public class FutureStateGeneratorImpl implements FutureStateGenerator{

    @Override
    public List<CelestialObject> futureState(List<CelestialObject> baseState,Double delta_time) {
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
            obj.accelerate(delta_time);
            obj.move(delta_time);
        }

        return copies;
    }
}
