package classes;

import java.util.ArrayList;
import java.util.List;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.FutureStateGenerator;
import interfaces.Location;
import interfaces.SolarSystem;
import interfaces.TrajectoriesGenerator;

public class TrajectoriesGenerator1 implements TrajectoriesGenerator{

    private SolarSystem system;
    private FutureStateGenerator futureState;
    private Camera camera;
    private int numberObjects;
    List<List<CelestialObject>> array;
    public TrajectoriesGenerator1(SolarSystem _system, FutureStateGenerator _futureState,Camera _camera){
        system = _system;
        futureState = _futureState;
        camera = _camera;
    }

    @Override
    public List<Trajectory> generate(double steps) {
        array = new ArrayList<>();
        numberObjects = system.getObjects().size();
        array.add(system.getObjects());
        for (int i = 0; i < steps; i++) {
            array.add(futureState.futureState(array.get(array.size() - 1),Double.valueOf(100)));
        }
        List<Trajectory> trajectories = new ArrayList<Trajectory>();
        List<Location> locations;
        List<CelestialObject> cameraSpots = cameraLocater(steps);
        for (int i = 0; i < numberObjects; i++){
            locations = new ArrayList<Location>();
            for (int x = 0; x < steps; x++){
                locations.add(array.get(x).get(i).get_location());
            }
            trajectories.add(new Trajectory(locations,cameraSpots,camera));
        }
        return trajectories;

    }

    private List<CelestialObject> cameraLocater(double steps){
        int i = 0;
        boolean notFound = true;
        while (notFound && i < numberObjects){
            if (array.get(0).get(i) == camera.get_followed_planet()){
                notFound = false;
            }
            else{
                i++;
            }
        }
        List<CelestialObject> cameraSpots = new ArrayList<CelestialObject>();
        if (!notFound){
            for (int x = 0; x < steps; x++ ){
                cameraSpots.add(array.get(x).get(i));
            }
        }
        else{
            for (int x = 0; x < steps; x++){
                cameraSpots.add(null);
            }
        }
        return cameraSpots;
    }

}
