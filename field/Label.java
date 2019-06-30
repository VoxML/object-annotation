package field;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class Label extends AnnotationComponent {
    private JLabel label;
    private String name;

    public Label(HashSet<AnnotationComponent> set, String name, Rectangle bounds, AnnotationComponent prev, JPanel panel)
    {
        super(set);
        set.add(this);
        this.setSet(set);
        this.name = name;
        this.bounds = bounds;
        this.setPanel(panel);
        this.setPrev(prev);
        createLabel(bounds, name);
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(getLabel() != null && bounds!=null)
            getLabel().setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(getLabel() != null && bounds!=null)
            getLabel().setBounds(bounds);
    }


    public JLabel createLabel(Rectangle labelBounds, String name)
    {
        JLabel result = new JLabel(name);
        result.setBounds(labelBounds);
        result.setVisible(true);
        getPanel().add(result);
        label = result;
        updateLocation();
        this.component = result;
        return getLabel();
    }

    public Rectangle getBounds() {return bounds;}

    public String getName() {return name;}

    public String toString()
    {
        return "Label with text " + getName();
    }

    public String getKey() {return getName() + "_label"; }

    public JLabel getLabel() {
        return label;
    }
}
