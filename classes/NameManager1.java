package classes;

import java.util.HashSet;
import java.util.Set;

import interfaces.CelestialObject;
import interfaces.NameFactory;
import interfaces.NameManager;
import interfaces.SpacePanel;

public class NameManager1 implements NameManager{
    Set<CelestialObject> objects;
    NameFactory factory;
    SpacePanel panel;

    public NameManager1(NameFactory _factory, SpacePanel _panel){
        objects = new HashSet<CelestialObject>();
        factory = _factory;
        panel = _panel;
    }

    @Override
    public void toggleName(CelestialObject object) {
        if (objects.contains(object)){
            objects.remove(object);
        }
        else{
            objects.add(object);
        }
    }

    @Override
    public void addNames() {
        for (CelestialObject object : objects){
            panel.addNewDisplayable(factory.createName(object,30),Long.valueOf(2));
        }
    }

}
