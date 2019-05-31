package field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TextField extends AnnotationField<String> {
    public JTextField textfield;
    public String key;

    public TextField(String key, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map, ArrayList<AnnotationComponent> set)
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
        createTextField(bounds);
    }

    public TextField(String key, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map, ArrayList<AnnotationComponent> set)
    {
        super(set);
        this.set = set;
        if(!set.contains(this))
            set.add(this);
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        createTextField(bounds);
    }


    public void updateLocation()
    {
        super.updateLocation();
        if(textfield != null && bounds!=null)
            textfield.setBounds(bounds);
    }

    protected void setHeight(int newHeight)
    {
        super.setHeight(newHeight);
        if(textfield != null && bounds!=null)
            textfield.setBounds(bounds);
    }

    public JTextField createTextField(Rectangle textfieldBounds)
    {
        JTextField result = new JTextField();
        result.setBounds(textfieldBounds);
        result.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String value = (String) result.getText();
                        valueStrings.clear();
                        valueStrings.add(value);
                        map.put(key, valueStrings);
                        if(map.containsKey("Intrinsic"))
                            map.put("Intr",map.get("Intrinsic"));
                        if(map.containsKey("Extrinsic"))
                            map.put("Extr",map.get("Extrinsic"));
                    }
                }
        );
        result.setVisible(true);
        panel.add(result);
        textfield = result;
        updateLocation();
        return textfield;
    }

    public String toString()
    {
        return "Text field with key " + key;
    }

}