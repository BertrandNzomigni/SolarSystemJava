package interfaces;

public interface TrajectoriesManager {

    /**Draws trajectories only if the internal iterator is equals to zero. Set the iterator to the interval. */
    public void evaluate();
    /** Edit the value of the interval. */
    public void setInterval(int interval);
    /** Edit the number of cycles represented by each trajectory. */
    public void setSteps(double steps);
}