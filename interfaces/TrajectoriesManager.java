package interfaces;

public interface TrajectoriesManager {

    /**If the iterator is equals to zero, it will draw trajectories and set the iterator to the interval. Otherwise, it decrement the iterator by one. */
    public void drawTrajectoriesOrReduceIterator();
    /** Edit the value of the interval. It determines the rate at which trajectories are updated. 0 = Immediate update*/
    public void setInterval(int interval);
    /** Edit the number of cycles represented by each trajectory. */
    public void setSteps(double steps);
}