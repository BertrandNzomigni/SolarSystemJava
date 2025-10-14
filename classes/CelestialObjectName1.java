package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import interfaces.CelestialObject;
import interfaces.CelestialObjectName;
import interfaces.SpacePanel;


/** Represents the name of a celestial object in a menu. */
public class CelestialObjectName1 implements CelestialObjectName {
    int x;
    int y;
    CelestialObject object;
    String content;
    Font font;
    Graphics graphics;
    public CelestialObjectName1(int x,int y,CelestialObject object){
        this.x = x;
        this.y = y;
        this.object = object;
        content = object.get_name();
        font = new Font("Arial", Font.PLAIN, 10);
    }

    @Override
    public void display(Graphics g, SpacePanel panel) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(content, x, y);
        graphics = g;


    }

    @Override
    public boolean isClicked(Point point) {
        FontMetrics metrics = graphics.getFontMetrics(font);
        int textWidth = metrics.stringWidth(content);
        int textHeight = metrics.getHeight();
        return (point.getX() > x && point.getX() < x+textWidth && point.getY() > y && point.getY() < y + textHeight);
    }

    @Override
    public CelestialObject namedObject() {
        return object;
    }
    
}
