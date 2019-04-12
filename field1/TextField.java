package field1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TextField extends AnnotationField1<String> {
    protected JTextField textfield;
    protected String key;

    public TextField(String key, Rectangle bounds, AnnotationComponent prev, AnnotationComponent next,
                    JPanel panel, HashMap<String, ArrayList<String>> map)
    {
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
        createTextField(bounds);
    }

    public TextField(String key, Rectangle bounds, JPanel panel, HashMap<String, ArrayList<String>> map)
    {
        this.key = key;
        this.bounds = bounds;
        this.panel = panel;
        this.map = map;
        createTextField(bounds);
    }


    public void updateLocation()
    {
        super.updateLocation();
        if(bounds!=null)
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
                        System.out.println(map);
                    }
                }
        );
        result.setVisible(true);
        panel.add(result);
        textfield = result;
        updateLocation();
        return textfield;
    }

}