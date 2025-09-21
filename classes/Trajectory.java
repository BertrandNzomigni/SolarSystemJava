package classes;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import interfaces.Displayable;

public class Trajectory implements Displayable {
    List<Point> points;
    public Trajectory(List<Point> _points){
        points = _points;
    }

    @Override
    public void display(Graphics g, JPanel panel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
    
}
