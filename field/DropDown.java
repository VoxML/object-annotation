package field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;

public class DropDown extends AnnotationField {
    public JComboBox dropdown;
    public String[] options;

    public DropDown(String key, String[] options, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        this.prev = prev;
        this.options = options;
        if(map != null)
        {
            valueStrings.clear();
            if(options.length>0)
                valueStrings.add(options[0]);
            map.put(key, valueStrings);
        }
        createDropdown(bounds, options);
    }

    public DropDown(String key, String[] options, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        createDropdown(bounds, options);
    }


    public void updateLocation()
    {
        super.updateLocation();
        if(dropdown != null && bounds != null)
            dropdown.setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(dropdown != null && bounds != null)
            dropdown.setBounds(bounds);
    }

    public JComboBox createDropdown(Rectangle dropdownBounds, String[] dropdownOptions)
    {
        JComboBox<String> result = new JComboBox<String>(dropdownOptions);
        result.setBounds(dropdownBounds);
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String value = (String) result.getSelectedItem();
                        valueStrings.clear();
                        valueStrings.add(value);
                        map.put(key, valueStrings);
                    }
                }
        );
        result.setVisible(true);
        panel.add(result);
        dropdown = result;
        updateLocation();
        return dropdown;
    }

    public ArrayList<String> getValueStrings()
    {
        String value = (String) dropdown.getSelectedItem();
        valueStrings.clear();
        valueStrings.add(value);
        map.put(key, valueStrings);
        return valueStrings;
    }

    public String toString()
    {
        return "Dropdown with key " + key;
    }
}