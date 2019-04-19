package field1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckBox extends AnnotationField1 {
    String name;
    protected JCheckBox checkbox;

    public CheckBox(String key, String name, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.name = name;
        this.set = set;
        set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        this.prev = prev;
        this.next = next;
        if(prev != null)
            this.prev.next = this;
        if(next != null)
            this.next.prev = this;
        createCheckBox(name, bounds);
    }

    public CheckBox(String key, String name, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
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
        if(bounds!=null)
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


}
