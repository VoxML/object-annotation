package buttons;

import field1.AnnotationComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

public class Button extends AnnotationComponent {
    public String name;
    public JButton button;
    public ActionListener AL;

    public Button(ArrayList<AnnotationComponent> set)
    {super(set);}

    public Button(String name, AnnotationComponent prev, AnnotationComponent next, Rectangle bounds, JPanel panel, ActionListener AL, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(set != null)
            set.add(this);
        this.name = name;
        this.prev = prev;
        this.next = next;
        this.bounds = bounds;
        this.panel = panel;
        if(prev != null)
            this.prev.setNext(this);
        if(next != null)
            this.next.setPrev(this);
        button = createButton(name, this.bounds, AL);
        updateLocation();
    }

    public Button(String name, Rectangle bounds, JPanel panel, ActionListener AL, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        set.add(this);
        this.name = name;
        this.prev = null;
        this.next = null;
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

    protected JButton createButton(String name, Rectangle buttonBounds, ActionListener AL) {
        button = new JButton(name);
        this.AL = AL;
        button.addActionListener(AL);
        button.setBounds(buttonBounds);
        button.setVisible(true);
        updateLocation();
        panel.add(button);
        return button;
    }
}
