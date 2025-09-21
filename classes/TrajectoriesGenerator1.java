package classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.FutureStateGenerator;
import interfaces.SolarSystem;
import interfaces.TrajectoriesGenerator;

public class TrajectoriesGenerator1 implements TrajectoriesGenerator{

    private SolarSystem system;
    private Camera camera;
    private FutureStateGenerator futureState;
    public TrajectoriesGenerator1(SolarSystem _system,Camera _camera, FutureStateGenerator _futureState){
        system = _system;
        camera = _camera;
        futureState = _futureState;
    }

    @Override
    public List<Trajectory> generate(int steps) {
        List<List<CelestialObject>> array = new ArrayList<>();
        int numberObjects = system.getObjects().size();
        array.add(system.getObjects());
        for (int i = 0; i < steps; i++) {
            array.add(futureState.futureState(array.get(array.size() - 1)));
        }
        List<Trajectory> trajectories = new ArrayList<Trajectory>();
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < numberObjects; i++){
            for (int x = 0; i < steps; i++){
                points.add(camera.worldToScreen(array.get(x).get(i).getLocation()));
            }
            trajectories.add(new Trajectory(points));
            points.clear();
        }
        return trajectories;

    }

}
