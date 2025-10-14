package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;

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
    public CelestialObjectName1(int x,int y,CelestialObject object){
        this.x = x;
        this.y = y;
        this.object = object;
        content = object.get_name();
        font = new Font("Arial", Font.PLAIN, 20);
    }

    @Override
    public void display(Graphics g, SpacePanel panel) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        g2d.setColor(Color.RED);
        g2d.drawString(content, x, y);
        System.out.println(content);
    }

    @Override
    public boolean isClicked(Point point,Graphics graphics) {
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
