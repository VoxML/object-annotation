package field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CheckBox extends AnnotationField {
    private String name;
    private JCheckBox checkbox;

    public CheckBox(String key, String name, Rectangle bounds, AnnotationComponent prev,
                    JPanel panel, HashMap<String, ArrayList<String>> map, HashSet<AnnotationComponent> set)
    {
        super(set);
        this.name = name;
        this.setSet(set);
        set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.setPanel(panel);
        this.map = map;
        this.setPrev(prev);
        createCheckBox(name, bounds);
        if(map != null)
        {
            super.getValueStrings().clear();
            super.getValueStrings().add("false");
            map.put(key, super.getValueStrings());
        }
    }

    public void updateLocation()
    {
        super.updateLocation();
        if(getCheckbox() != null && bounds!=null)
            getCheckbox().setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(getCheckbox() != null && bounds!=null)
            getCheckbox().setBounds(bounds);
    }

    public JCheckBox createCheckBox(String name, Rectangle checkboxBounds)
    {
        JCheckBox result = new JCheckBox(name);
        result.setBounds(checkboxBounds);
        result.setBackground(panel.getBackground());
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String value;
                        if(result.isSelected())
                            value = "true";
                        else
                            value = "false";
                        getValueStrings().clear();
                        getValueStrings().add(value);
                        getMap().put(getKey(), getValueStrings());
                    }
                }
        );
        result.setVisible(true);
        getPanel().add(result);
        checkbox = result;
        updateLocation();
        this.component = result;
        return result;
    }

    public ArrayList<String> getValueStrings() {
        String value;
        if(getCheckbox().isSelected())
            value = "true";
        else
            value = "false";
        super.getValueStrings().clear();
        super.getValueStrings().add(value);
        getMap().put(getKey(), super.getValueStrings());
        return super.getValueStrings();
    }

    public String toString()
    {
        return "Checkbox with key " + getKey();
    }

    public String getName() {
        return name;
    }

    public JCheckBox getCheckbox() {
        return checkbox;
    }
}
