package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import interfaces.CelestialObject;
import interfaces.CelestialObjectName;
import interfaces.NavigationMenu;

public class NavigationMenu1 implements NavigationMenu{
    private int screenWidth;
    private int screenHeight;
    private List<CelestialObject> objects;
    private List<CelestialObjectName> names;

    public NavigationMenu1(int screenWidth, int screenHeight,List<CelestialObject> objects){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.objects = objects;;
        this.names = new ArrayList<CelestialObjectName>();
    }
    @Override
    public void display(Graphics g, JPanel panel) {
        g.setColor(Color.gray);
        g.fillRect(0,0,(int)(0.25 * screenWidth),(int)(0.4 * screenHeight));
        int i = 0;
        for (CelestialObject object : objects){
            names.add(new CelestialObjectName1(10, i * 10,object));
        }
    }

    @Override
    public CelestialObject isClicked() {
        throw new UnsupportedOperationException("Unimplemented method 'isClicked'");
    }
    
}
