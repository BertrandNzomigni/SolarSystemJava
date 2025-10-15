package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import java.awt.Graphics2D;

import interfaces.Camera;
import interfaces.CelestialObject;
import interfaces.CelestialObjectName;
import interfaces.Command;
import interfaces.Displayable;
import interfaces.SpacePanel;


/** Represents the name of a celestial object in a menu. */
public class CelestialObjectName1 implements CelestialObjectName {
    int x;
    int y;
    CelestialObject object;
    String content;
    Font font;
    Command onClickCommand;
    public CelestialObjectName1(int x,int y,CelestialObject object,Camera camera){
        this.x = x;
        this.y = y;
        this.object = object;
        content = object.get_name();
        font = new Font("Arial", Font.PLAIN, 20);
        this.onClickCommand = new FollowPlanetCommand(camera, object);
    }

    @Override
    public void display(Graphics g, SpacePanel panel) {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g.getFontMetrics(font);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(content, x, y + metrics.getAscent());
    }

    @Override
    public boolean isClicked(Point point,JComponent panel) {
        FontMetrics metrics = panel.getFontMetrics(font);
        int textWidth = metrics.stringWidth(content);
        int textHeight = metrics.getHeight();
        return (point.getX() > x && point.getX() < x+textWidth && point.getY() > y && point.getY() < y + textHeight);
    }

    @Override
    public CelestialObject namedObject() {
        return object;
    }

    @Override
    public void click() {
        if (onClickCommand != null) {
            onClickCommand.execute();
        }
    }

    @Override
    public List<Displayable> components() {
        return new ArrayList<Displayable>();
    }
    
}
