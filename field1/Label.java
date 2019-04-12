package field1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Label extends AnnotationComponent {
    protected JLabel label;
    protected String name;

    public Label(String name, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next, JPanel panel)
    {
        this.name = name;
        this.bounds = bounds;
        this.panel = panel;
        this.prev = prev;
        this.next = next;
        if(prev != null)
            this.prev.next = this;
        if(next != null)
            this.next.prev = this;
        createLabel(bounds, name);
    }

    public Label(String name, Rectangle bounds, JPanel panel)
    {
        this.name = name;
        this.bounds = bounds;
        this.panel = panel;
        createLabel(bounds, name);
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(bounds!=null)
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
}
