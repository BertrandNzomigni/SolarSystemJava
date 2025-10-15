package classes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import interfaces.Displayable;
import interfaces.SpacePanel;

public class Circle implements Displayable {
    private int x;
    private int y;
    private int radius;
    private Color color;

    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void display(Graphics g,SpacePanel panel) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    @Override
    public List<Displayable> components() {
        return new ArrayList<Displayable>();
    }
}

