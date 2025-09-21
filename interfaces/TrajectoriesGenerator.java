package interfaces;

import java.util.List;

import classes.Trajectory;

public interface TrajectoriesGenerator {
    /** Returns a list of trajectories computed from the current state of the system. */
    public List<Trajectory> generate(int steps);
}
