package classes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import interfaces.Displayable;
import interfaces.SpacePanel;

public class SpacePanel1 extends JPanel implements SpacePanel{
    Map<Long, List<Displayable>> content;
    List<Long> hasContent;
    
    public SpacePanel1(){
        content = new HashMap<Long, List<Displayable>>();
        hasContent = new ArrayList<Long>();
        setBackground(Color.BLACK);
    }

    @Override
    public void addNewDisplayable(Displayable displayable, Long level) {
        checkLevel(level);
        content.get(level).add(displayable);
    }

    public void addNewDisplayables(List<Displayable> displayables, Long level){
        checkLevel(level);
        for (Displayable displayable : displayables){
            content.get(level).add(displayable);
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // clear background
        hasContent.sort(null);
        for(Long level : hasContent){
            for(Displayable displayable : content.get(level)){
                displayable.display(g,this);
            }
        }
    }
    /** This method checks if the level exist. If it doesn't exist, it will create the level. */
    private void checkLevel(Long level){
        if (content.get(level) == null){
            content.put(level,new ArrayList<Displayable>());
            hasContent.add(level);
        }
    }
    /** This method removes all the content of the panel. */
    public void clear(){
        content.clear();
        hasContent.clear();
    }
    
}
