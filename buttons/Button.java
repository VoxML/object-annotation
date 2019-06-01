package buttons;

import field.AnnotationComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class Button extends AnnotationComponent {
    public String name;
    public JButton button;
    public ActionListener AL;

    public Button(HashSet<AnnotationComponent> set)
    {super(set);}

    public Button() {    }

    public Button(String name, AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, JPanel panel, ActionListener AL,
                  HashSet<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(set != null)
            set.add(this);
        this.name = name;
        this.prev = prev;
        this.bounds = bounds;
        this.panel = panel;
        button = createButton(name, this.bounds, AL);
        updateLocation();
    }

    public Button(String name, Rectangle bounds, JPanel panel, ActionListener AL, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.name = name;
        this.prev = null;
        this.bounds = bounds;
        this.panel = panel;
        button = createButton(name, this.bounds, AL);
        updateLocation();
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(button != null && bounds!=null)
            button.setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(button!=null && bounds != null)
            button.setBounds(bounds);
    }

    public String getKey()
    {
        return "button";
    }

    protected JButton createButton(String name, Rectangle buttonBounds, ActionListener AL) {
        button = new JButton(name);
        this.AL = AL;
        button.addActionListener(AL);
        button.setBounds(buttonBounds);
        button.setVisible(true);
        panel.add(button);
        updateLocation();
        return button;
    }

    public String toString()
    {
        return "Button with name " + name;
    }
}
