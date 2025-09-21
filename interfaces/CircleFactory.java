package interfaces;

import java.util.List;

import classes.Circle;

public interface CircleFactory {

    /** This method create a list of circles for every celestial object of the system. */
    public List<Circle> createDisplayables();
}