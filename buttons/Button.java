package buttons;

import field.AnnotationComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class Button extends AnnotationComponent {
    private String name;
    private JButton button;
    private ActionListener AL;

    public Button() {    }

    public Button(String name, AnnotationComponent prev, Rectangle bounds, JPanel panel, ActionListener AL,
                  HashSet<AnnotationComponent> set)
    {
        super(set);
        this.setSet(set);
        if(set != null)
            set.add(this);
        this.setName(name);
        this.setPrev(prev);
        this.bounds = bounds;
        this.setPanel(panel);
        setButton(createButton(name, this.bounds, AL));
        updateLocation();
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(getButton() != null && bounds!=null)
            getButton().setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(getButton() !=null && bounds != null)
            getButton().setBounds(bounds);
    }

    public String getKey()
    {
        return "button";
    }

    protected JButton createButton(String name, Rectangle buttonBounds, ActionListener AL) {
        setButton(new JButton(name));
        this.setAL(AL);
        getButton().setBounds(buttonBounds);
        getButton().setVisible(true);
        getPanel().add(getButton());
        updateLocation();
        return getButton();
    }

    public String toString()
    {
        return "Button with name " + getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public ActionListener getAL() {
        return AL;
    }

    public void setAL(ActionListener AL) {
        this.AL = AL;
        while(getButton().getActionListeners().length>0)
            getButton().removeActionListener(getButton().getActionListeners()[0]);
        getButton().addActionListener(AL);
    }
}
