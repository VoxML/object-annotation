package field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Label extends AnnotationComponent {
    public JLabel label;
    public String name;

    public Label(HashSet<AnnotationComponent> set, String name, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next, JPanel panel)
    {
        super(set);
        if(!set.contains(this))
            set.add(this);
        this.set = set;
        this.name = name;
        this.bounds = bounds;
        this.panel = panel;
        this.prev = prev;
        createLabel(bounds, name);
    }

    public Label(HashSet<AnnotationComponent> set, String name, Rectangle bounds, JPanel panel)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.name = name;
        this.bounds = bounds;
        this.panel = panel;
        createLabel(bounds, name);
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(label != null && bounds!=null)
            label.setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(label != null && bounds!=null)
            label.setBounds(bounds);
    }


    public JLabel createLabel(Rectangle labelBounds, String name)
    {
        JLabel result = new JLabel(name);
        result.setBounds(labelBounds);
        result.setVisible(true);
        panel.add(result);
        label = result;
        updateLocation();
        return label;
    }

    public Rectangle getBounds() {return bounds;}

    public String getName() {return name;}

    public String toString()
    {
        return "Label with text " + name;
    }

    public String getKey() {return name + "_label"; }
}
