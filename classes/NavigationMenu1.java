package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.CelestialObjectName;
import interfaces.Displayable;
import interfaces.NavigationMenu;
import interfaces.SpacePanel;

public class NavigationMenu1 implements NavigationMenu{
    private int screenWidth;
    private int screenHeight;
    private List<CelestialObjectName> names;

    public NavigationMenu1(int screenWidth, int screenHeight,List<CelestialObject> objects,Camera camera){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.names = new ArrayList<CelestialObjectName>();
        int i = 0;
        for (CelestialObject object : objects){
            names.add(new CelestialObjectName1(0,15*i,object,camera));
            i += 1;
        }
    }
    @Override
    public void display(Graphics g, SpacePanel panel) {
        g.setColor(Color.gray);
        g.fillRect(0,0,(int)(0.25 * screenWidth),(int)(0.4 * screenHeight));
        for (CelestialObjectName name : this.names){
            name.display(g, panel);
            
        }
        
    }

    public List<CelestialObjectName> get_names(){
        return names;
    }

    @Override
    public CelestialObject isClicked() {
        throw new UnsupportedOperationException("Unimplemented method 'isClicked'");
    }
    @Override
    public List<Displayable> components() {
        List<Displayable> displayables = new ArrayList<Displayable>();
        for (CelestialObjectName name : names){
            displayables.add(name);
        }
        return displayables;
    }
    
}
