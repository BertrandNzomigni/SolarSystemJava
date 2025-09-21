package classes;
import interfaces.Location;
import interfaces.SpaceVector;

public class Location1 implements Location{
    Double x;
    Double y;

    public Location1(Double _x,Double _y){
        x = _x;
        y = _y;
    }
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double distance(Location loc2) {
        return Math.sqrt(Math.pow(x - loc2.getX(), 2) + Math.pow(y - loc2.getY(),2));
    }
    @Override
    public Location applyVector(SpaceVector vector) {

        Double _x = x + vector.getX();
        Double _y = y + vector.getY();
        return new Location1(_x,_y);

    }
    @Override
    public double angle(Location loc2) {
        return Math.atan2(loc2.getY() - y,loc2.getX() - x);
    }
    
    public Location copy(){
        Location cp = new Location1(x,y);
        return cp;
    }
}
