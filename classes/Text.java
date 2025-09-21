package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import interfaces.Displayable;

public class Text implements Displayable{
    private String content;
    private int x;
    private int y;
    private int size;
    /** Create a displayable text. The coordinates are that of the text's top-center. */
    public Text(String _content, int _x, int _y, int _size){
        content = _content;
        x = _x;
        y = _y;
        size = _size;
    }
    
    @Override
    public void display(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Arial", Font.PLAIN, size);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);

        FontMetrics metrics = g.getFontMetrics(font);
        int textWidth = metrics.stringWidth(content);

        g.drawString(content, x - textWidth/2, y);
    }
    
}
