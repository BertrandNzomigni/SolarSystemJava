package classes;


import interfaces.SpacePanel;
import interfaces.TrajectoriesGenerator;
import interfaces.TrajectoriesManager;

import java.util.ArrayList;
import java.util.List;

import interfaces.Displayable;

public class TrajectoriesManager1 implements TrajectoriesManager{
    private int iterator;
    private int interval;
    private double steps;
    private TrajectoriesGenerator generator;
    private SpacePanel panel;
    private List<Trajectory> pastTrajectories;

    public TrajectoriesManager1(TrajectoriesGenerator _generator,SpacePanel _panel){
        iterator = 0;
        interval = 1;
        steps = 1;
        generator = _generator;
        panel = _panel;
        pastTrajectories = generator.generate(steps);
    }
    @Override
    public void evaluate() {
        List<Displayable> l = new ArrayList<Displayable>();
        if (iterator <= 0){
            pastTrajectories = generator.generate(steps);
            
            iterator = interval;
        }
        else{
            iterator--;
        }
        l.addAll(pastTrajectories);
        panel.addNewDisplayables(l,Long.valueOf(0));
    }

    @Override
    public void setInterval(int _interval) {
        interval = _interval;
    }

    @Override
    public void setSteps(double _steps) {
        steps = _steps;
    }

}
