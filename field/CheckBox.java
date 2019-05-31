package field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckBox extends AnnotationField {
    public String name;
    public JCheckBox checkbox;

    public CheckBox(String key, String name, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.name = name;
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        this.prev = prev;
        createCheckBox(name, bounds);
    }

    public CheckBox(String key, String name, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        createCheckBox(name, bounds);
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(checkbox != null && bounds!=null)
            checkbox.setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(checkbox != null && bounds!=null)
            checkbox.setBounds(bounds);
    }

    public JCheckBox createCheckBox(String name, Rectangle checkboxBounds)
    {
        JCheckBox result = new JCheckBox(name);
        result.setBounds(checkboxBounds);
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String value;
                        if(result.isSelected())
                            value = "true";
                        else
                            value = "false";
                        valueStrings.clear();
                        valueStrings.add(value);
                        map.put(key, valueStrings);
                    }
                }
        );
        result.setVisible(true);
        panel.add(result);
        checkbox = result;
        updateLocation();
        return result;
    }

    public String toString()
    {
        return "Checkbox with key " + key;
    }

    public ArrayList<String> getValueStrings()
    {
        return valueStrings;
    }

}
