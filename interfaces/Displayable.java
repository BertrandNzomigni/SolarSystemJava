package interfaces;

import java.awt.Graphics;
import java.util.List;

public interface Displayable {
    public void display(Graphics g, SpacePanel panel);

    public List<Displayable> components();
    
}
