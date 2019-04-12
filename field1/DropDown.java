package field1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DropDown extends AnnotationField1 {
    protected JComboBox dropdown;
    protected String[] options;

    public DropDown(String key, String[] options, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map)
    {
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        this.prev = prev;
        this.next = next;
        this.options = options;
        System.out.println("OPTIONS: " + options);
        if(prev != null)
            this.prev.next = this;
        if(next != null)
            this.next.prev = this;
        createDropdown(bounds, options);
    }

    public DropDown(String key, String[] options, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map)
    {
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        createDropdown(bounds, options);
    }


    public void updateLocation()
    {
        super.updateLocation();
        if(bounds!=null)
            dropdown.setBounds(bounds);
    }

    public JComboBox createDropdown(Rectangle dropdownBounds, String[] dropdownOptions)
    {
        System.out.println(dropdownOptions == null);
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
}