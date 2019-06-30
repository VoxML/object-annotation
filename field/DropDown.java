package field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DropDown extends AnnotationField {
    private JComboBox dropdown;
    private String[] options;

    public DropDown(String key, String[] options, Rectangle bounds, AnnotationComponent prev,
                    JPanel panel, HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.setSet(set);
        set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.setPanel(panel);
        this.map = map;
        this.setPrev(prev);
        this.options = options;
        if(map != null)
        {
            super.getValueStrings().clear();
            if(options.length>0)
                super.getValueStrings().add(options[0]);
            map.put(key, super.getValueStrings());
        }
        createDropdown(bounds, options);
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(getDropdown() != null && bounds != null)
            getDropdown().setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(getDropdown() != null && bounds != null)
            getDropdown().setBounds(bounds);
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
                        getValueStrings().clear();
                        getValueStrings().add(value);
                        getMap().put(getKey(), getValueStrings());
                    }
                }
        );
        result.setVisible(true);
        getPanel().add(result);
        dropdown = result;
        updateLocation();
        this.component = result;
        return getDropdown();
    }

    public ArrayList<String> getValueStrings()
    {
        String value = (String) getDropdown().getSelectedItem();
        super.getValueStrings().clear();
        super.getValueStrings().add(value);
        getMap().put(getKey(), super.getValueStrings());
        return super.getValueStrings();
    }

    public String toString()
    {
        return "Dropdown with key " + getKey();
    }

    public JComboBox getDropdown() {
        return dropdown;
    }

    public String[] getOptions() {
        return options;
    }
}