package classes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import interfaces.CelestialObject;
import interfaces.NavigationMenu;

public class NavigationMenu1 implements NavigationMenu{
    private int screenWidth;
    private int screenHeight;

    NavigationMenu1(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
    @Override
    public void display(Graphics g, JPanel panel) {
        g.setColor(Color.gray);
        g.drawRect(0,0,(int)(0.25 * screenWidth),(int)(0.4 * screenHeight));
    }

    @Override
    public CelestialObject isClicked() {
        throw new UnsupportedOperationException("Unimplemented method 'isClicked'");
    }
    
}
