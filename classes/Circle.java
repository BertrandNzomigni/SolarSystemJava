package classes;

import javax.swing.*;
import java.awt.*;
import interfaces.Displayable;

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
    public void display(Graphics g, JPanel panel) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

